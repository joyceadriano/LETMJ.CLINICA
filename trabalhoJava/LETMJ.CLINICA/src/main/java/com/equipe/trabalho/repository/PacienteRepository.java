package com.equipe.trabalho.repository;

import com.equipe.trabalho.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}

