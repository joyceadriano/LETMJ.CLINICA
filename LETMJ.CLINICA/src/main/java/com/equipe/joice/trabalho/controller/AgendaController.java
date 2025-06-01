package com.equipe.joice.trabalho.controller;

import com.equipe.joice.trabalho.model.Agenda;
import com.equipe.joice.trabalho.service.AgendaService;
import com.equipe.joice.trabalho.service.MedicoService;
import com.equipe.joice.trabalho.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendas", agendaService.listar());
        return "agendas/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        return "agendas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Agenda agenda) {
        agenda.setPaciente(pacienteService.buscar(agenda.getPaciente().getId()).orElse(null));
        agenda.setMedico(medicoService.buscar(agenda.getMedico().getId()).orElse(null));
        agendaService.salvar(agenda);
        return "redirect:/agendas";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var agendaOpt = agendaService.buscar(id);
        if (agendaOpt.isEmpty()) {
            return "redirect:/agendas";
        }
        model.addAttribute("agenda", agendaOpt.get());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        return "agendas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        agendaService.excluir(id);
        return "redirect:/agendas";
    }
}
