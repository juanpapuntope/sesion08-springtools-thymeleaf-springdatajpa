package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/")
	public String inicio() { return "login"; }
	
	@GetMapping("/login")
    public String mostrarLogin() { return "login"; }
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
	    try {
	        Usuario user = usuarioService.login(username, password);
	        model.addAttribute("nombres", user.getNomUsua());
	        model.addAttribute("apellidos", user.getApeUsua());
	        return (user.getTipo().getIdTipo() == 1) ? "menu" : "bienvenido";
	    } catch (RuntimeException e) {
	        model.addAttribute("mensajeError", e.getMessage());
	        return "login";
	    }
	}
	
    @GetMapping("/menu")
    public String mostrarMenu() { return "menu"; }

    // --- MÉTODOS QUE AGREGUE---

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "listar";
    }
    
    @GetMapping("/registrar")
    public String nuevoUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", tipoService.listar());
        return "registrar";
    }
    
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        model.addAttribute("Usuario", usuarioService.buscarPorId(id));
        model.addAttribute("tipos", tipoService.listar());
        return "registrar";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model){      
        try {
            usuarioService.guardar(usuario);
            return "redirect:/listar";
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error: " + e.getMessage());
            model.addAttribute("tipos", tipoService.listar());
            return "registrar";
        }
    }
    
}