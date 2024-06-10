package com.app.procesamiento_datos.steps.step1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import com.app.procesamiento_datos.model.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemReaderSteap implements Tasklet {

    /*Este Step es el paso de lectura de nuestro archivo*/



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("__________________ Inicia Lectura__________________");
        FlatFileItemReader<UsuarioEntity> reader = lecturaFlatFileItemReader();
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

    private static FlatFileItemReader<UsuarioEntity> lecturaFlatFileItemReader() {
        FlatFileItemReader<UsuarioEntity> reader = new FlatFileItemReader<UsuarioEntity>();
        reader.setResource(new ClassPathResource("files/archivo.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<UsuarioEntity>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "nombre", "email");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<UsuarioEntity>() {{
                setTargetType(UsuarioEntity.class);
            }});
        }});
        return reader;
    }
}
