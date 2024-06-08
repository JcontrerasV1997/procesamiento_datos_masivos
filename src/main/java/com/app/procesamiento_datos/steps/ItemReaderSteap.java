package com.app.procesamiento_datos.steps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import com.app.procesamiento_datos.model.entity.UsuarioEntity;
@Slf4j
public class ItemReaderSteap implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("__________________ Inicia Lectura__________________");
            FlatFileItemReader<UsuarioEntity> reader = new FlatFileItemReader<UsuarioEntity>();
            reader.setResource(new ClassPathResource("usuarios.csv"));
            reader.setLineMapper(new DefaultLineMapper<UsuarioEntity>() {{
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames("id", "nombre", "email");
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<UsuarioEntity>() {{
                    setTargetType(UsuarioEntity.class);
                }});
            }});

        return RepeatStatus.FINISHED;
    }
}
