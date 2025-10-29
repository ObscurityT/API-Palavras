package com.amandamariana.apipalavras.repository;

import com.amandamariana.apipalavras.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amandamariana.apipalavras.model.Palavra;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalavraRepository extends JpaRepository<Palavra, Long> {
    boolean existsByTermo(String termo);
    Optional<Palavra> findByTermo(String termo);
}
