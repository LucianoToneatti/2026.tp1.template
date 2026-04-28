package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.exception.LibroNoDisponibleException;
import com.bibliotech.exception.LimitePrestamosException;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PrestamoServiceImpl implements PrestamoService {

    private final List<Prestamo> prestamos = new ArrayList<>();
    private final Set<String> recursosEnPrestamo = new HashSet<>();

    @Override
    public void prestar(Socio socio, Recurso recurso) throws BibliotecaException {
        if (recursosEnPrestamo.contains(recurso.isbn())) {
            throw new LibroNoDisponibleException(recurso.isbn());
        }

        long prestamosActivos = prestamos.stream()
            .filter(p -> p.socio().dni().equals(socio.dni()) && p.fechaDevolucion() == null)
            .count();

        if (prestamosActivos >= socio.limiteLibros()) {
            throw new LimitePrestamosException(socio.nombre());
        }

        prestamos.add(new Prestamo(socio, recurso, LocalDate.now(), null));
        recursosEnPrestamo.add(recurso.isbn());
    }

    @Override
    public void devolver(Socio socio, Recurso recurso) throws BibliotecaException {
        Prestamo prestamo = prestamos.stream()
            .filter(p -> p.socio().dni().equals(socio.dni())
                && p.recurso().isbn().equals(recurso.isbn())
                && p.fechaDevolucion() == null)
            .findFirst()
            .orElseThrow(() -> new BibliotecaException(
                "No se encontró un préstamo activo para el recurso: " + recurso.isbn()));

        prestamos.remove(prestamo);
        prestamos.add(new Prestamo(socio, recurso, prestamo.fechaPrestamo(), LocalDate.now()));
        recursosEnPrestamo.remove(recurso.isbn());
    }

    @Override
    public List<Prestamo> historial(Socio socio) {
        return prestamos.stream()
            .filter(p -> p.socio().dni().equals(socio.dni()))
            .collect(Collectors.toList());
    }
}
