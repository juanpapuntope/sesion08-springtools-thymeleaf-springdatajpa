package com.cibertec.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Medico;

public interface IMedicoRepository extends JpaRepository<Medico, Integer> {

}
