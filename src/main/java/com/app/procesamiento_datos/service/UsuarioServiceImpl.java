package com.app.procesamiento_datos.service;

import com.app.procesamiento_datos.dao.UsuarioService;
import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;


/*La clase UsuarioServiceImpl es una implementación del servicio UsuarioService. Contiene un método guardarDatosUsuario que se encarga de guardar una lista de entidades UsuarioEntity en una base de datos. Aquí está cómo funciona:
        Se crea un objeto JdbcBatchItemWriter, que es una utilidad de Spring Batch para escribir datos en una base de datos utilizando JDBC.
        Se establece el DataSource del escritor, que es el objeto que proporciona las conexiones a la base de datos.
        Se establece el proveedor de parámetros del ítem del escritor, que es el objeto que proporciona los valores de los parámetros para la sentencia SQL. En este caso, se utiliza BeanPropertyItemSqlParameterSourceProvider, que extrae los valores de los parámetros de las propiedades del bean.
        Se establece la sentencia SQL que se utilizará para insertar los datos en la base de datos.
        Se llama al método afterPropertiesSet del escritor, que es necesario para inicializar el escritor después de que se han establecido todas sus propiedades.
        Finalmente, se llama al método write del escritor para escribir la lista de entidades UsuarioEntity en la base de datos.*/
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
