/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.servicio;

import ec.com.espe.arqui.dao.EmpleadoDAO;
import ec.com.espe.arqui.exception.ValidacionException;
import ec.com.espe.arqui.modelo.Empleado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author rdne1
 */
@LocalBean
@Stateless
public class EmpleadoServicio {
    @EJB
    private EmpleadoDAO empleadoDAO;
    
    public Empleado buscarEmpleadoPorUsuario(String usuario) {
        List<Empleado> empleados;
        Empleado empleado;
        Empleado empleadoTmp = new Empleado();
        empleadoTmp.setUsuario(usuario);
        empleados = this.empleadoDAO.find(empleadoTmp);
        if (empleados.size() == 1) {
            empleado = empleados.get(0);
        } else {
            empleado = null;
        }

        return empleado;
    }

    public List<Empleado> obtenerTodas() {
        return this.empleadoDAO.findAll();
    }

    public Empleado obtenerPorID(String codigoEmpleado) {
        return this.empleadoDAO.findById(codigoEmpleado, false);
    }

    public void crearEmpleado(Empleado empleado) throws ValidacionException {

        this.empleadoDAO.insert(empleado);
        this.empleadoDAO.flush();
    }

    public void actualiarEmpleado(Empleado empleado) {
        this.empleadoDAO.update(empleado);
    }

    public void eliminarEmpleado(String codigoEmpleado) {
        try {
            Empleado empleadoTmp = this.obtenerPorID(codigoEmpleado);
            this.empleadoDAO.remove(empleadoTmp);
        } catch (Exception e) {
            throw new ValidacionException("El empleado " + codigoEmpleado + " esta asociada otra tabla");
        }
    }
}
