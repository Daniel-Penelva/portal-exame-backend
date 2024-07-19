package com.api.portal_exame_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exames")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exameId;

    private String titulo;
    private String descricao;
    private String pontosMaximos;
    private String numeroDePerguntas;
    private boolean ativo = false;

    public Exame() {
    }

    public Exame(Long exameId, String titulo, String descricao, String pontosMaximos, String numeroDePerguntas,
            boolean ativo) {
        this.exameId = exameId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontosMaximos = pontosMaximos;
        this.numeroDePerguntas = numeroDePerguntas;
        this.ativo = ativo;
    }

    public Long getExameId() {
        return exameId;
    }

    public void setExameId(Long exameId) {
        this.exameId = exameId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPontosMaximos() {
        return pontosMaximos;
    }

    public void setPontosMaximos(String pontosMaximos) {
        this.pontosMaximos = pontosMaximos;
    }

    public String getNumeroDePerguntas() {
        return numeroDePerguntas;
    }

    public void setNumeroDePerguntas(String numeroDePerguntas) {
        this.numeroDePerguntas = numeroDePerguntas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
