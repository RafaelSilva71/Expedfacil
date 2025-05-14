package org.example.expedfacil.controller;

import org.example.expedfacil.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {
    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        return null;

    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
       return null;

    }

}
