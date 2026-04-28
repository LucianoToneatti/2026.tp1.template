package com.bibliotech.service;

import com.bibliotech.model.Libro;
import com.bibliotech.exception.BibliotecaException;
import java.util.List;

public interface LibroService {
    void registrarLibro(Libro libro);
    List<Libro> buscarPorTitulo(String titulo) throws BibliotecaException;
    List<Libro> buscarPorAutor(String autor) throws BibliotecaException;
    List<Libro> buscarPorCategoria(String categoria) throws BibliotecaException;
    List<Libro> listarTodos();
}
