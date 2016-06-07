/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rdne1
 */
@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable{
    @Id
    @Column(name = "CODIGO_DEPARTAMENTO", nullable = false)
    private String codigo;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "DIRECTOR", nullable = false)
    private String nombreDirector;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_INICIO", nullable = false)
    private Date fechaInicio;

    public Departamento() {
    }

    public Departamento(String codigo, String nombre, String nombreDirector, Date fechaInicio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreDirector = nombreDirector;
        this.fechaInicio = fechaInicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Departamento{" + "codigo=" + codigo + ", nombre=" + nombre + ", nombreDirector=" + nombreDirector + ", fechaInicio=" + fechaInicio + '}';
    }
    
    
}
