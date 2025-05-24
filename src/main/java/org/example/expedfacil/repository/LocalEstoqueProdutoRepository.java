package org.example.expedfacil.repository;

import org.example.expedfacil.model.LocalEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalEstoqueProdutoRepository extends JpaRepository<LocalEstoqueProduto, Long> {
    List<LocalEstoqueProduto> findByNumeroEmbarque(String numeroEmbarque);
}
