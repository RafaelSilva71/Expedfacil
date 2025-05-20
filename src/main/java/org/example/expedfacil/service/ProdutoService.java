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
                createProdutoDTO.id(),
                createProdutoDTO.nome(),
                createProdutoDTO.quantPorCaixa(),
                createProdutoDTO.quantCxFd()
        );

        return produtoRepository.save(entity);
    }

    public Produto buscarPorId(String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> ListarProdutos() {
        return produtoRepository.findAll();
    }




    public void updateProduto(String id, UpdateProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));


        produto.setNome(dto.nome());
        produto.setQuantPorCaixa(dto.quantPorCaixa());
        produto.setQuantCxFd(dto.quantCxFd());


        produtoRepository.save(produto);
    }

    public void deleteById(String id) {
        produtoRepository.deleteById(id);
    }

}
