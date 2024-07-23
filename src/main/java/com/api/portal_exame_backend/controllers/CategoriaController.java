package com.api.portal_exame_backend.controllers;

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
import com.api.portal_exame_backend.services.CategoriaService;

@RestController                                  // Indica que a classe é um controlador REST e que cada método retorna diretamente um objeto JSON ou XML como resposta.
@RequestMapping("/categoria")                    // URL base para todos os endpoints dentro desta classe. Todas as rotas começam com /categoria.
@CrossOrigin("*")                                // Permite solicitações de qualquer origem (Cross-Origin Resource Sharing). O asterisco (*) significa que qualquer domínio pode acessar os endpoints deste controlador.
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // http://localhost:8080/categoria/
    @PostMapping("/")
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.criarCategoria(categoria));
    }

    // http://localhost:8080/categoria/
    @PutMapping("/")
    public Categoria atualizarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.atualizarCategoria(categoria);
    }

    // http://localhost:8080/categoria/
    @GetMapping("/")
    public ResponseEntity<?> buscarTodasCategorias() {                         // O ponto de interrogação (?) indica um tipo curinga, ou seja, representa um tipo desconhecido. Quando se usa ResponseEntity<?>, está dizendo que o tipo da resposta é genérico e pode ser qualquer coisa.  
        return ResponseEntity.ok(categoriaService.buscarTodasCategorias());
    }

    // http://localhost:8080/categoria/{categoriaId}
    @GetMapping("/{categoriaId}")
    public Categoria buscarCategoriaPorId(@PathVariable("categoriaId") Long categoriaId) {
        return categoriaService.buscarCategoriaPorId(categoriaId);
    }

    // http://localhost:8080/categoria/{categoriaId}
    @DeleteMapping("/{categoriaId}")
    public void removerCategoria(@PathVariable("categoriaId") Long categoriaId) {
        categoriaService.removerCategoria(categoriaId);
    }

}
