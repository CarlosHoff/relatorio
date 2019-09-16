package br.com.hoffmann.relatorio.batch.step.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioStatisticaDto implements Serializable {

    private Integer totalArquivosOk;
    private Integer getTotalArquivosErro;
    private Integer linhaQueDeuErro;
    private List<String> erroDaLinha;
    private Map<String, Integer> mapError = new HashMap<>();

    public Integer getTotalArquivosOk() {
        return totalArquivosOk;
    }

    public void setTotalArquivosOk(Integer totalArquivosOk) {
        this.totalArquivosOk = totalArquivosOk;
    }

    public Integer getGetTotalArquivosErro() {
        return getTotalArquivosErro;
    }

    public void setGetTotalArquivosErro(Integer getTotalArquivosErro) {
        this.getTotalArquivosErro = getTotalArquivosErro;
    }

    public Integer getLinhaQueDeuErro() {
        return linhaQueDeuErro;
    }

    public void setLinhaQueDeuErro(Integer linhaQueDeuErro) {
        this.linhaQueDeuErro = linhaQueDeuErro;
    }

    public List<String> getErroDaLinha() {
        return erroDaLinha;
    }

    public void setErroDaLinha(List<String> erroDaLinha) {
        this.erroDaLinha = erroDaLinha;
    }

    public Map<String, Integer> getMapError() {
        return mapError;
    }

    public void setMapError(Map<String, Integer> mapError) {
        this.mapError = mapError;
    }
}
