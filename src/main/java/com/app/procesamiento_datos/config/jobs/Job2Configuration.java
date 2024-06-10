package com.app.procesamiento_datos.config.jobs;

import com.app.procesamiento_datos.steps.job2.ItemProcessorStep;
import com.app.procesamiento_datos.steps.job2.ItemReaderSteap;
import com.app.procesamiento_datos.steps.job2.ItemWriterStep;
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
public class Job2Configuration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep2() {
        return new ItemWriterStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep2() {
        return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemReaderSteap itemReaderStep2() {
        return new ItemReaderSteap();
    }

    @Bean
    public Step leerArchivoStep2() {
        return stepBuilderFactory.get("leerArchivoStep2")
                .tasklet(itemReaderStep2())
                .build();
    }

    @Bean
    public Step procesarArchivoStep2() {
        return stepBuilderFactory.get("procesarArchivoStep2")
                .tasklet(itemProcessorStep2())
                .build();
    }


    @Bean
    public Step escribirArchivosStep2() {
        return stepBuilderFactory.get("escribirArchivosStep2")
                .tasklet(itemWriterStep2())
                .build();
    }

    @Bean
    public Job procesarArchivoJob2() {
        return jobBuilderFactory.get("procesarArchivoJob2")
                .start(leerArchivoStep2())
                .next(procesarArchivoStep2())
                .next(escribirArchivosStep2())
                .build();
    }

}
