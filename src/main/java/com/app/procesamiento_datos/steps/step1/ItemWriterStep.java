package com.app.procesamiento_datos.steps.step1;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import com.app.procesamiento_datos.service.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ItemWriterStep implements Tasklet {

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;

    @Autowired
    private UsuarioServiceImpl usuarioService;

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
            usuarioService.guardarDatosUsuario(dataSource1,listaDeUsuarios);
        }
        return RepeatStatus.FINISHED;
    }
}
