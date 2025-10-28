package com.amandamariana.apipalavras.repository;

import com.amandamariana.apipalavras.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;

@Repository
public interface etiquetaRepository extends JpaRepository<Etiqueta, Long> {
}
