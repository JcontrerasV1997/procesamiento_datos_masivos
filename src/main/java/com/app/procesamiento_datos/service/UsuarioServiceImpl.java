package com.app.procesamiento_datos.service;

import com.app.procesamiento_datos.dao.UsuarioService;
import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    public void guardarDatosUsuario(DataSource datasource, List<UsuarioEntity> listaDeUsuarios) throws Exception {
        JdbcBatchItemWriter<UsuarioEntity> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(datasource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO usuarios (id, email, nombre) VALUES (:id, :email, :nombre)");
        writer.afterPropertiesSet();
        writer.write(listaDeUsuarios);

    }
}
