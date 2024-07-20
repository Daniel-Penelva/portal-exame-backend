package com.api.portal_exame_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.portal_exame_backend.model.Categoria;
import com.api.portal_exame_backend.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    List<Exame> findByCategoria(Categoria categoria);                         // Este método busca todos os exames que pertencem a uma determinada categoria.

    List<Exame> findByAtivo(Boolean estado);                                  // Este método busca todos os exames que têm um estado ativo ou inativo específico.

    List<Exame> findByCategoriaAndAtivo(Categoria categoria, Boolean estado); // Este método busca todos os exames que pertencem a uma determinada categoria e têm um estado ativo ou inativo específico.

}
