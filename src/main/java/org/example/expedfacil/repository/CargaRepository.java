package org.example.expedfacil.repository;

import org.example.expedfacil.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargaRepository extends JpaRepository<Carga, String> {
    Optional<Carga> findByNumeroEmbarque(String numeroEmbarque);
}

