package com.thinkhappily.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration
{
    private PlatformTransactionManager transactionManager;
    private JobRepository jobRepository;

    @Autowired
    public BatchConfiguration(PlatformTransactionManager pTransactionManager, JobRepository pJobRepository)
    {
        transactionManager = pTransactionManager;
        jobRepository = pJobRepository;
    }

    @Bean
    public static JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry)
    {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory()
    {
        return new JobBuilderFactory(jobRepository);
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory()
    {
        return new StepBuilderFactory(jobRepository, transactionManager);
    }
}
