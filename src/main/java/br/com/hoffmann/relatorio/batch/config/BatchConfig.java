package br.com.hoffmann.relatorio.batch.config;

import br.com.hoffmann.relatorio.batch.step.*;
import br.com.hoffmann.relatorio.batch.step.dto.RelatorioDto;
import br.com.hoffmann.relatorio.batch.step.dto.RelatorioStatisticaDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public RelatorioStepOneReader relatorioStepOneReader;

    @Autowired
    public RelatorioStepOneProcessor relatorioStepOneProcessor;

    @Autowired
    public RelatorioStepOneWriter relatorioStepOneWriter;

    @Autowired
    public RelatorioStepTwoReader relatorioStepTwoReader;

    @Autowired
    public RelatorioStepTwoProcessor relatorioStepTwoProcessor;

    @Autowired
    public RelatorioStepTwoWriter relatorioStepTwoWriter;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStepOne())
                .end()
                .build();
    }

    @Bean
    public Step orderStepOne() {
        return stepBuilderFactory.get("orderStepOne").<String, RelatorioDto>chunk(1)
                .reader(relatorioStepOneReader)
                .processor(relatorioStepOneProcessor)
                .writer(relatorioStepOneWriter)
                .build();
    }

    @Bean
    public Step orderStepTwo() {
        return stepBuilderFactory.get("orderStepTwo").<RelatorioStatisticaDto, RelatorioStatisticaDto>chunk(1)
                .reader(relatorioStepTwoReader)
                .processor(relatorioStepTwoProcessor)
                .writer(relatorioStepTwoWriter)
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

}