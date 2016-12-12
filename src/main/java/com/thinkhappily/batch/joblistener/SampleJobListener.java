package com.thinkhappily.batch.joblistener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class SampleJobListener extends JobExecutionListenerSupport
{
    @Override
    public void afterJob(JobExecution jobExecution)
    {
        StringBuilder jobExitDescription = new StringBuilder();

        int completedCount = 0;

        for (StepExecution stepExecution : jobExecution.getStepExecutions())
        {
            jobExitDescription.append(stepExecution.getStepName());
            jobExitDescription.append(" : ");
            jobExitDescription.append(stepExecution.getExitStatus().getExitDescription());
            jobExitDescription.append(System.lineSeparator());
            if (BatchStatus.COMPLETED.name().equals(stepExecution.getExitStatus().getExitCode()))
            {
                completedCount++;
            }
        }

        String jobExitStatusCode = String.format("%d/%d STEPS COMPLETED", completedCount,
                jobExecution.getStepExecutions().size());

        ExitStatus jobExitStatus = new ExitStatus(jobExitStatusCode, jobExitDescription.toString());

        jobExecution.setExitStatus(jobExitStatus);
    }

}
