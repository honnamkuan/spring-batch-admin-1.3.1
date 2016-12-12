package com.thinkhappily.batch.jobconfig;

import com.thinkhappily.batch.joblistener.SampleJobListener;
import com.thinkhappily.batch.steplistener.SampleStepListener;
import com.thinkhappily.batch.tasklet.PropertyReaderTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.CompositeJobExecutionListener;
import org.springframework.batch.core.listener.CompositeStepExecutionListener;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JavaJobConfig
{
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JavaJobConfig(JobBuilderFactory pJobBuilderFactory, StepBuilderFactory pStepBuilderFactory)
    {
        jobBuilderFactory = pJobBuilderFactory;
        stepBuilderFactory = pStepBuilderFactory;
    }


    @Bean
    public Tasklet propertyReaderTasklet()
    {
        return new PropertyReaderTasklet();
    }

    @Bean
    public StepExecutionListener compositeStepListener()
    {
        CompositeStepExecutionListener listener = new CompositeStepExecutionListener();
        listener.register(new SampleStepListener());
        return listener;
    }

    @Bean
    public Step sampleStep()
    {
        return stepBuilderFactory.get("sampleStep")
                .tasklet(propertyReaderTasklet())
                .listener(compositeStepListener())
                .build();
    }

    @Bean
    public Step sampleStep2()
    {
        return stepBuilderFactory.get("sampleStep2")
                .tasklet(propertyReaderTasklet())
                .listener(compositeStepListener())
                .build();
    }


    @Bean
    public JobExecutionListener compositeJobListener()
    {
        CompositeJobExecutionListener listener = new CompositeJobExecutionListener();
        listener.register(new SampleJobListener());
        return listener;
    }

    @Bean
    public Job javaJob()
    {
        return jobBuilderFactory.get("javaJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(sampleStep())
                .next(sampleStep2())
                .listener(compositeJobListener())
                .build();
    }
}
