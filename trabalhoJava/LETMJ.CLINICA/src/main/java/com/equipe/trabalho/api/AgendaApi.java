package com.equipe.trabalho.api;

import com.equipe.trabalho.model.Agenda;
import com.equipe.trabalho.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendas")
public class AgendaApi {
    @Autowired private AgendaService service;

    @GetMapping
    public List<Agenda> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agenda criar(@RequestBody Agenda agenda) {
        return service.salvar(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda dados) {
        Optional<Agenda> existente = service.buscar(id);
        if (existente.isPresent()) {
            dados.setId(id);
            return ResponseEntity.ok(service.salvar(dados));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }
}
