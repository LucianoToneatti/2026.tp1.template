package com.bibliotech.model;

import java.time.LocalDate;

public record Prestamo(
    Socio socio,
    Recurso recurso,
    LocalDate fechaPrestamo,
    LocalDate fechaDevolucion
) {}
