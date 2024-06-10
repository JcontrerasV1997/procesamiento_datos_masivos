package com.app.procesamiento_datos.model.mappers;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioEntityRowMapper implements RowMapper<UsuarioEntity> {
    @Override
    public UsuarioEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId((long) rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}
