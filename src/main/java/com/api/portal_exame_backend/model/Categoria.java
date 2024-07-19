package com.api.portal_exame_backend.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String titulo;
    private String descricao;

    // Uma categoria para muitos exames
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore                                                      // vai ignorar o campo exames durante a serialização para JSON, evitando problemas de referência cíclica.
    private Set<Exame> exames = new LinkedHashSet<>();               // Exame inicializado como um LinkedHashSet, ou seja, isso armazena todos os exames associados a uma categoria.

    public Categoria() {
    }

    public Categoria(Long categoriaId, String titulo, String descricao) {
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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

    public Set<Exame> getExames() {
        return exames;
    }

    public void setExames(Set<Exame> exames) {
        this.exames = exames;
    }

}

/*OBS. Diferença entre LinkedHashSet e HashSet:
 * LinkedHashSet:
 *  -> Ordem de Inserção: Mantém a ordem de inserção dos elementos. Isso significa que quando você itera sobre um LinkedHashSet, os elementos serão 
 *     retornados na mesma ordem em que foram adicionados.
 * 
 *  -> Performance: Tem um desempenho um pouco inferior ao HashSet devido ao armazenamento adicional necessário para manter a ordem de inserção. 
 *     A complexidade de tempo para operações básicas (inserção, remoção, etc.) ainda é O(1), mas com um pequeno overhead.
 * 
 *  -> Uso de Memória: Usa mais memória do que um HashSet devido à manutenção de uma lista de links duplamente encadeada para todos os seus 
 *     elementos.
 * 
 * HashSet:
 *  -> Ordem de Inserção: Não garante a ordem dos elementos. A ordem dos elementos pode mudar ao longo do tempo e não será a mesma em que foram 
 *     inseridos.
 * 
 *  -> Performance: Geralmente tem um desempenho ligeiramente melhor que o LinkedHashSet devido à menor sobrecarga de armazenamento. A 
 *     complexidade de tempo para operações básicas (inserção, remoção, etc.) é O(1).
 * 
 *  -> Uso de Memória: Usa menos memória do que um LinkedHashSet já que não mantém a ordem de inserção dos elementos.
*/