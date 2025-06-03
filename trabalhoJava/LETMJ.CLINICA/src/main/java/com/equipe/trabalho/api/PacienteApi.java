package com.equipe.trabalho.api;

import com.equipe.trabalho.model.Paciente;
import com.equipe.trabalho.service.PacienteService;
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

@RestController
@RequestMapping("/api/pacientes")
public class PacienteApi {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.salvar(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> editar(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        return pacienteService.buscar(id)
                .map(pacienteExistente -> {
                    pacienteExistente.setNome(pacienteAtualizado.getNome());
                    pacienteExistente.setCpf(pacienteAtualizado.getCpf());
                    pacienteExistente.setDataNascimento(pacienteAtualizado.getDataNascimento());
                    pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
                    return ResponseEntity.ok(pacienteService.salvar(pacienteExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return pacienteService.buscar(id)
                .map(paciente -> {
                    pacienteService.excluir(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}