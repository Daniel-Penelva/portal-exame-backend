package com.api.portal_exame_backend.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    // Muitos exames para uma categoria
    @ManyToOne(fetch = FetchType.EAGER)  // Aqui, a estratégia de carregamento dos dados aplica que a categoria associada a um exame será carregada imediatamente junto com o exame.
    private Categoria categoria;

    // Um exame para muitas perguntas
    @OneToMany(mappedBy = "exame", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // fetch = FetchType.LAZY: Define que a busca das perguntas associadas a um exame será feita de maneira "preguiçosa", ou seja,  as perguntas não serão carregadas imediatamente com o exame, mas apenas quando forem explicitamente acessadas. 
    @JsonIgnore
    private Set<Pergunta> perguntas = new HashSet<>();

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

}
