package org.example.expedfacil.service;

import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.controller.dto.UpdateProdutoDTO;
import org.example.expedfacil.exception.ProdutoJaExisteException;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(CreateProdutoDTO createProdutoDTO) {
        if (produtoRepository.existsById(createProdutoDTO.id())) {
            throw new ProdutoJaExisteException(createProdutoDTO.id());
        }

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
