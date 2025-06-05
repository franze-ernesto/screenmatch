package br.com.casa.screenmatch.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int ano;
    private String diretor;

    @Column(name = "soma_das_avaliacoes")
    private Double somaDasAvaliacoes;

    @Column(name = "total_de_avaliacoes")
    private int totalDeAvaliacoes;

    @Column(name = "duracao_em_minutos")
    private int duracaoEmMinutos;

}
