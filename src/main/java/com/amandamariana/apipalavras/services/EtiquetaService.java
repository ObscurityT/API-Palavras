package com.amandamariana.apipalavras.services;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.repository.EtiquetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EtiquetaService {


    private EtiquetaRepository etiquetaRepository;

    @Transactional(readOnly = true)
    public List<Etiqueta> listarTodas() {
        return etiquetaRepository.findAll();
    }

    @Transactional
    public Etiqueta salvar(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    public void deletarPorNome(String nome)
    {
        Etiqueta etiqueta = etiquetaRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Etiqueta não encontranda" + nome));
    }


    public Etiqueta atualizarPorNome(String nome, Etiqueta etiqueta) {
        Etiqueta existente = etiquetaRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Etiqueta não encontrada: " + nome));

        existente.setNome(etiqueta.getNome());
        return  etiquetaRepository.save(existente);
    }
}
