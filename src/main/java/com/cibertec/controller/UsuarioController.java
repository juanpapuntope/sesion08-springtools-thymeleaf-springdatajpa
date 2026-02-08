package com.cibertec.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cibertec.model.Tipo;
import com.cibertec.model.Usuario;
import com.cibertec.repository.TipoRepository;
import com.cibertec.repository.UsuarioRepository;
import com.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final TipoRepository tipoRepository;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioRepository usuarioRepository,
                             TipoRepository tipoRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.tipoRepository = tipoRepository;
    }

    // Mostrar formulario
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con código: " + id));

        List<Tipo> tipos = tipoRepository.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipos", tipos);
        return "usuarios/editar"; // templates/usuarios/editar.html
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id,
                         @ModelAttribute Usuario usuarioForm) {
        usuarioService.editar(id, usuarioForm);
        return "redirect:/usuarios/listado"; // cambia a tu ruta real
    }
}
