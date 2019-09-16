package br.com.hoffmann.relatorio.batch.step.dto;

import br.com.hoffmann.relatorio.entity.Relatorio;

import java.io.Serializable;

public class RelatorioDto implements Serializable {

    private Relatorio registroOk;
    private String RegistroComErro;

    public Relatorio getRegistroOk() {
        return registroOk;
    }

    public void setRegistroOk(Relatorio registroOk) {
        this.registroOk = registroOk;
    }

    public String getRegistroComErro() {
        return RegistroComErro;
    }

    public void setRegistroComErro(String registroComErro) {
        RegistroComErro = registroComErro;
    }
}
