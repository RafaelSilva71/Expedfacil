package org.example.expedfacil.service;

import org.example.expedfacil.controller.CreateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(CreateProdutoDTO createProdutoDTO) {

        //DTO -> entity

        var entity = new Produto(
                null,
                createProdutoDTO.codigo(),
                createProdutoDTO.descricao(),
                createProdutoDTO.lote(),
                createProdutoDTO. quantidade(),
                createProdutoDTO.localEstoque()
        );

        return produtoRepository.save(entity);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }


}
