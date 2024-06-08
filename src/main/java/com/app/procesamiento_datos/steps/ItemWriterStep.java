package com.app.procesamiento_datos.steps;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ItemWriterStep implements Tasklet {


    /*En este paso se escriben para guardar los datos procesados en una base de datos*/
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        log.info("__________________ Inicia Escritura__________________");

        List<UsuarioEntity> listaDeUsuarios = (List<UsuarioEntity>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("listaDeUsuarios");

        listaDeUsuarios.forEach(p ->{
            if (p != null) {
                log.info("Usuario: " + p.toString());
            }
            else {
                log.info("Usuario: null");
            }
        });

        JdbcBatchItemWriter<UsuarioEntity> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO usuarios (id, nombre, email) VALUES (:id, :nombre, :email)");
       // writer.setDataSource(dataSource);

        return RepeatStatus.FINISHED;
    }
}
