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
@Table(name = "SEDE_DEPARTAMENTO")
public class SedeDepartamento implements Serializable{
    @Id
    @Column(name = "CODIGO_SEDE", nullable = false)
    private String codigo;
    
    @Column(name = "CODIGO_LUGAR", nullable = false)
    private String codigoLugar;
    
    @JoinColumn(name = "CODIGO_LUGAR", referencedColumnName = "CODIGO_LUGAR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lugar lugar;
    
    @Column(name = "CODIGO_DEPARTAMENTO", nullable = false)
    private String codigoDepartamento;
    
    @JoinColumn(name = "CODIGO_DEPARTAMENTO", referencedColumnName = "CODIGO_DEPARTAMENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departamento departamento;
    
    @Column(name = "DIRECCION_SEDE", nullable = false)
    private String direccion;

    public SedeDepartamento() {
    }

    public SedeDepartamento(String codigo, String codigoLugar, String codigoDepartamento, String direccion) {
        this.codigo = codigo;
        this.codigoLugar = codigoLugar;
        this.codigoDepartamento = codigoDepartamento;
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoLugar() {
        return codigoLugar;
    }

    public void setCodigoLugar(String codigoLugar) {
        this.codigoLugar = codigoLugar;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
        final SedeDepartamento other = (SedeDepartamento) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SedeDepartamento{" + "codigo=" + codigo + ", codigoLugar=" + codigoLugar + ", lugar=" + lugar + ", codigoDepartamento=" + codigoDepartamento + ", departamento=" + departamento + ", direccion=" + direccion + '}';
    }
    
    
}
