package com.cibertec.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usua")
    private int codUsua;

    @Column(name = "nom_usua")
    private String nomUsua;
    @Column(name = "ape_usua")
    private String apeUsua;

    @Column(name = "usr_usua")//@Column(nullable = false, unique = true)
    private String usrUsua;

    @Column(name = "cla_usua")
    private String claUsua;

    @Column(name = "fna_usua")
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fnaUsua;  // ← Cambiar a LocalDate
    
    @ManyToOne
    @JoinColumn(name = "idtipo")
    private Tipo tipo;   // entidad Tipo que corresponde a tb_tipos
    
    
    @Column(name = "est_usua")
    private int estUsua;

    // Constructor vacío (OBLIGATORIO para JPA)
    public Usuario() {
    }

    // Getters y Setters
    public int getCodUsua() {
        return codUsua;
    }

    public void setCodUsua(int codUsua) {
        this.codUsua = codUsua;
    }

    public String getNomUsua() {
        return nomUsua;
    }

    public void setNomUsua(String nomUsua) {
        this.nomUsua = nomUsua;
    }

    public String getApeUsua() {
        return apeUsua;
    }

    public void setApeUsua(String apeUsua) {
        this.apeUsua = apeUsua;
    }

    public String getUsrUsua() {
        return usrUsua;
    }

    public void setUsrUsua(String usrUsua) {
        this.usrUsua = usrUsua;
    }

    public String getClaUsua() {
        return claUsua;
    }

    public void setClaUsua(String claUsua) {
        this.claUsua = claUsua;
    }

    public LocalDate getFnaUsua() {
		return fnaUsua;
	}

	public void setFnaUsua(LocalDate fnaUsua) {
		this.fnaUsua = fnaUsua;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
    
    public int getEstUsua() {
        return estUsua;
    }


	public void setEstUsua(int estUsua) {
        this.estUsua = estUsua;
    }

}
