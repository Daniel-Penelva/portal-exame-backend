package com.api.portal_exame_backend.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.portal_exame_backend.exception.ResourceNotFoundException;
import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.model.Pergunta;
import com.api.portal_exame_backend.repositories.ExameRepository;
import com.api.portal_exame_backend.repositories.PerguntaRepository;
import com.api.portal_exame_backend.services.PerguntaService;

@Service
public class PerguntaServiceImpl implements PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Override
    public Pergunta criarPergunta(Pergunta pergunta) {

        Long exameId = pergunta.getExame().getExameId();

        // Verificar se o exame existe
        if (!exameRepository.existsById(exameId)) {
            throw new ResourceNotFoundException("Exame com ID " + exameId + " não encontrado.");
        }

        // Contar o número de perguntas associadas ao exame
        long count = contarPerguntasPorExameId(exameId);

        // Verificar se o limite de 10 perguntas já foi atingido
        if (count >= 10) {
            throw new IllegalStateException("O limite de 10 perguntas para este exame já foi atingido.");
        }

        return perguntaRepository.save(pergunta);
    }

    @Override
    public Pergunta atualizarPergunta(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    /* findAll(): Este método retorna uma lista de todas as perguntas no banco de dados.
     * new HashSet<>(perguntasList): A conversão explícita da lista em um conjunto (Set) utilizando o construtor de HashSet.
     * Streams: Utilizando streams, eu posso converter a lista em um Set de forma mais fluida e clara.
    */
    @Override
    public Set<Pergunta> buscarTodasPerguntas() {
        return perguntaRepository.findAll().stream().collect(Collectors.toSet());
    }

/* Ou posso fazer assim também: 
    @Override
    public Set<Pergunta> buscarTodasPerguntas() {
        List<Pergunta> perguntasList = perguntaRepository.findAll();
        return new HashSet<>(perguntasList);
    }
*/

    @Override
    public Pergunta buscarPerguntaPorId(Long perguntaId) {
        return perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pergunta com ID " + perguntaId + " não encontrado"));
    }

    @Override
    public void removerPergunta(Long perguntaId) {
        Pergunta pergunta = new Pergunta();
        pergunta.setPerguntaId(perguntaId);
        perguntaRepository.delete(pergunta);
    }

    @Override
    public Set<Pergunta> buscarPerguntasDeExame(Exame exame) {
        return perguntaRepository.findByExame(exame);
    }

    @Override
    public Pergunta listarPergunta(Long perguntaId) {
        return this.perguntaRepository.getOne(perguntaId);  // O método getOne do repositório retorna uma referência à entidade sem carregá-la imediatamente do banco de dados (lazy loading).
    }

    @Override
    public Long contarPerguntasPorExameId(Long exameId) {
        return perguntaRepository.countByExameId(exameId);
    }

}

/*OBS. Uso de Set: A conversão de List para Set em buscarTodasPerguntas garante que não haja duplicatas nas perguntas retornadas.*/
