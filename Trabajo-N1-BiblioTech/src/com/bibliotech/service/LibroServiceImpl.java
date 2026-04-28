package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Libro;
import com.bibliotech.repository.LibroRepository;
import java.util.List;

public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public void registrarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) throws BibliotecaException {
        List<Libro> resultado = libroRepository.buscarPorTitulo(titulo);
        if (resultado.isEmpty()) {
            throw new BibliotecaException("No se encontraron libros con el título: " + titulo);
        }
        return resultado;
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) throws BibliotecaException {
        List<Libro> resultado = libroRepository.buscarPorAutor(autor);
        if (resultado.isEmpty()) {
            throw new BibliotecaException("No se encontraron libros del autor: " + autor);
        }
        return resultado;
    }

    @Override
    public List<Libro> buscarPorCategoria(String categoria) throws BibliotecaException {
        List<Libro> resultado = libroRepository.buscarPorCategoria(categoria);
        if (resultado.isEmpty()) {
            throw new BibliotecaException("No se encontraron libros en la categoría: " + categoria);
        }
        return resultado;
    }

    @Override
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }
}
