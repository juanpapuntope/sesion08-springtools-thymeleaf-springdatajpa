package com.cibertec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cibertec.model.*;

import com.cibertec.repository.IPacienteRepository;

@Service
	public class PacienteService {

	    private final IPacienteRepository pacienteRepository;

	    public PacienteService(IPacienteRepository pacienteRepository) {
	        this.pacienteRepository = pacienteRepository;
	    }

	    public List<Paciente> listarTodos() {
	        return pacienteRepository.findAll();
	    }
	}
	

