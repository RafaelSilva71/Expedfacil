package org.example.expedfacil.service;

import org.example.expedfacil.controller.dto.conferencia.CargaConferidaDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ConferenciaTempStorageService {

    private final Map<String, CargaConferidaDTO> armazenamentoTemporario = new HashMap<>();

    public void salvar(String numeroEmbarqueOriginal, CargaConferidaDTO dto) {
        armazenamentoTemporario.put(numeroEmbarqueOriginal, dto);
    }

    public Optional<CargaConferidaDTO> buscar(String numeroEmbarqueOriginal) {
        return Optional.ofNullable(armazenamentoTemporario.get(numeroEmbarqueOriginal));
    }

    public Map<String, CargaConferidaDTO> listarTodas() {
        return new HashMap<>(armazenamentoTemporario);
    }

    public boolean remover(String numeroEmbarqueOriginal) {
        return armazenamentoTemporario.remove(numeroEmbarqueOriginal) != null;
    }
}

