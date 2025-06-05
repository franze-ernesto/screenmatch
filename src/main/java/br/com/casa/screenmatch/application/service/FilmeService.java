package br.com.casa.screenmatch.application.service;

import br.com.casa.screenmatch.domain.model.Filme;
import br.com.casa.screenmatch.domain.repository.FilmeRepository;

import br.com.casa.screenmatch.interfaces.web.dto.input.DataRequestFilme;
import br.com.casa.screenmatch.interfaces.web.dto.output.DataResponseFilme;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    private final OmdbClientFilme omdbClientFilme;
    private final FilmeRepository filmeRespository;

    public FilmeService(OmdbClientFilme omdbClientFilme, FilmeRepository filmeRespository) {
        this.omdbClientFilme = omdbClientFilme;
        this.filmeRespository = filmeRespository;
    }

    public DataResponseFilme buscarFilme(DataRequestFilme request) {
        DataResponseFilme dados = omdbClientFilme.buscarFilmeTitulo(request.titulo());

        Filme filme = new Filme();

        filme.setTitulo(dados.titulo());
        filme.setAno(Integer.parseInt(dados.ano()));
        filme.setDiretor(dados.diretor());
        filme.setDuracaoEmMinutos(dados.duracaoEmMinutos());
        filme.setSomaDasAvaliacoes(0.0);
        filme.setTotalDeAvaliacoes(0);

        filmeRespository.save(filme);

        return dados;

    }
}
