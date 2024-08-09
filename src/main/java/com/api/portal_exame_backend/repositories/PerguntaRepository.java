package com.api.portal_exame_backend.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.model.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    Set<Pergunta> findByExame(Exame exame);  // Este método busca todas as perguntas que pertencem a um determinado exame.

    @Query("SELECT COUNT(p) FROM Pergunta p WHERE p.exame.exameId = :exameId")
    Long countByExameId(@Param("exameId") Long exameId);
}
