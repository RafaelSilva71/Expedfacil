package org.example.expedfacil.repository;

import org.example.expedfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {



}
