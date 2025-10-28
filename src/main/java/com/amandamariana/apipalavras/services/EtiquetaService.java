package com.amandamariana.apipalavras.services;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.repository.etiquetaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class etiquetaService {


    private etiquetaRepository etiquetaRepository;

    @Transactional(readOnly = true)
    public List<Etiqueta> listarTodas() {
        return etiquetaRepository.findAll();
    }

    @Transactional
    public Etiqueta salvar(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    public Etiqueta deletar(Etiqueta etiqueta)
    {
        return etiquetaRepository.delete(etiqueta);
    }


}
