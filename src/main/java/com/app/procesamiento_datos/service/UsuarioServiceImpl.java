package com.app.procesamiento_datos.service;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import com.app.procesamiento_datos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public void guardarDatosUsuario(UsuarioEntity usuarioEntity) {


    }
}
