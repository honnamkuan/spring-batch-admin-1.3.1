package com.thinkhappily.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

public class PropertyReaderTasklet implements Tasklet
{
    private String value;

    @Override
    public RepeatStatus execute(StepContribution pStepContribution, ChunkContext pChunkContext) throws Exception
    {
        pChunkContext.getStepContext().getStepExecution().getExecutionContext().putString("textValue", value);

        return RepeatStatus.FINISHED;
    }

    @Value("${textValue}")
    public void setValue(String value)
    {
        this.value = value;
    }
}