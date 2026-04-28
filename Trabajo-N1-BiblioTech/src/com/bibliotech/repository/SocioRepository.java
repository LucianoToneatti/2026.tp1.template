package com.bibliotech.repository;

import com.bibliotech.model.Socio;
import java.util.Optional;

public interface SocioRepository extends Repository<Socio, String> {
    Optional<Socio> buscarPorEmail(String email);
    boolean existeDni(String dni);
    boolean existeEmail(String email);
}
