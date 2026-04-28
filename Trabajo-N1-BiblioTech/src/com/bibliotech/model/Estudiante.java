package com.bibliotech.model;

public record Estudiante(
    int id,
    String nombre,
    String dni,
    String email
) implements Socio {

    @Override
    public int limiteLibros() {
        return 3;
    }
}
