package org.example.expedfacil.repository;

import org.example.expedfacil.model.AvariaCarga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvariaCargaRepository extends JpaRepository<AvariaCarga, Long> {
    Optional<AvariaCarga> findByNumeroEmbarque(String numeroEmbarque);
    boolean existsByNumeroEmbarque(String numeroEmbarque);
    void deleteByNumeroEmbarque(String numeroEmbarque);
}

