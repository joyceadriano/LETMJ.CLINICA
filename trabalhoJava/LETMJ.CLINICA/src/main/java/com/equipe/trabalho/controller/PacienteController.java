package com.equipe.trabalho.controller;

import com.equipe.trabalho.model.Paciente;
import com.equipe.trabalho.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", service.listar());
        return "pacientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Paciente paciente) {
        service.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", service.buscar(id).get());
        return "pacientes/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/pacientes";
    }
}