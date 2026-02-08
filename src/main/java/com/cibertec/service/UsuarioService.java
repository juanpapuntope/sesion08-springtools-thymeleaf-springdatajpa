package com.cibertec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cibertec.model.Usuario;
import com.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioService {
	

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario login(String username, String password){
		
		Usuario usuario = usuarioRepository
		        .findByUsrUsuaAndClaUsua(username, password)
		        .orElseThrow(() -> new RuntimeException("Credenciales son incorrectas. Intente de nuevo."));
		
	    if(usuario.getTipo() == null) {
	        throw new RuntimeException("El usuario no tiene un tipo asignado.");
	    }
		return usuario;
	}
	public List<Usuario> listarTodos() {
	    return usuarioRepository.findAll();
	}
	
	public void guardar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
