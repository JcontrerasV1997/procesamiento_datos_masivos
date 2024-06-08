package com.app.procesamiento_datos.config;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import com.app.procesamiento_datos.steps.ItemProcessorStep;
import com.app.procesamiento_datos.steps.ItemWriterStep;
import com.batch.steps.ItemDescompressStep;
import com.batch.steps.ItemProcesorStep;
import com.batch.steps.ItemReaderStep;
import com.batch.steps.ItemWriterStep;
import lombok.Data;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobReader {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    @JobScope
    private  ItemProcessorStep processor;
    @Bean
    @JobScope
    private ItemWriterStep writer;

    public JobReader(JobBuilderFactory jobBuilderFactory,
                     StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;}

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .<UsuarioEntity, UsuarioEntity> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .flow(myStep())
                .end()
                .build();
    }
}
