package com.app.procesamiento_datos.dao;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public interface UsuarioService {


    void guardarDatosUsuario(DataSource datasource, List<UsuarioEntity> listaDeUsuarios) throws Exception;

}
