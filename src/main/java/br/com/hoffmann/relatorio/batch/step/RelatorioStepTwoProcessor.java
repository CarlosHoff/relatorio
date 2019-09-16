package br.com.hoffmann.relatorio.batch.step;

import br.com.hoffmann.relatorio.batch.step.dto.RelatorioStatisticaDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class RelatorioStepTwoProcessor implements ItemProcessor<RelatorioStatisticaDto, RelatorioStatisticaDto> {

    @Override
    public RelatorioStatisticaDto process(RelatorioStatisticaDto item) throws Exception {
        return item;
    }
}
