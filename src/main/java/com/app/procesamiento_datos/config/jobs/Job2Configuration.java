package com.app.procesamiento_datos.config.jobs;

import com.app.procesamiento_datos.steps.job1.ItemProcessorStep;
import com.app.procesamiento_datos.steps.job1.ItemReaderSteap;
import com.app.procesamiento_datos.steps.job1.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Job2Configuration {


}
