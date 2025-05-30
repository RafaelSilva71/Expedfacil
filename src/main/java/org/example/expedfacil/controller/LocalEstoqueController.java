package org.example.expedfacil.controller;

import org.example.expedfacil.model.Carga;
import org.example.expedfacil.model.LocalEstoqueProduto;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.LocalEstoqueProdutoRepository;
import org.example.expedfacil.service.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local-estoque")
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueProdutoRepository localEstoqueProdutoRepository;

    // 🔍 1. Listar todos os locais de estoque salvos
    @GetMapping
    public ResponseEntity<List<LocalEstoqueProduto>> listarTodos() {
        List<LocalEstoqueProduto> todos = localEstoqueProdutoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    // 🔍 2. Listar locais de uma carga específica
    @GetMapping("/carga/{numeroEmbarque}")
    public ResponseEntity<List<LocalEstoqueProduto>> listarPorCarga(@PathVariable String numeroEmbarque) {
        List<LocalEstoqueProduto> locais = localEstoqueProdutoRepository.findByNumeroEmbarque(numeroEmbarque);
        return ResponseEntity.ok(locais);
    }

    // ✏️ 3. Atualizar o campo "localEstoque" de um produto específico
    @PutMapping("/{id}")
    public ResponseEntity<LocalEstoqueProduto> atualizarLocalEstoque(@PathVariable Long id,
                                                                     @RequestBody String novoLocal) {
        LocalEstoqueProduto produto = localEstoqueProdutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setLocalEstoque(novoLocal);
        localEstoqueProdutoRepository.save(produto);

        return ResponseEntity.ok(produto);
    }
}
