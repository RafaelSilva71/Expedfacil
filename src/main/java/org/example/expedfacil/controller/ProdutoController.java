package org.example.expedfacil.controller;

import org.example.expedfacil.model.Produto;
import org.example.expedfacil.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Long> createProduto(@RequestBody CreateProdutoDTO dto) {
        Produto newProduto = produtoService.createProduto(dto);
        return ResponseEntity.ok(newProduto.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }
}
