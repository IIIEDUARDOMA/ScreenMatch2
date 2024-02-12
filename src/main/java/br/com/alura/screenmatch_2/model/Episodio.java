package br.com.alura.screenmatch_2.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private double avaliacao;
    private Integer numeroEpisodio;
    private LocalDate dataDeLancamento;

    public Episodio(Integer temporada, DadosEpisodios dadosEpisodios) {
        this.temporada = temporada;
        this.titulo = dadosEpisodios.titulos();
        this.numeroEpisodio = dadosEpisodios.episodio();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodios.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataDeLancamento = LocalDate.parse(dadosEpisodios.dataLnacamento());
        } catch (DateTimeException e) {
            this.dataDeLancamento = null;
        }
    }

        public Integer getTemporada () {
            return temporada;
        }

        public void setTemporada (Integer temporada){
            this.temporada = temporada;
        }

        public String getTitulo () {
            return titulo;
        }

        public void setTitulo (String titulo){
            this.titulo = titulo;
        }

        public double getAvaliacao () {
            return avaliacao;
        }

        public void setAvaliacao ( double avaliacao){
            this.avaliacao = avaliacao;
        }

        public Integer getNumeroEpisodio () {
            return numeroEpisodio;
        }

        public void setNumeroEpisodio (Integer numeroEpisodio){
            this.numeroEpisodio = numeroEpisodio;
        }

        public LocalDate getDataDeLancamento () {
            return dataDeLancamento;
        }

        public void setDataDeLancamento (LocalDate dataDeLancamento){
            this.dataDeLancamento = dataDeLancamento;
        }

        @Override
        public String toString () {
            return "temporada = " + temporada +
                    "\ntitulo = " + titulo +
                    "\navaliacao = " + avaliacao +
                    "\nnumeroEpisodio = " + numeroEpisodio +
                    "\ndataDeLancamento = " + dataDeLancamento + "\n";
        }
    }
