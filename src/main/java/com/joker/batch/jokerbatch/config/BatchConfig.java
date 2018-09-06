package com.joker.batch.jokerbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joker.batch.jokerbatch.task.Task1;
import com.joker.batch.jokerbatch.task.Task2;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private StepBuilderFactory steps;
	@Autowired
	private JobBuilderFactory job;

	@Bean
	public Step buildStep1() {
		return steps.get("Step1").tasklet(new Task1()).build();
	}

	@Bean
	public Step buildStep2() {
		return steps.get("Step2").tasklet(new Task2()).build();
	}

	@Bean
	public Job buildJob() {
		return job.get("Joker batch job").
				incrementer(new RunIdIncrementer()).
				start(buildStep1()).
				next(buildStep2()).
				build();
	}
}
