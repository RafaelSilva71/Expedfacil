package org.example.expedfacil.controller;

import jakarta.validation.Valid;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Long> createProduto(@RequestBody @Valid CreateProdutoDTO createProdutoDTO) {
        Produto newProduto = produtoService.createProduto(createProdutoDTO);
        return ResponseEntity.ok(newProduto.getId());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
       Produto produto = produtoService.buscarPorId(id);
       if(produto != null) {
           return ResponseEntity.ok(produto);
       } else {
           return ResponseEntity.notFound().build();
       }
    }


}
