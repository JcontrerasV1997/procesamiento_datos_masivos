package com.app.procesamiento_datos.model.entity;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioEntity {

    @Id
    private Long id;

    private String nombre;

    private String email;
}
