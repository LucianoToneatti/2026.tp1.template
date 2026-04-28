package com.bibliotech.repository;

import com.bibliotech.model.Socio;
import java.util.*;

public class SocioRepositoryImpl implements SocioRepository {

    private final Map<String, Socio> storage = new HashMap<>();

    @Override
    public void save(Socio socio) {
        storage.put(socio.dni(), socio);
    }

    @Override
    public Optional<Socio> findById(String dni) {
        return Optional.ofNullable(storage.get(dni));
    }

    @Override
    public List<Socio> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String dni) {
        storage.remove(dni);
    }

    @Override
    public Optional<Socio> buscarPorEmail(String email) {
        return storage.values().stream()
            .filter(s -> s.email().equalsIgnoreCase(email))
            .findFirst();
    }

    @Override
    public boolean existeDni(String dni) {
        return storage.containsKey(dni);
    }

    @Override
    public boolean existeEmail(String email) {
        return storage.values().stream()
            .anyMatch(s -> s.email().equalsIgnoreCase(email));
    }
}
