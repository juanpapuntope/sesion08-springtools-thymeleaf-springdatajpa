package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.model.Tipo;
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
	        
	        if(user != null && user.getTipo().getIdTipo() == 1) {//1:administrativo
	            return "menu";
	        } else {
	            return "bienvenido"; 
	        }
	        
	        
	    } catch (RuntimeException e) {
	        model.addAttribute("mensajeError", e.getMessage());
	        return "login";
	    }
	}
	
	// Mostrar formulario
	@GetMapping("/registrar")
	public String nuevoUsuario(Model model){

	    Usuario usuario = new Usuario();
	    usuario.setTipo(new Tipo()); // 🔥 IMPORTANTE

	    model.addAttribute("usuario", usuario);
	    model.addAttribute("tipos", tipoService.listar());

	    return "registrar";
	}


    // Guardar
    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario, Model model){      
        
        try {
        	usuarioService.guardar(usuario);
            model.addAttribute("mensajeOk", "Usuario registrado correctamente");
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al registrar el usuario");
        }
        return "registrar";
    }
    
    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        // Aquí podrías validar que hay una sesión activa
        return "menu";
    }
    
    //ESTO
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "listar";
    }
    //ESTO
    
    
    //METODO PARA EDITAR 
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {

        Usuario usuario = usuarioService.buscarPorId(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipos", tipoService.listar());

        return "registrar";
    }

    
}