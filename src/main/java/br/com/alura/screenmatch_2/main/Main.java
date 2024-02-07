package br.com.alura.screenmatch_2.main;

import br.com.alura.screenmatch_2.model.DadosEpisodios;
import br.com.alura.screenmatch_2.model.DadosSeries;
import br.com.alura.screenmatch_2.model.DadosTemporada;
import br.com.alura.screenmatch_2.service.ConsumoAPI;
import br.com.alura.screenmatch_2.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f1cb3336";
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("Digite o nome da série que busca: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas() ; i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }

        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulos())));

        List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
