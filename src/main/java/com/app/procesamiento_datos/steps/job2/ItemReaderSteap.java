package com.app.procesamiento_datos.steps.job2;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemReaderSteap implements Tasklet {

    /*Este Step es el paso de lectura de nuestro archivo*/

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("__________________ Inicia Lectura__________________");
        JdbcCursorItemReader<UsuarioEntity> reader = databaseItemReader();
        reader.open(new ExecutionContext()); // Abre el lector
        List<UsuarioEntity> listaUsuario = new ArrayList<>();
        UsuarioEntity usuario;
        while ((usuario = reader.read()) != null) {
            log.info("Usuario leído: " + usuario);
            UsuarioEntity usuarioLectura = new UsuarioEntity();
            usuarioLectura.setId(usuario.getId());
            usuarioLectura.setNombre(usuario.getNombre());
            usuarioLectura.setEmail(usuario.getEmail());
            listaUsuario.add(usuarioLectura);
        }

        reader.close(); // Cierra el lector cuando hayas terminado
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("listaDeUsuarios", listaUsuario);

        return RepeatStatus.FINISHED;
    }

    private JdbcCursorItemReader<UsuarioEntity> databaseItemReader() {
        JdbcCursorItemReader<UsuarioEntity> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource1);
        reader.setSql("SELECT id, nombre, email FROM usuarios");
        reader.setRowMapper(new UsuarioEntityRowMapper());
        return reader;
    }


}
