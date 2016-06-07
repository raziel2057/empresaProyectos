/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rdne1
 */
@Entity
@Table(name = "PROYECTO")
public class Proyecto implements Serializable{
    @Id
    @Column(name = "CODIGO_PROYECTO", nullable = false)
    private String codigo;
    
    @Column(name = "CODIGO_SEDE", nullable = false)
    private String codigoSede;
    
    @JoinColumn(name = "CODIGO_SEDE", referencedColumnName = "CODIGO_SEDE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SedeDepartamento sede;
    
    @Column(name = "NOMBRE", nullable = false)
    private String descripcion;
    
    @Column(name = "ESTADO", nullable = false)
    private String estado;

    public Proyecto() {
    }

    public Proyecto(String codigo, String codigoSede, String descripcion, String estado) {
        this.codigo = codigo;
        this.codigoSede = codigoSede;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public SedeDepartamento getSede() {
        return sede;
    }

    public void setSede(SedeDepartamento sede) {
        this.sede = sede;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
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
        final Proyecto other = (Proyecto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "codigo=" + codigo + ", codigoSede=" + codigoSede + ", sede=" + sede + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
    
    
}
