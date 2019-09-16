package br.com.hoffmann.relatorio.batch.step;

import br.com.hoffmann.relatorio.batch.step.dto.RelatorioDto;
import br.com.hoffmann.relatorio.batch.step.dto.RelatorioStatisticaDto;
import br.com.hoffmann.relatorio.entity.Relatorio;
import br.com.hoffmann.relatorio.repository.RelatorioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@StepScope
public class RelatorioStepOneWriter implements ItemWriter<RelatorioDto> {
    private static final Logger LOG = LoggerFactory.getLogger(RelatorioStepOneWriter.class);

    private static final String SQL_ERROR = "[SQL_ERROR]";

    @Autowired
    private RelatorioRepository repository;

    @Autowired
    private EntityManager entityManager;

    private StepExecution stepExecution;
    private Map<String, Integer> mapError = new HashMap<>();
    private RelatorioStatisticaDto relatorioStatisticaDto = new RelatorioStatisticaDto();

    Integer totalArquivosErro = 0;
    Integer totalArquivosOk = 0;
    Integer numeroDaLinhaDoErro;
    List<String> errosTotal = new ArrayList<>();
    List<String> listaAuxiliar = new ArrayList<>();
    String getError = null;

    @BeforeStep
    public void saveStepExecution(final StepExecution stepExecution) {this.stepExecution = stepExecution; }

    @Override
    public void write(List<? extends RelatorioDto> items) throws Exception {

        if (isEmpty(items.get(0).getRegistroComErro()) && salvaRelatorio(items.get(0).getRegistroOk())){
            this.totalArquivosOk++;
            relatorioStatisticaDto.setTotalArquivosOk(this.totalArquivosOk);

        } else {
            this.totalArquivosErro++;

            this.numeroDaLinhaDoErro = (this.totalArquivosOk + this.totalArquivosErro);
            relatorioStatisticaDto.setGetTotalArquivosErro(this.totalArquivosErro);
            relatorioStatisticaDto.setErroDaLinha(this.errosTotal);

            if (!isEmpty(items.get(0).getRegistroComErro())){
                listaAuxiliar.add(items.get(0).getRegistroComErro());
                mapError.put(items.get(0).getRegistroComErro(), this.numeroDaLinhaDoErro);
            } else {
                mapError.put(listaAuxiliar.get(this.totalArquivosErro -1), this.numeroDaLinhaDoErro);
            }
            relatorioStatisticaDto.setMapError(mapError);
        }

    }

    public boolean salvaRelatorio (Relatorio relatorio){

        try {
            repository.save(relatorio);
            repository.flush();
        } catch (Exception error){
            entityManager.clear();
            getError = SQL_ERROR + " -> " + error.getMessage();
            listaAuxiliar.add(getError);
            LOG.info("Falha ao salvar o Relatorio");
            return  false;
        }
        return true;
    }

}
