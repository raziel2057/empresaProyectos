/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.web;

import ec.com.espe.arqui.modelo.Proyecto;
import ec.com.espe.arqui.modelo.SedeDepartamento;
import ec.com.espe.arqui.servicio.ProyectoServicio;
import ec.com.espe.arqui.servicio.SedeServicio;
import ec.com.espe.arqui.util.ValidacionesInputBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author rdne1
 */
@ViewScoped
@ManagedBean
public class ProyectoBean extends BaseBean implements Serializable {
    
    
    @EJB
    private ProyectoServicio proyectoServicio;
    private List<Proyecto> proyectos;
    private Proyecto proyecto;
    private Proyecto proyectoSelected;
    
    @EJB
    private SedeServicio sedeServicio;
    private List<SedeDepartamento> sedes;

    ValidacionesInputBean validacion = new ValidacionesInputBean();

    private boolean enRegistro;


    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;

    public LoginBean getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginBean datosLogin) {
        this.datosLogin = datosLogin;
    }
    
    

    public boolean isEnRegistro() {
        return enRegistro;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyectoSelected() {
        return proyectoSelected;
    }

    public void setProyectoSelected(Proyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    public SedeServicio getSedeServicio() {
        return sedeServicio;
    }

    public void setSedeServicio(SedeServicio sedeServicio) {
        this.sedeServicio = sedeServicio;
    }

    public List<SedeDepartamento> getSedes() {
        return sedes;
    }

    public void setSedes(List<SedeDepartamento> sedes) {
        this.sedes = sedes;
    }

    public ValidacionesInputBean getValidacion() {
        return validacion;
    }

    public void setValidacion(ValidacionesInputBean validacion) {
        this.validacion = validacion;
    }

    
    
    

    @PostConstruct
    public void inicializar() {
        proyectos = proyectoServicio.obtenerTodas();
        enRegistro = false;
        
        this.sedes = this.sedeServicio.obtenerTodas();
        
        

    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.proyecto = new Proyecto();
        this.proyecto.setCodigo("");
        
    }

    @Override
    public void modificar() {

        super.modificar();
        this.proyecto = new Proyecto();
        try {
            BeanUtils.copyProperties(this.proyecto, this.proyectoSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }
    }

    @Override
    public void eliminar() {
        super.eliminar();
        this.proyecto = new Proyecto();
        try {
            BeanUtils.copyProperties(this.proyecto, this.proyectoSelected);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }
    }

    public void aceptar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (super.isEnNuevo()) {
            
                try {
                   
                    this.proyectoServicio.crearProyecto(this.proyecto);
                    //this.proyectos.add(0, this.proyecto);
                    this.inicializar();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el proyecto: " + this.proyecto.getDescripcion(), null));
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
                }
            
        } else if (super.isEnModificar()) {

            
                try {

                   
                    this.proyectoServicio.actualiarProyecto(this.proyecto);
                    BeanUtils.copyProperties(this.proyectoSelected, this.proyecto);
                    this.inicializar();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se actualizo el proyecto: " + this.proyecto.getDescripcion(), null));
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
                }
            

        } else if (super.isEnEliminar()) {
            try {
                this.proyectoServicio.eliminarProyecto(this.proyecto.getCodigo());
                //this.proyectos.remove(this.proyecto);
                this.inicializar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino el proyecto: " + this.proyecto.getDescripcion(), null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        }
        this.cancelar();
    }

    

    public void cancelar() {
        super.reset();
        this.proyecto = null;
        this.proyectoSelected = null;

    }
    
    public void asignarProyecto()
    {
        this.getDatosLogin().setProyectoSelected(this.proyectoSelected);
    }
    
}
