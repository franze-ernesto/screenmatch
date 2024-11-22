package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.dto.DadosEpisodio;
import br.com.alura.screenmatch.dto.DadosSerie;
import br.com.alura.screenmatch.dto.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();



    Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        System.out.println("\nMenu Principal");
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

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).nome() );
//            }
//
//        }
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 Episodios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de qual ano você deseja ver os Episódios? ");
        var ano = sc.nextInt();
        sc.nextLine();

        LocalDate dataBusca = LocalDate.of(ano,  1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                "Episodio: " + e.getTitulo() +
                                "Data lançamento: " + e.getDataLancamento().format(formatador)
                ));

    }

}
