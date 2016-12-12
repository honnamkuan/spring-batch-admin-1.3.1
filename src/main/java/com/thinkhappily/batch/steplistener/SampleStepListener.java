package com.thinkhappily.batch.steplistener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.util.StringUtils;

public class SampleStepListener extends StepExecutionListenerSupport
{
    @Override
    public ExitStatus afterStep(StepExecution pStepExecution)
    {
        String textValue = pStepExecution.getExecutionContext().getString("textValue");
        if (StringUtils.hasText(textValue))
        {
            ExitStatus exitStatus = pStepExecution.getExitStatus().addExitDescription("textValue:" + textValue);
            pStepExecution.setExitStatus(exitStatus);
        }

        return pStepExecution.getExitStatus();
    }

}
