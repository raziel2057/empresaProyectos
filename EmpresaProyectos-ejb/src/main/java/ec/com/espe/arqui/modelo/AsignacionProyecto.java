/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rdne1
 */
@Entity
@Table(name = "DETALLE_FACTURA")
@IdClass(AsignacionProyectoPK.class)
public class AsignacionProyecto implements Serializable{
    @Id
    @Column(name = "CODIGO_PROYECTO", nullable = false)
    private String codigoProyecto;
    
    @JoinColumn(name = "CODIGO_PROYECTO", referencedColumnName = "CODIGO_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    
    @Id
    @Column(name = "CODIGO_EMPLEADO", nullable = false)
    private String codigoEmpleado;
    
    @JoinColumn(name = "CODIGO_EMPLEADO", referencedColumnName = "CODIGO_EMPLEADO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    
    @Column(name = "HORAS_SEMANALES", nullable = false)
    private BigDecimal horasSemanales;
    
    @Column(name = "CARGO_EMPLEADO", nullable = false)
    private String cargoEmpleado;

    public AsignacionProyecto() {
    }

    public AsignacionProyecto(String codigoProyecto, String codigoEmpleado, BigDecimal horasSemanales, String cargoEmpleado) {
        this.codigoProyecto = codigoProyecto;
        this.codigoEmpleado = codigoEmpleado;
        this.horasSemanales = horasSemanales;
        this.cargoEmpleado = cargoEmpleado;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public BigDecimal getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(BigDecimal horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public String getCargoEmpleado() {
        return cargoEmpleado;
    }

    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigoProyecto);
        hash = 89 * hash + Objects.hashCode(this.codigoEmpleado);
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
        final AsignacionProyecto other = (AsignacionProyecto) obj;
        if (!Objects.equals(this.codigoProyecto, other.codigoProyecto)) {
            return false;
        }
        if (!Objects.equals(this.codigoEmpleado, other.codigoEmpleado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AsignacionProyecto{" + "codigoProyecto=" + codigoProyecto + ", proyecto=" + proyecto + ", codigoEmpleado=" + codigoEmpleado + ", empleado=" + empleado + ", horasSemanales=" + horasSemanales + ", cargoEmpleado=" + cargoEmpleado + '}';
    }
    
    
}
