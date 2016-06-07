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
@Table(name = "CARGO")
public class Cargo implements Serializable{
    @Id
    @Column(name = "CODIGO_CARGO", nullable = false)
    private String codigo;
    
    @Column(name = "CODIGO_SEDE", nullable = false)
    private String codigoSede;
    
    @JoinColumn(name = "CODIGO_SEDE", referencedColumnName = "CODIGO_SEDE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SedeDepartamento sede;
    
    @Column(name = "DESCRIPCION_CARGO", nullable = false)
    private String descripcion;

    public Cargo() {
    }

    public Cargo(String codigo, String codigoSede, String descripcion) {
        this.codigo = codigo;
        this.codigoSede = codigoSede;
        this.descripcion = descripcion;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigo);
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cargo{" + "codigo=" + codigo + ", codigoSede=" + codigoSede + ", sede=" + sede + ", descripcion=" + descripcion + '}';
    }
    
    
}
