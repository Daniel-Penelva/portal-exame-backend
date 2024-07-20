package com.api.portal_exame_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perguntaId;

    @Column(length = 5000)
    private String conteudo;

    private String imagem;
    private String opcao1;
    private String opcao2;
    private String opcao3;
    private String opcao4;
    private String resposta;

    @Transient
    private String respostaDada; // campo transitório, ou seja, significa que o campo não será persistido no banco de dados. 

    public Pergunta() {
    }

    public Pergunta(Long perguntaId, String conteudo, String imagem, String opcao1, String opcao2, String opcao3,
            String opcao4, String resposta, String respostaDada) {
        this.perguntaId = perguntaId;
        this.conteudo = conteudo;
        this.imagem = imagem;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.opcao4 = opcao4;
        this.resposta = resposta;
        this.respostaDada = respostaDada;
    }

    public Long getPerguntaId() {
        return perguntaId;
    }

    public void setPerguntaId(Long perguntaId) {
        this.perguntaId = perguntaId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public String getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(String opcao3) {
        this.opcao3 = opcao3;
    }

    public String getOpcao4() {
        return opcao4;
    }

    public void setOpcao4(String opcao4) {
        this.opcao4 = opcao4;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getRespostaDada() {
        return respostaDada;
    }

    public void setRespostaDada(String respostaDada) {
        this.respostaDada = respostaDada;
    }

}

/*OBS. Sobre anotação @Transient 
 * A anotação @Transient em Java é usada para marcar um campo de uma entidade JPA (Java Persistence API) como transitório. Isso significa que o 
 * campo não será persistido no banco de dados. Em outras palavras, o valor do campo anotado com @Transient não será armazenado no banco de dados 
 * e não será considerado nas operações de persistência (inserção, atualização, etc.).
 * 
 * Quando usar @Transient?
 * Quando tem campos em sua entidade que são usados apenas para lógica de negócio em tempo de execução e não precisam ser persistidos. Por 
 * exemplo, pode usar @Transient para:
 *      -> Campos calculados que não precisam ser armazenados.
 *      -> Dados temporários ou de sessão.
 *      -> Campos que agregam informações auxiliares ou temporárias.
 * 
 * Essa abordagem é útil para manter a entidade leve e evitar o armazenamento de dados temporários ou desnecessários no banco de dados.
*/