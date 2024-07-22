package com.api.portal_exame_backend.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.portal_exame_backend.exception.ResourceNotFoundException;
import com.api.portal_exame_backend.model.Categoria;
import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.repositories.ExameRepository;
import com.api.portal_exame_backend.services.ExameService;

@Service
public class ExameServiceImpl implements ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Override
    public Exame criarExame(Exame exame) {
        return exameRepository.save(exame);
    }

    @Override
    public Exame atualizarExame(Exame exame) {
        return exameRepository.save(exame);
    }

    @Override
    public Set<Exame> buscarTodosExames() {
        return new LinkedHashSet<>(exameRepository.findAll());
    }

    @Override
    public Exame buscarExamePorId(Long exameId) {
        return exameRepository.findById(exameId)
                .orElseThrow(() -> new ResourceNotFoundException("Exame com ID " + exameId + " não encontrado"));
    }

    @Override
    public void removerExame(Long exameId) {
        Exame exame = new Exame();
        exame.setExameId(exameId);
        exameRepository.delete(exame);
    }

    @Override
    public List<Exame> buscarExamePorCategoria(Categoria categoria) {
        return this.exameRepository.findByCategoria(categoria);
    }

    @Override
    public List<Exame> buscarExameAtivo() {
        return exameRepository.findByAtivo(true);
    }

    @Override
    public List<Exame> buscarExameAtivoDeUmaCategoria(Categoria categoria) {
        return exameRepository.findByCategoriaAndAtivo(categoria, true);
    }

}
