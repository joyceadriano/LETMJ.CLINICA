package com.equipe.trabalho.service;



import com.equipe.trabalho.model.Medico;
import com.equipe.trabalho.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public List<Medico> listar() {
        return repository.findAll();
    }

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Medico> buscar(Long id) {
        return repository.findById(id);
    }
}