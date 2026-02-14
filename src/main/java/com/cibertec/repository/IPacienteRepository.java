package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Paciente;
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

}
