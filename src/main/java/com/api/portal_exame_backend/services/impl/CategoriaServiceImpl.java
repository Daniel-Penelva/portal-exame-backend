package com.api.portal_exame_backend.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.portal_exame_backend.exception.ResourceNotFoundException;
import com.api.portal_exame_backend.model.Categoria;
import com.api.portal_exame_backend.repositories.CategoriaRepository;
import com.api.portal_exame_backend.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> buscarTodasCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria buscarCategoriaPorId(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Categoria com ID " + categoriaId + " não encontrado"));
    }

    @Override
    public void removerCategoria(Long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        categoriaRepository.delete(categoria);
    }

}
