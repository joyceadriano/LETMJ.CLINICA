package com.equipe.joice.trabalho.repository;

import com.equipe.joice.trabalho.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}

