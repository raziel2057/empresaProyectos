/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rdne1
 */
public class AsignacionProyectoPK implements Serializable{
    protected String codigoProyecto;
    protected String codigoEmpleado;

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.codigoProyecto);
        hash = 67 * hash + Objects.hashCode(this.codigoEmpleado);
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
        final AsignacionProyectoPK other = (AsignacionProyectoPK) obj;
        if (!Objects.equals(this.codigoProyecto, other.codigoProyecto)) {
            return false;
        }
        if (!Objects.equals(this.codigoEmpleado, other.codigoEmpleado)) {
            return false;
        }
        return true;
    }
    
    
}
