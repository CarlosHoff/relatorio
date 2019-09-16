package br.com.hoffmann.relatorio.batch.step;

import br.com.hoffmann.relatorio.batch.step.dto.RelatorioStatisticaDto;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class RelatorioStepTwoReader implements ItemReader<RelatorioStatisticaDto> {

    private RelatorioStatisticaDto relatorioStatisticaDto;

    @BeforeStep
    public void retrieveInterStepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.relatorioStatisticaDto = (RelatorioStatisticaDto) jobContext.get("relatorioStatisticaDto");
    }


    @Override
    public RelatorioStatisticaDto read() {
        return relatorioStatisticaDto;
    }
}
