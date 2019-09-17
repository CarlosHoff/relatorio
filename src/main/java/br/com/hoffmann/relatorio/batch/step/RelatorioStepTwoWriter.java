package br.com.hoffmann.relatorio.batch.step;

import br.com.hoffmann.relatorio.batch.step.dto.RelatorioStatisticaDto;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

@Component
@StepScope
public class RelatorioStepTwoWriter implements ItemWriter<RelatorioStatisticaDto> {

    @Value("${arquivo.saida}")
    private String arquivoSaida;

    @Override
    public void write(List<? extends RelatorioStatisticaDto> items) throws Exception {


        StringBuilder builder = new StringBuilder();

        FileWriter fw = new FileWriter(arquivoSaida);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < items.get(0).getErroDaLinha().size(); i++) {
            String erroDaLinha = items.get(0).getErroDaLinha().get(i);
            Integer linhaDeErro = items.get(0).getMapError().get(erroDaLinha);
            builder.append("Erro na linha: " + linhaDeErro + " - " + erroDaLinha + "\r\n");
        }

        if (!items.get(0).getGetTotalArquivosErro().equals(0)) {
            builder.append("\r\n");
        }

        builder.append("************ Relatório ************" + "\r\n");
        builder.append("Total de arquivos com sucesso: " + items.get(0).getTotalArquivosOk() + "\r\n");
        builder.append("Total de arquivos com erro: " + items.get(0).getGetTotalArquivosErro() + "\r\n");
        builder.append("***********************************");
        pw.write(builder.toString());

        pw.close();

    }

}
