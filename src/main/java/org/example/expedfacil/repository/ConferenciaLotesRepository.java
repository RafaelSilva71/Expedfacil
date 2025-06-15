package org.example.expedfacil.repository;

import org.example.expedfacil.model.ConferenciaLotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConferenciaLotesRepository extends JpaRepository<ConferenciaLotes, Long> {
    Optional<ConferenciaLotes> findByNumeroEmbarqueOriginal(String numeroEmbarqueOriginal);
    boolean existsByNumeroEmbarqueOriginal(String numeroEmbarqueOriginal);
    void deleteByNumeroEmbarqueOriginal(String numeroEmbarqueOriginal);
}
