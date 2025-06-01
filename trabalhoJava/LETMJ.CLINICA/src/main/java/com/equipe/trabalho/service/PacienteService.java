package com.equipe.trabalho.service;

import com.equipe.trabalho.model.Paciente;
import com.equipe.trabalho.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repo;

    public List<Paciente> listar() { return repo.findAll(); }
    public Paciente salvar(Paciente p) { return repo.save(p); }
    public void excluir(Long id) { repo.deleteById(id); }
    public Optional<Paciente> buscar(Long id) { return repo.findById(id); }
}