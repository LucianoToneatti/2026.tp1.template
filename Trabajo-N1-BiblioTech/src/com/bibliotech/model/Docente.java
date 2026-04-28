package com.bibliotech.model;

public record Docente(
    int id,
    String nombre,
    String dni,
    String email
) implements Socio {

    @Override
    public int limiteLibros() {
        return 5;
    }
}
