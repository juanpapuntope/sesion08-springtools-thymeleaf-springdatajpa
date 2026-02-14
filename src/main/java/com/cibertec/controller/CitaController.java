package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.model.Cita;
import com.cibertec.model.Medico;
import com.cibertec.model.Paciente;
import com.cibertec.service.CitaService;
import com.cibertec.service.MedicoService;
import com.cibertec.service.PacienteService;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

  
    public CitaController(CitaService citaService, PacienteService pacienteService, MedicoService medicoService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

   
    @GetMapping("/consulta")
    public String listarCitas(Model model) {
        model.addAttribute("listaCitas", citaService.listarTodos());
        return "consultaCita";
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable("id") int id, Model model) {
        Cita cita = citaService.buscarPorId(id);
        
    
        if (cita.getPaciente() == null) cita.setPaciente(new Paciente());
        if (cita.getMedico() == null) cita.setMedico(new Medico());
        
        model.addAttribute("cita", cita);
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("medicos", medicoService.listarTodos());
        
        return "mantenimientoCita";
    }
   
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Cita cita, RedirectAttributes attribute) {
        try {
            citaService.guardar(cita);
            
            attribute.addFlashAttribute("mensaje", "Cita actualizada correctamente en Clínica VIDA.");
            attribute.addFlashAttribute("clase", "success");
        } catch (Exception e) {
           
            attribute.addFlashAttribute("mensaje", "Hubo un error al intentar actualizar la cita.");
            attribute.addFlashAttribute("clase", "danger");
        }
        return "redirect:/citas/consulta";
    }
}