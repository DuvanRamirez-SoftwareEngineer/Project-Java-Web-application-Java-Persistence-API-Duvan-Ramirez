package com.iudigital.mercafacil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "almacenes")
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoAlmacen;

    private String nombre;
    private String direccion;
    private String nombreGerente;

    public Long getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(Long codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }
}