package org.example.expedfacil.service;

import org.example.expedfacil.controller.CreateProdutoDTO;
import org.example.expedfacil.controller.UpdateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
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

    public List<Produto> ListarProdutos() {
        return produtoRepository.findAll();
    }




    public void updateProduto(Long id, UpdateProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));


        produto.setCodigo(dto.codigo());
        produto.setDescricao(dto.descricao());
        produto.setLote(dto.lote());
        produto.setQuantidade(dto.quantidade());
        produto.setLocalEstoque(dto.localEstoque());


        produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }




}
