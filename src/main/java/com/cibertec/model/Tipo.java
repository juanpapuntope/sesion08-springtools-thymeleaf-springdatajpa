package com.cibertec.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tipos")
public class Tipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo")  // ← IMPORTANTE: debe ser "idtipo" no "id_tipo"
    private Integer idTipo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    // Constructor vacío (OBLIGATORIO para JPA)
    public Tipo() {
    }
    
    // Getters y Setters
    public Integer getIdTipo() {
        return idTipo;
    }
    
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}