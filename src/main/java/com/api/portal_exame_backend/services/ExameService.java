package com.api.portal_exame_backend.services;

import java.util.List;
import java.util.Set;

import com.api.portal_exame_backend.model.Categoria;
import com.api.portal_exame_backend.model.Exame;

public interface ExameService {

    Exame criarExame(Exame exame);

    Exame atualizarExame(Exame exame);

    Set<Exame> buscarTodosExames();

    Exame buscarExamePorId(Long exameId);

    void removerExame(Long exameId);

    List<Exame> buscarExamePorCategoria(Categoria categoria);

    List<Exame> buscarExameAtivo();

    List<Exame> buscarExameAtivoDeUmaCategoria(Categoria categoria);

}
