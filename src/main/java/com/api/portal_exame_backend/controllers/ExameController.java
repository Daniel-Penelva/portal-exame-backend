package com.api.portal_exame_backend.controllers;

import java.util.List;

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

import com.api.portal_exame_backend.model.Categoria;
import com.api.portal_exame_backend.model.Exame;
import com.api.portal_exame_backend.services.ExameService;

@RestController
@RequestMapping("/exame")
@CrossOrigin("*")
public class ExameController {

    @Autowired
    private ExameService exameService;

    // http://localhost:8080/exame/
    @PostMapping("/")
    public ResponseEntity<Exame> criarExame(@RequestBody Exame exame) {
        return ResponseEntity.ok(exameService.criarExame(exame));
    }

    // http://localhost:8080/exame/
    @PutMapping("/")
    public ResponseEntity<Exame> atualizarExame(@RequestBody Exame exame) {
        return ResponseEntity.ok(exameService.atualizarExame(exame));
    }

    // http://localhost:8080/exame/
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosExames() {
        return ResponseEntity.ok(exameService.buscarTodosExames());
    }

    // http://localhost:8080/exame/{exameId}
    @GetMapping("/{exameId}")
    public Exame buscarExamePorId(@PathVariable("exameId") Long exameId) {
        return exameService.buscarExamePorId(exameId);
    }

    // http://localhost:8080/exame/{exameId}
    @DeleteMapping("/{exameId}")
    public void removerExame(@PathVariable("exameId") Long exameId) {
        exameService.removerExame(exameId);
    }

    // http://localhost:8080/exame/categoria/{categoriaId}
    @GetMapping("/categoria/{categoriaId}")
    public List<Exame> buscarExamePorCategoria(@PathVariable("categoriaId") long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return exameService.buscarExamePorCategoria(categoria);
    }

    // http://localhost:8080/exame/ativo
    @GetMapping("/ativo")
    public List<Exame> buscarExameAtivo() {
        return exameService.buscarExameAtivo();
    }

    // http://localhost:8080/exame/categoria/ativo/{categoriaId}
    @GetMapping("/categoria/ativo/{categoriaId}")
    public List<Exame> buscarExameAtivoDeUmaCategoria(@PathVariable("categoriaId") Long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return exameService.buscarExameAtivoDeUmaCategoria(categoria);
    }
}
