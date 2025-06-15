package org.example.expedfacil.mongo;

import org.example.expedfacil.model.LogDeCarga;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogDeCargaRepository extends MongoRepository<LogDeCarga, String> {
}
