package com.amandamariana.apipalavras.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name="palavras")
@Entity(name="palavras")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Palavra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String termo;

    @ManyToMany
    @JoinTable(
            name = "palavra_etiqueta",
            joinColumns = @JoinColumn(name = "palavra_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    )
    private Set<Etiqueta> etiquetas;
}
