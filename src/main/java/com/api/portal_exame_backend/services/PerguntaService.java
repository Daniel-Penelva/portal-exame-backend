package com.api.portal_exame_backend.services;

import java.util.Set;

import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.model.Pergunta;

public interface PerguntaService {

    Pergunta criarPergunta(Pergunta pergunta);

    Pergunta atualizarPergunta(Pergunta pergunta);

    Set<Pergunta> buscarTodasPerguntas();

    Pergunta buscarPerguntaPorId(Long perguntaId);

    void removerPergunta(Long perguntaId);

    Set<Pergunta> buscarPerguntasDeExame(Exame exame);

    Pergunta listarPergunta(Long perguntaId);

}
