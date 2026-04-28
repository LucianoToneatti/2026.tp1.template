package com.bibliotech.model;

public record LibroFisico(
    String isbn,
    String titulo,
    String autor,
    int anio,
    String categoria,
    int numeroPaginas
) implements Recurso {}
