package br.com.alura.screenmatch_2;

import br.com.alura.screenmatch_2.model.DadosEpisodios;
import br.com.alura.screenmatch_2.model.DadosSeries;
import br.com.alura.screenmatch_2.model.DadosTemporada;
import br.com.alura.screenmatch_2.service.ConsumoAPI;
import br.com.alura.screenmatch_2.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=the+office&apikey=f1cb3336");
		System.out.println(json);
		var conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
		System.out.println(dados);
		json = consumoAPI.obterDados("https://www.omdbapi.com/?t=the+office&season=1&episode=1&apikey=f1cb3336");
		DadosEpisodios dadosEpisodios = conversor.obterDados(json, DadosEpisodios.class);
		System.out.println(dadosEpisodios);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas() ; i++) {
			json = consumoAPI.obterDados("https://www.omdbapi.com/?t=the+office&season=" + i + "&apikey=f1cb3336");
			DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(temporada);
		}

		temporadas.forEach(System.out::println);
	}
}
