package com.iudigital.mercafacil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoGrupo;

    private String descripcion;

    public Long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(Long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}