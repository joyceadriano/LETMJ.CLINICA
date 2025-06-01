package com.equipe.trabalho.service;

import com.equipe.trabalho.model.Agenda;
import com.equipe.trabalho.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public List<Agenda> listar() {
        return repository.findAll();
    }

    public Agenda salvar(Agenda agenda) {
        return repository.save(agenda);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Agenda> buscar(Long id) {
        return repository.findById(id);
    }
}