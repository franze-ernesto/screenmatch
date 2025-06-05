package br.com.casa.screenmatch.application.service;

import br.com.casa.screenmatch.interfaces.web.dto.output.DataResponseFilme;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class OmdbClientFilme {
    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public DataResponseFilme buscarFilmeTitulo (String titulo) {
        String url = String.format("%s?t=%s&apiKey=%s", apiUrl, titulo, apiKey);
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        if (response == null || response.get("Response").asText().equalsIgnoreCase("False")) {
            throw new RuntimeException("Filme não encontrado");

        }

        return new DataResponseFilme(
                response.get("Title").asText(),
                response.get("Year").asText(),
                response.get("Director").asText(),
                response.get("Genre").asText(),
                0.0,
                0,
                converterDuracao(response.get("Runtime").asText())

        );

    }

    private int converterDuracao(String duracao) {
        try {
            return Integer.parseInt(duracao.replaceAll("minutes", ""));
        }
        catch (Exception e) {
            return 0;
        }
    }


}
