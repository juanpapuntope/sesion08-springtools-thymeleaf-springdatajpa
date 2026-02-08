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
		
		// Validar que tenga tipo asignado
	    if(usuario.getTipo() == null) {
	        throw new RuntimeException("El usuario no tiene un tipo asignado.");
	    }
		return usuario;
	}
	
	public void guardar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	 public List<Usuario> listadoUsuarios() {
	        return usuarioRepository.findAll();
	    }
	 
	 public Usuario editar(int codUsua, Usuario datos) {

		    Usuario existente = usuarioRepository.findById(codUsua)
		        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con código: " + codUsua));

		    if (hasText(datos.getNomUsua())) existente.setNomUsua(datos.getNomUsua().trim());
		    if (hasText(datos.getApeUsua())) existente.setApeUsua(datos.getApeUsua().trim());

		    if (hasText(datos.getUsrUsua())) {
		        String nuevoUser = datos.getUsrUsua().trim();
		        if (usuarioRepository.existsByUsrUsuaAndCodUsuaNot(nuevoUser, codUsua)) {
		            throw new RuntimeException("El usuario '" + nuevoUser + "' ya está en uso.");
		        }
		        existente.setUsrUsua(nuevoUser);
		    }

		    if (hasText(datos.getClaUsua())) existente.setClaUsua(datos.getClaUsua());
		    if (datos.getFnaUsua() != null) existente.setFnaUsua(datos.getFnaUsua());
		    if (datos.getTipo() != null) existente.setTipo(datos.getTipo());

		    // OJO: int siempre tiene valor. Si tu request no manda estUsua, puede venir 0.
		    existente.setEstUsua(datos.getEstUsua());

		    if (existente.getTipo() == null) {
		        throw new RuntimeException("El usuario no puede quedar sin tipo asignado.");
		    }

		    return usuarioRepository.save(existente);
		}

		private boolean hasText(String s) {
		    return s != null && !s.trim().isEmpty();
		}

}
