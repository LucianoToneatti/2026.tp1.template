package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.exception.SocioNoEncontradoException;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.SocioRepository;
import java.util.List;

public class SocioServiceImpl implements SocioService {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    private final SocioRepository socioRepository;

    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    @Override
    public void registrarSocio(Socio socio) throws BibliotecaException {
        if (socioRepository.existeDni(socio.dni())) {
            throw new BibliotecaException("Ya existe un socio con el DNI: " + socio.dni());
        }
        if (!socio.email().matches(EMAIL_REGEX)) {
            throw new BibliotecaException("El email no tiene un formato válido: " + socio.email());
        }
        if (socioRepository.existeEmail(socio.email())) {
            throw new BibliotecaException("Ya existe un socio con el email: " + socio.email());
        }
        socioRepository.save(socio);
    }

    @Override
    public Socio buscarPorDni(String dni) throws BibliotecaException {
        return socioRepository.findById(dni)
            .orElseThrow(() -> new SocioNoEncontradoException(0));
    }

    @Override
    public List<Socio> listarTodos() {
        return socioRepository.findAll();
    }
}
