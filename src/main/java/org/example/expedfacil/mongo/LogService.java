package org.example.expedfacil.mongo;

import org.example.expedfacil.model.LogDeCarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    private LogDeCargaRepository repository;

    public void registrarLog(String cargaId, String msg){
        LogDeCarga log = new LogDeCarga();

        log.setCargaId(cargaId);
        log.setMensagem(msg);
        log.setData(LocalDateTime.now().toString());
        repository.save(log);

    }
}
