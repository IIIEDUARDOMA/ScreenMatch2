package br.com.alura.screenmatch_2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(@JsonAlias("Title") String titulos,
                             @JsonAlias("Episode") Integer episodio,
                             @JsonAlias("imdbRating") String avaliacao,
                             @JsonAlias("Released") String dataLnacamento) {
}
