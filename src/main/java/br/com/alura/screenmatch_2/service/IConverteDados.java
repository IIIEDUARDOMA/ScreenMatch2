package br.com.alura.screenmatch_2.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
