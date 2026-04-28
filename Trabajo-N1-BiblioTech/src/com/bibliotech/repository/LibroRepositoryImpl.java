package com.bibliotech.repository;

import com.bibliotech.model.Libro;
import java.util.*;
import java.util.stream.Collectors;

public class LibroRepositoryImpl implements LibroRepository {

    private final Map<String, Libro> storage = new HashMap<>();

    @Override
    public void save(Libro libro) {
        storage.put(libro.isbn(), libro);
    }

    @Override
    public Optional<Libro> findById(String isbn) {
        return Optional.ofNullable(storage.get(isbn));
    }

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String isbn) {
        storage.remove(isbn);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return storage.values().stream()
            .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        return storage.values().stream()
            .filter(l -> l.autor().toLowerCase().contains(autor.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Libro> buscarPorCategoria(String categoria) {
        return storage.values().stream()
            .filter(l -> l.categoria().toLowerCase().contains(categoria.toLowerCase()))
            .collect(Collectors.toList());
    }
}
