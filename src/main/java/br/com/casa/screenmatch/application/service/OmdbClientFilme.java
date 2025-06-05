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
    double somaDasAvaliacoes = 0.0;
    int totalDeAvaliacoes = 0;

    private final RestTemplate restTemplate = new RestTemplate();

    public DataResponseFilme buscarFilmeTitulo (String titulo) {
        String url = String.format("%s?t=%s&apiKey=%s", apiUrl, titulo, apiKey);
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        if (response == null || response.get("Response").asText().equalsIgnoreCase("False")) {
            throw new RuntimeException("Filme não encontrado");

        }

        JsonNode ratings = response.get("Ratings");
        if (ratings != null && ratings.isArray()) {
            for (JsonNode rating : ratings) {
                String source = rating.get("Source").asText();
                String value = rating.get("Value").asText();
                if (source.equals("Internet Movie Database")) {
                    String[] partes = value.split("/");
                    somaDasAvaliacoes = Double.parseDouble(partes[0]);
                    totalDeAvaliacoes = 1;
                }
            }
        }

        return new DataResponseFilme(
                response.get("Title").asText(),
                response.get("Year").asText(),
                response.get("Director").asText(),
                response.get("Genre").asText(),
                somaDasAvaliacoes,
                totalDeAvaliacoes,
                converterDuracao(response.get("Runtime").asText())
        );
    }

    private int converterDuracao(String duracao) {
        try {
            return Integer.parseInt(duracao.replaceAll("[^\\d]", ""));

        }
        catch (Exception e) {
            return 0;
        }
    }


}
