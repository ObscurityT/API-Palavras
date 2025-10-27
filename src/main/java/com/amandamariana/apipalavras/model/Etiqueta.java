package com.amandamariana.apipalavras.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name="etiquetas")
@Entity(name="etiquetas")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "etiquetas")
    private Set<Palavra> palavras;
}
