package com.amandamariana.apipalavras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amandamariana.apipalavras.model.Palavra;
import org.springframework.stereotype.Repository;

@Repository
public interface PalavraRepository extends JpaRepository<Palavra, Long> {
    boolean existsByTermo(String termo);
}
