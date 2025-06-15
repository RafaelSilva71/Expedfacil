package org.example.expedfacil.service;

import jakarta.transaction.Transactional;
import org.example.expedfacil.controller.dto.avaria.ProdutoAvariadoDTO;
import org.example.expedfacil.controller.dto.avaria.ProdutoAvariadoResponseDTO;
import org.example.expedfacil.model.*;
import org.example.expedfacil.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AvariaCargaService {

    private final AvariaCargaRepository avariaCargaRepository;
    private final ProdutoAvariadoRepository produtoAvariadoRepository;
    private final CargaRepository cargaRepository;
    private final ConferenciaLotesRepository conferenciaLotesRepository;
    private final ProdutoRepository produtoRepository; // ✅ Adicionado

    public AvariaCargaService(AvariaCargaRepository avariaCargaRepository,
                              ProdutoAvariadoRepository produtoAvariadoRepository,
                              CargaRepository cargaRepository,
                              ConferenciaLotesRepository conferenciaLotesRepository,
                              ProdutoRepository produtoRepository) { // ✅ No construtor
        this.avariaCargaRepository = avariaCargaRepository;
        this.produtoAvariadoRepository = produtoAvariadoRepository;
        this.cargaRepository = cargaRepository;
        this.conferenciaLotesRepository = conferenciaLotesRepository;
        this.produtoRepository = produtoRepository; // ✅ Atribuído
    }

    @Transactional
    public void registrarAvarias(List<ProdutoAvariadoDTO> dtoList) {
        if (dtoList.isEmpty()) return;

        String numeroEmbarque = dtoList.get(0).getNumeroEmbarque();

        Carga carga = cargaRepository.findByNumeroEmbarque(numeroEmbarque)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Carga com número " + numeroEmbarque + " não encontrada."));

        AvariaCarga avaria = avariaCargaRepository.findByNumeroEmbarque(numeroEmbarque)
                .orElseGet(() -> {
                    AvariaCarga nova = new AvariaCarga();
                    nova.setNumeroEmbarque(numeroEmbarque);
                    return avariaCargaRepository.save(nova);
                });

        List<ProdutoAvariado> produtos = dtoList.stream().map(dto -> {
            Produto produto = produtoRepository.findById(dto.getCodigoProduto())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                            "Produto com código " + dto.getCodigoProduto() + " não encontrado."));

            ProdutoAvariado p = new ProdutoAvariado();
            p.setCodigoProduto(dto.getCodigoProduto());
            p.setNomeProduto(produto.getNome()); // ✅ Nome preenchido automaticamente
            p.setLote(dto.getLote());
            p.setQuantidade(dto.getQuantidade());
            p.setMotivo(dto.getMotivo());
            p.setObservacao(dto.getObservacao());
            p.setAvariaCarga(avaria);
            return p;
        }).toList();

        produtoAvariadoRepository.saveAll(produtos);
    }

    public List<ProdutoAvariadoResponseDTO> listarTodas() {
        return produtoAvariadoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoAvariadoResponseDTO> listarPorNumeroEmbarque(String numeroEmbarque) {
        return produtoAvariadoRepository.findByAvariaCargaNumeroEmbarque(numeroEmbarque).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void atualizarObservacao(Long id, String novaObservacao) {
        ProdutoAvariado produto = produtoAvariadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Produto avariado com ID " + id + " não encontrado."));

        produto.setObservacao(novaObservacao);
        produtoAvariadoRepository.save(produto);
    }

    @Transactional
    public void deletarProdutoAvariado(Long id) {
        if (!produtoAvariadoRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Produto avariado não encontrado.");
        }
        produtoAvariadoRepository.deleteById(id);
    }

    private ProdutoAvariadoResponseDTO toDTO(ProdutoAvariado p) {
        ProdutoAvariadoResponseDTO dto = new ProdutoAvariadoResponseDTO();
        dto.setId(p.getId());
        dto.setCodigoProduto(p.getCodigoProduto());
        dto.setNomeProduto(p.getNomeProduto());
        dto.setLote(p.getLote());
        dto.setQuantidade(p.getQuantidade());
        dto.setMotivo(p.getMotivo());
        dto.setObservacao(p.getObservacao());
        dto.setNumeroEmbarque(p.getAvariaCarga().getNumeroEmbarque());
        return dto;
    }
}
