package com.cibertec.service;



import java.util.List;
import org.springframework.stereotype.Service;
import com.cibertec.model.Medico;
import com.cibertec.repository.IMedicoRepository;

@Service
public class MedicoService {

    private final IMedicoRepository medicoRepository;

    public MedicoService(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
}