package org.example.expedfacil.controller;

import org.example.expedfacil.model.Carga;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.service.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carga")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @Autowired
    private CargaRepository cargaRepository;


    @PostMapping
    public ResponseEntity<Carga> criarCarga(@RequestBody CreateCargaDTO dto) {
        Carga novaCarga = cargaService.create(dto);
        return ResponseEntity.ok(novaCarga);
    }

    @GetMapping
    public ResponseEntity<List<Carga>> listarTodas() {
        return ResponseEntity.ok(cargaRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Carga> buscarPorId(@PathVariable String id) {
        return cargaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (!cargaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cargaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
