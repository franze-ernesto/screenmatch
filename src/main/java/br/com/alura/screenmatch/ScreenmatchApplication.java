package br.com.alura.screenmatch;

import br.com.alura.screenmatch.dto.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=stranger+things&apikey=" + System.getenv("API_KEY"));

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.converteDados(json, DadosSerie.class);
		System.out.println(dados);


	}
}
