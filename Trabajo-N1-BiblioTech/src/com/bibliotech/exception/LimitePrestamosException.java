package com.bibliotech.exception;

public class LimitePrestamosException extends BibliotecaException {
    public LimitePrestamosException(String nombre) {
        super("El socio " + nombre + " alcanzó su límite de préstamos.");
    }
}
