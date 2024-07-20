package com.api.portal_exame_backend.services;

import java.util.Set;

import com.api.portal_exame_backend.model.Categoria;

public interface CategoriaService {

    Categoria criarCategoria(Categoria categoria);

    Categoria atualizarCategoria(Categoria categoria);

    Set<Categoria> buscarTodasCategorias();

    Categoria buscarCategoriaPorId(Long categoriaId);

    void removerCategoria(Long categoriaId);

}
