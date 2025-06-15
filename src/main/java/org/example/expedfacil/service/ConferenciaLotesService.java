package org.example.expedfacil.service;

import org.example.expedfacil.controller.dto.conferencia.*;
import org.example.expedfacil.controller.dto.conferencia.response.CargaConferidaResponseDTO;
import org.example.expedfacil.controller.dto.conferencia.response.EntregaConferidaResponseDTO;
import org.example.expedfacil.controller.dto.conferencia.response.LoteConferidoResponseDTO;
import org.example.expedfacil.controller.dto.conferencia.response.ProdutoConferidoResponseDTO;
import org.example.expedfacil.model.*;
import org.example.expedfacil.repository.ConferenciaLotesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConferenciaLotesService {

    private final ConferenciaLotesRepository repository;

    public ConferenciaLotesService(ConferenciaLotesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void salvar(String numeroEmbarqueOriginal, CargaConferidaDTO dto) {
        ConferenciaLotes conferencia = new ConferenciaLotes();
        conferencia.setNumeroEmbarqueOriginal(numeroEmbarqueOriginal);

        List<EntregaConferida> entregas = dto.getEntregasConferidas().stream().map(entregaDTO -> {
            EntregaConferida entrega = new EntregaConferida();
            entrega.setNumeroEntrega(Integer.valueOf(entregaDTO.getNumeroEntrega()));
            entrega.setConferencia(conferencia);

            List<ProdutoConferido> produtos = entregaDTO.getProdutos().stream().map(produtoDTO -> {
                ProdutoConferido produto = new ProdutoConferido();
                produto.setCodigoProduto(produtoDTO.getCodigoProduto());
                produto.setNomeProduto(produtoDTO.getNomeProduto());
                produto.setQuantidadeTotal(produtoDTO.getQuantidadeTotal());
                produto.setEntrega(entrega);

                List<LoteConferido> lotes = produtoDTO.getLotes().stream().map(loteDTO -> {
                    LoteConferido lote = new LoteConferido();
                    lote.setLote(loteDTO.getLote());
                    lote.setValidade(loteDTO.getValidade()); // mantém como String
                    lote.setQuantidade(loteDTO.getQuantidade());

                    lote.setDataProducao(loteDTO.getDataProducao()); // mantém como String também

                    lote.setProduto(produto);
                    return lote;
                }).collect(Collectors.toList());



                produto.setLotes(lotes);
                return produto;
            }).collect(Collectors.toList());

            entrega.setProdutos(produtos);
            return entrega;
        }).collect(Collectors.toList());

        conferencia.setEntregasConferidas(entregas);
        repository.save(conferencia);
    }

    public Optional<ConferenciaLotes> buscar(String numeroEmbarqueOriginal) {
        return repository.findByNumeroEmbarqueOriginal(numeroEmbarqueOriginal);
    }

    public List<CargaConferidaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean remover(String numeroEmbarqueOriginal) {
        if (repository.existsByNumeroEmbarqueOriginal(numeroEmbarqueOriginal)) {
            repository.deleteByNumeroEmbarqueOriginal(numeroEmbarqueOriginal);
            return true;
        }
        return false;
    }

    public CargaConferidaResponseDTO toDTO(ConferenciaLotes conferencia) {
        CargaConferidaResponseDTO dto = new CargaConferidaResponseDTO();
        dto.setId(conferencia.getId());
        dto.setNumeroEmbarqueOriginal(conferencia.getNumeroEmbarqueOriginal());
        dto.setDataRegistro(conferencia.getDataRegistro());

        List<EntregaConferidaResponseDTO> entregasDTO = conferencia.getEntregasConferidas().stream().map(entrega -> {
            EntregaConferidaResponseDTO entregaDTO = new EntregaConferidaResponseDTO();
            entregaDTO.setId(entrega.getId());
            entregaDTO.setNumeroEntrega(entrega.getNumeroEntrega());

            List<ProdutoConferidoResponseDTO> produtosDTO = entrega.getProdutos().stream().map(produto -> {
                ProdutoConferidoResponseDTO produtoDTO = new ProdutoConferidoResponseDTO();
                produtoDTO.setId(produto.getId());
                produtoDTO.setCodigoProduto(produto.getCodigoProduto());
                produtoDTO.setNomeProduto(produto.getNomeProduto());
                produtoDTO.setQuantidadeTotal(produto.getQuantidadeTotal());

                List<LoteConferidoResponseDTO> lotesDTO = produto.getLotes().stream().map(lote -> {
                    LoteConferidoResponseDTO loteDTO = new LoteConferidoResponseDTO();
                    loteDTO.setId(lote.getId());
                    loteDTO.setLote(lote.getLote());

                    // Passa direto a String da entidade (sem conversão)
                    loteDTO.setValidade(lote.getValidade());
                    loteDTO.setDataProducao(lote.getDataProducao());

                    loteDTO.setQuantidade(lote.getQuantidade());
                    return loteDTO;
                }).collect(Collectors.toList());


                produtoDTO.setLotes(lotesDTO);
                return produtoDTO;
            }).collect(Collectors.toList());

            entregaDTO.setProdutos(produtosDTO);
            return entregaDTO;
        }).collect(Collectors.toList());

        dto.setEntregasConferidas(entregasDTO);
        return dto;
    }
}
