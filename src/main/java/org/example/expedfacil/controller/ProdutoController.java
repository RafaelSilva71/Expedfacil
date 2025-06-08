package org.example.expedfacil.controller;

import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.controller.dto.UpdateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.service.CargaService;
import org.example.expedfacil.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CargaService cargaService;

    public ProdutoController(ProdutoService produtoService, CargaService cargaService) {
        this.produtoService = produtoService;
        this.cargaService = cargaService;
    }

    @PostMapping
    public ResponseEntity<String> createProduto(@RequestBody CreateProdutoDTO dto) {
        Produto newProduto = produtoService.createProduto(dto);
        return ResponseEntity.ok(newProduto.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable String id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
      var produto =   produtoService.ListarProdutos();
      return ResponseEntity.ok(produto);

    }



    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProdutoById(@PathVariable String id, @RequestBody UpdateProdutoDTO dto) {
        produtoService.updateProduto(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
    produtoService.deleteById(String.valueOf(id));
    return ResponseEntity.noContent().build();
    }

}
