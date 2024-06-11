package com.app.procesamiento_datos.model.mappers;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/*La clase UsuarioEntityRowMapper es una implementación de la interfaz RowMapper de Spring JDBC. Esta clase se utiliza para mapear las filas de un ResultSet a una entidad UsuarioEntity. En el método mapRow:
        Se crea una nueva instancia de UsuarioEntity.
        Se establece el id del usuario con el valor de la columna "id" del ResultSet.
        Se establece el nombre del usuario con el valor de la columna "nombre" del ResultSet.
        Se establece el correo electrónico del usuario con el valor de la columna "email" del ResultSet.
        Finalmente, se devuelve la entidad UsuarioEntity mapeada.
        Esta clase es útil cuando se realiza una consulta a la base de datos y se necesita convertir el resultado en una lista de entidades UsuarioEntity.*/

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
