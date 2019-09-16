package br.com.hoffmann.relatorio.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "RELATORIO")
public class Relatorio {

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RELATORIO")
    @SequenceGenerator(sequenceName = "SQ_RELATORIO", allocationSize = 1, name = "SQ_RELATORIO")
    private Long relatorioId;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    private Integer idade;

    public Long getRelatorioId() {
        return relatorioId;
    }

    public void setRelatorioId(Long relatorioId) {
        this.relatorioId = relatorioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relatorio relatorio = (Relatorio) o;
        return Objects.equals(relatorioId, relatorio.relatorioId) &&
                Objects.equals(nome, relatorio.nome) &&
                Objects.equals(idade, relatorio.idade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatorioId, nome, idade);
    }
}
