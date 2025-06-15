package org.example.expedfacil.repository;

import org.example.expedfacil.model.ProdutoAvariado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoAvariadoRepository extends JpaRepository<ProdutoAvariado, Long> {

    List<ProdutoAvariado> findByAvariaCargaNumeroEmbarque(String numeroEmbarque);
}
