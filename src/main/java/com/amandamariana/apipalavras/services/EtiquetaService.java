package com.amandamariana.apipalavras.services;

import com.amandamariana.apipalavras.model.DTOs.EtiquetaRequestDTO;
import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;
import com.amandamariana.apipalavras.repository.EtiquetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    @Transactional(readOnly = true)
    public List<Etiqueta> listarTodas() {
        return etiquetaRepository.findAll();
    }

    @Transactional
    public Etiqueta salvar(EtiquetaRequestDTO etiquetaDTO) {
        if(etiquetaRepository.findByNome(etiquetaDTO.nome()).isPresent())
        {throw new RuntimeException("Etiqueta já existente");}
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNome(etiquetaDTO.nome());
        return etiquetaRepository.save(etiqueta);
    }

    public void deletarPorNome(String nome)
    {
        Etiqueta etiqueta = etiquetaRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Etiqueta não encontrada " + nome));

        etiquetaRepository.delete(etiqueta);
    }


    public Etiqueta atualizarPorNome(String nome, EtiquetaRequestDTO etiquetaDto) {
      Etiqueta etiqueta = etiquetaRepository.findByNome(nome)
              .orElseThrow(()-> new RuntimeException("Etiqueta não encontrada " + nome));

        etiqueta.setNome(etiquetaDto.nome());
        return  etiquetaRepository.save(etiqueta);
    }

    public Etiqueta buscarPorID(Long id) {
        return etiquetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etiqueta não encontrada com o id: " + id));
    }

    public Etiqueta buscarPalavrasPorEtiqueta(String nome) {
        return etiquetaRepository.findByNome(nome)
                .orElseThrow(()-> new RuntimeException("Etiqueta não encontrada " + nome));
    }
}
