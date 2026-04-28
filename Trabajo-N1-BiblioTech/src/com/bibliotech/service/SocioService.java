package com.bibliotech.service;

import com.bibliotech.model.Socio;
import com.bibliotech.exception.BibliotecaException;
import java.util.List;

public interface SocioService {
    void registrarSocio(Socio socio) throws BibliotecaException;
    Socio buscarPorDni(String dni) throws BibliotecaException;
    List<Socio> listarTodos();
}
