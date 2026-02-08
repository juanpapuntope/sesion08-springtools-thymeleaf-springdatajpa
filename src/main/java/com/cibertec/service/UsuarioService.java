package com.cibertec.service;

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
		
		// Validar que tenga tipo asignado
	    if(usuario.getTipo() == null) {
	        throw new RuntimeException("El usuario no tiene un tipo asignado.");
	    }
		return usuario;
	}
	
	public void guardar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
