package br.com.casa.screenmatch.interfaces.web.dto.output;

public record DataResponseFilme (
        String titulo,
        String ano,
        String diretor,
        String genero,
        Double somaDasAvaliacoes,
        int totalDeAvaliacoes,
        int duracaoEmMinutos

) {}

