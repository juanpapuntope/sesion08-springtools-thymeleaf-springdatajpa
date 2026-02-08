package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.model.Usuario;
import com.cibertec.service.TipoService;
import com.cibertec.service.UsuarioService;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;
    private final TipoService tipoService;

    public LoginController(UsuarioService usuarioService, TipoService tipoService) {
        this.usuarioService = usuarioService;
        this.tipoService = tipoService;
    }

    // --- SECCIÓN DE LOGIN ---
    
    @GetMapping("/")
    public String inicio() {
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            Usuario user = usuarioService.login(username, password);
            model.addAttribute("nombres", user.getNomUsua());
            model.addAttribute("apellidos", user.getApeUsua());

            // Si el ID del tipo es 1 (Administrativo), va al menú principal
            if (user.getTipo() != null && user.getTipo().getIdTipo() == 1) {
                return "menu";
            } else {
                return "bienvenido";
            }
        } catch (RuntimeException e) {
            model.addAttribute("mensajeError", e.getMessage());
            return "login";
        }
    }

    // --- SECCIÓN DE REGISTRO ---

    @GetMapping("/registrar")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", tipoService.listar());
        return "registrar";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.guardar(usuario);
            model.addAttribute("mensajeOk", "Usuario procesado correctamente");
            // Después de guardar, es mejor listar para ver el cambio
            return "redirect:/listar"; 
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al procesar el usuario");
            model.addAttribute("tipos", tipoService.listar());
            return "registrar";
        }
    }

    // --- SECCIÓN DE MANTENIMIENTO (LISTAR Y EDITAR) ---

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.listarTodos());
        return "listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("tipos", tipoService.listar());
            return "editar";
        } catch (Exception e) {
            return "redirect:/listar";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {
        try {
            // save() en JPA hace update automáticamente si detecta el ID (codUsua)
            usuarioService.guardar(usuario); 
            return "redirect:/listar";
        } catch (Exception e) {
            return "redirect:/listar?error";
        }
    }

    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        return "menu";
    }
}