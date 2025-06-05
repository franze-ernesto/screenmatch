package br.com.casa.screenmatch.interfaces.web.controller;

import br.com.casa.screenmatch.application.service.FilmeService;
import br.com.casa.screenmatch.domain.model.Filme;
import br.com.casa.screenmatch.interfaces.web.dto.input.DataRequestFilme;
import br.com.casa.screenmatch.interfaces.web.dto.output.DataResponseFilme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService service) {
        this.filmeService = service;
    }

    @PostMapping
    public ResponseEntity<DataResponseFilme> buscarFilme(@RequestBody DataRequestFilme requestFilmeilme) {
        DataResponseFilme responseFilme = filmeService.buscarFilme(requestFilmeilme);
        return ResponseEntity.ok(responseFilme);
    }
}
