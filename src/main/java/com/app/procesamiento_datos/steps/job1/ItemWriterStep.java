package com.app.procesamiento_datos.steps.job1;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ItemWriterStep implements Tasklet {

    @Autowired
    private  JdbcTemplate dataSource1;;


    /*En este paso se escriben para guardar los datos procesados en una base de datos*/
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("__________________ Inicia Escritura__________________");

        List<UsuarioEntity> listaDeUsuarios = (List<UsuarioEntity>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("listaDeUsuarios");

        if (!Objects.equals(listaDeUsuarios, null)) {
            listaDeUsuarios.forEach(usuario -> log.info("Usuario: " + usuario));
            JdbcBatchItemWriter<UsuarioEntity> writer = new JdbcBatchItemWriter<>();
            writer.setDataSource((DataSource) dataSource1);
            writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
            writer.setSql("INSERT INTO usuarios (id, email, nombre) VALUES (:id, :email, :nombre)");
            writer.afterPropertiesSet();
            writer.write(listaDeUsuarios);
        }


        return RepeatStatus.FINISHED;
    }
}
