package br.com.hoffmann.relatorio.batch.step;

import br.com.hoffmann.relatorio.batch.step.dto.RelatorioDto;
import br.com.hoffmann.relatorio.entity.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class RelatorioStepOneProcessor implements ItemProcessor<String, RelatorioDto> {
    private static final Logger LOG = LoggerFactory.getLogger(RelatorioStepOneProcessor.class);

    @Override
    public RelatorioDto process(String item) throws Exception {

        RelatorioDto relatorioDto = new RelatorioDto();

        String getError = null;

        String lineOfFile = item.replaceAll("\"", "");

        String[] dadosArquivo = lineOfFile.split(",");

        Relatorio relatorio = getRelatorio(dadosArquivo);

        if (relatorio.getNome().length() > 50){
            LOG.info("Nome maior que 50 caracteres");
            getError = "Nome maior que 50 caracteres" + dadosArquivo[0];
        }

        relatorioDto.setRegistroOk(relatorio);
        relatorioDto.setRegistroComErro(getError);

        return relatorioDto;
    }

    private Relatorio getRelatorio(String[] dadosArquivo) {

        Relatorio relatorio = new Relatorio();

        relatorio.setNome(dadosArquivo[0]);
        relatorio.setIdade(Integer.valueOf(dadosArquivo[1]));

        return relatorio;

    }
}
