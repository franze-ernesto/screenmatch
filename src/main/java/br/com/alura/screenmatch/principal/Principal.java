package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.dto.DadosEpisodio;
import br.com.alura.screenmatch.dto.DadosSerie;
import br.com.alura.screenmatch.dto.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();



    Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        System.out.println("\nMenu Principal");

        try{
            System.out.println("Digite o nome da série a ser buscada: ");
            var nomeSerie = sc.nextLine();

            var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY + System.getenv("API_KEY"));

            DadosSerie dados = conversor.converteDados(json, DadosSerie.class);
            System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i=1; i<=dados.totalTemporadas();i++) { //"https://www.omdbapi.com/?t=dexter&season=1&apikey=2e6aef5c"
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season="+ i + API_KEY + System.getenv("API_KEY"));
            DadosTemporada temporada = conversor.converteDados(json, DadosTemporada.class);
            temporadas.add(temporada);

        }
        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);



        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

            System.out.println(avaliacoesPorTemporada);

            DoubleSummaryStatistics estat = episodios.stream()
                    .filter(e -> e.getAvaliacao() > 0.0)
                    .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

            System.out.println("Média: " + estat.getAverage());
            System.out.println("Melhor episódio: " + estat.getMax());
            System.out.println("Pior episódio: " + estat.getMin());
            System.out.println("Quantidade: " + estat.getCount());

        } catch (NullPointerException e) {
            System.out.println("Série Não Encontrada");
        }


    }

}
