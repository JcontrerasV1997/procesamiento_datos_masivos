package com.app.procesamiento_datos.config.jobs;

import com.app.procesamiento_datos.steps.step1.ItemProcessorStep;
import com.app.procesamiento_datos.steps.step1.ItemReaderSteap;
import com.app.procesamiento_datos.steps.step1.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBatchProcessing
public class Job1Configuration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    @Primary
    @JobScope
    public ItemWriterStep itemWriterStep() {
        return new ItemWriterStep();
    }

    @Bean
    @Primary
    @JobScope
    public ItemProcessorStep itemProcessorStep() {
        return new ItemProcessorStep();
    }

    @Bean
    @Primary
    @JobScope
    public ItemReaderSteap itemReaderStep() {
        return new ItemReaderSteap();
    }


    @Bean
    @Primary
    public Step leerArchivoStep() {
        return stepBuilderFactory.get("leerArchivoStep")
                .tasklet(itemReaderStep())
                .build();
    }

    @Bean
    @Primary
    public Step procesarArchivoStep() {
        return stepBuilderFactory.get("procesarArchivoStep")
                .tasklet(itemProcessorStep())
                .build();
    }


    @Bean
    @Primary
    public Step escribirArchivosStep() {
        return stepBuilderFactory.get("escribirArchivosStep")
                .tasklet(itemWriterStep())
                .build();
    }

@Bean
@Primary
    public Job procesarArchivoJob() {
        return jobBuilderFactory.get("procesarArchivoJob")
                .start(leerArchivoStep())
                .next(procesarArchivoStep())
                .next(escribirArchivosStep())
                .build();
    }


}
