package com.app.procesamiento_datos.config.jobs;

import com.app.procesamiento_datos.steps.job1.ItemProcessorStep;
import com.app.procesamiento_datos.steps.job1.ItemReaderSteap;
import com.app.procesamiento_datos.steps.job1.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class Job1Configuration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep() {
        return new ItemWriterStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep() {
        return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemReaderSteap itemReaderSteap() {
        return new ItemReaderSteap();
    }


    @Bean
    public Step leerArchivoStep() {
        return stepBuilderFactory.get("leerArchivoStep")
                .tasklet(itemReaderSteap())
                .build();
    }

    @Bean
    public Step procesarArchivoStep() {
        return stepBuilderFactory.get("procesarArchivoStep")
                .tasklet(itemProcessorStep())
                .build();
    }


    @Bean
    public Step escribirArchivosStep() {
        return stepBuilderFactory.get("escribirArchivosStep")
                .tasklet(itemWriterStep())
                .build();
    }

@Bean
    public Job procesarArchivoJob() {
        return jobBuilderFactory.get("procesarArchivoJob")
                .start(leerArchivoStep())
                .next(procesarArchivoStep())
                .next(escribirArchivosStep())
                .build();
    }


}
