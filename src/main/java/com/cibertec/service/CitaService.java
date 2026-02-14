package com.cibertec.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cibertec.model.Cita;
import com.cibertec.repository.ICitaRepository;

@Service
public class CitaService {

    private final ICitaRepository citaRepository;

    public CitaService(ICitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listarTodos() {
        return citaRepository.findAll();
    }

    public void guardar(Cita cita) {
        citaRepository.save(cita);
    }

    public Cita buscarPorId(int id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }
    
    public void eliminar(int id) {
        citaRepository.deleteById(id);;
    }
}
