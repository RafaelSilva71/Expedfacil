package org.example.expedfacil.repository;

import org.example.expedfacil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

