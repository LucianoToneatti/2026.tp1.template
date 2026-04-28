package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import java.util.List;

public interface PrestamoService {
    void prestar(Socio socio, Recurso recurso) throws BibliotecaException;
    void devolver(Socio socio, Recurso recurso) throws BibliotecaException;
    List<Prestamo> historial(Socio socio);
}
