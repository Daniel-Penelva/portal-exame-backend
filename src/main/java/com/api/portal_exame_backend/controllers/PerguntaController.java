package com.api.portal_exame_backend.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.model.Pergunta;
import com.api.portal_exame_backend.services.ExameService;
import com.api.portal_exame_backend.services.PerguntaService;

@RestController                                  
@RequestMapping("/pergunta")                    
@CrossOrigin("*")   
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private ExameService exameService;

    // http://localhost:8080/pergunta/
    @PostMapping("/")
    public ResponseEntity<Pergunta> criarPergunta(@RequestBody Pergunta pergunta){
        return ResponseEntity.ok(perguntaService.criarPergunta(pergunta));
    }

    // http://localhost:8080/pergunta/
    @PutMapping("/")
    public ResponseEntity<Pergunta> atualizarPergunta(@RequestBody Pergunta pergunta){
        return ResponseEntity.ok(perguntaService.atualizarPergunta(pergunta));
    }

    // http://localhost:8080/pergunta/
    @GetMapping("/")
    public ResponseEntity<?> buscarTodasPerguntas(){
        return ResponseEntity.ok(perguntaService.buscarTodasPerguntas());
    }

    // http://localhost:8080/pergunta/{perguntaId}
    @GetMapping("/{perguntaId}")
    public Pergunta buscarPerguntaPorId(@PathVariable("perguntaId") Long perguntaId){
        return perguntaService.buscarPerguntaPorId(perguntaId);
    }

     // http://localhost:8080/pergunta/{perguntaId}
    @DeleteMapping("/{perguntaId}")
    public void removerPergunta(@PathVariable("perguntaId") Long perguntaId){
        perguntaService.removerPergunta(perguntaId);
    }

    // http://localhost:8080/pergunta/exame/{exameId}
    @GetMapping("/exame/{exameId}")
    public ResponseEntity<?> listarPerguntaPorExame(@PathVariable("exameId") Long exameId){
        
        Exame exame = exameService.buscarExamePorId(exameId);  // 2                                      // o exameService busca o exame correspondente ao exameId. 
        Set<Pergunta> perguntas = exame.getPerguntas();        // 6                                      // obtém o conjunto de perguntas associadas ao exame usando o método getPerguntas() do objeto Exame.

        List exames = new ArrayList(perguntas);  // 6                                                    // converte o conjunto de perguntas (Set) em uma lista (List) para facilitar manipulações subsequentes.

        if(exames.size() > Integer.parseInt(exame.getNumeroDePerguntas())){                              // verifica se o número de perguntas excede o valor especificado em exame.getNumeroDePerguntas().
            exames = exames.subList(0, Integer.parseInt(exame.getNumeroDePerguntas() + 1));    // se exceder, ajusta a lista para conter apenas o número especificado de perguntas.
        } // 6 = 6.sublist(0,3)

        Collections.shuffle(exames);                                                                     // embaralha a lista de perguntas para garantir que a ordem das perguntas não seja a mesma sempre que a lista for retornada.
        return ResponseEntity.ok(exames);                                                                // retorna a lista de perguntas embaralhadas como uma resposta HTTP 200 OK.
    }


    // http://localhost:8080/pergunta/exame/todos/{exameId}
    @GetMapping("/exame/todos/{exameId}")
    public ResponseEntity<?> listarPeguntaDoExameComoAdministrador(@PathVariable("exameId") Long exameId){
        Exame exame = new Exame();
        exame.setExameId(exameId);

        Set<Pergunta> perguntas = perguntaService.buscarPerguntasDeExame(exame);
        return ResponseEntity.ok(perguntas);
    }

    // http://localhost:8080/pergunta/avaliar-exame
    @PostMapping("/avaliar-exame")
    public ResponseEntity<?> avaliarExame(@RequestBody List<Pergunta> perguntas){

        double pontosMaximos = 0;
        Integer respostasCorretas = 0;
        Integer tentativas = 0;

        for (Pergunta p : perguntas) {
            Pergunta pergunta = this.perguntaService.listarPergunta(p.getPerguntaId());
            if(pergunta.getResposta().equals(p.getRespostaDada())){
                respostasCorretas ++;
                double pontos = Double.parseDouble(perguntas.get(0).getExame().getPontosMaximos())/perguntas.size();
                pontosMaximos += pontos; 

            }if(p.getRespostaDada() != null) {
                tentativas++;
            }
        }

        Map<String, Object> respostas = new HashMap<>();
        respostas.put("pontosMaximos", pontosMaximos);
        respostas.put("respostasCorretas", respostasCorretas);
        respostas.put("tentativas", tentativas);
        
        return ResponseEntity.ok(respostas);
    }

    // http://localhost:8080/pergunta/exame/contar/{exameId}
    @GetMapping("/exame/contar/{exameId}")
    public ResponseEntity<Long> contarPerguntas(@PathVariable Long exameId) {
        Long count = perguntaService.contarPerguntasPorExameId(exameId);
        return ResponseEntity.ok(count);
    }

}

/*Sobre sublist()
 * É utilizado para criar uma visão (sublista) de uma parte da lista original. Ele retorna uma nova lista que é uma parte da lista original, baseada em índices fornecidos.
 * 
 * Assinatura do Método:
 * List<E> subList(int fromIndex, int toIndex)
 * 
 *  -> fromIndex: O índice inicial (inclusivo) da sublista.
 *  -> toIndex: O índice final (exclusivo) da sublista.
 * 
 * O que faz?
 * Cria uma Sublista: Retorna uma nova lista que contém os elementos da lista original entre os índices fromIndex (inclusivo) e toIndex (exclusivo).
 * Baseada na Lista Original: A sublista é uma visão da lista original, o que significa que alterações na sublista afetam a lista original e vice-versa.
*/