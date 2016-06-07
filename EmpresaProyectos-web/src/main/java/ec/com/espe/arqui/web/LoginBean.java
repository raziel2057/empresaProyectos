/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.web;


import ec.com.espe.arqui.modelo.Empleado;
import ec.com.espe.arqui.modelo.Proyecto;
import ec.com.espe.arqui.servicio.EmpleadoServicio;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RAUL
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {

    @EJB
    private EmpleadoServicio empleadoServicio;
    

    private Empleado empleado;
    private String username;
    private String password;
    private boolean loggedIn = false;
    private boolean enNuuevoCliente = false;
    
    private Proyecto proyectoSelected;

    public Proyecto getProyectoSelected() {
        return proyectoSelected;
    }

    public void setProyectoSelected(Proyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean estaLogeado() {
        return loggedIn;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

   

    public boolean isEnNuuevoCliente() {
        return enNuuevoCliente;
    }

    public void setEnNuuevoCliente(boolean enNuuevoCliente) {
        this.enNuuevoCliente = enNuuevoCliente;
    }

    /*public String login() {
     //RequestContext context = RequestContext.getCurrentInstance();
     FacesContext context = FacesContext.getCurrentInstance(); 
     FacesMessage message = null;
        
     if(username != null  && password != null) {
     this.cliente = clienteServicio.buscarClientePorUsuario(username);
     if(this.cliente!=null && this.cliente.getUsuario().equals(username)&& this.cliente.getClave().equals(password))
     {
     //loggedIn = true;
     //message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
     ((HttpServletRequest)context.getExternalContext().getRequest()).getSession().setAttribute("cliente", this.cliente);
     return "empresaCrud";
     }
     else {
     return "prueba";
     //loggedIn = false;
     /// message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
     }
            
     } 
        
     /*FacesContext.getCurrentInstance().addMessage(null, message);
     context.addCallbackParam("estaLogeado", loggedIn);
     if (loggedIn)
     context.addCallbackParam("view", "faces/empresaCrud.xhtml");
     }*/
    public void registroCliente() {
        //FacesContext context = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();

        enNuuevoCliente = true;

        //context.addCallbackParam("view", "faces/nuevoCliente.xhtml.xhtml");

    }

    public void login() {
        enNuuevoCliente=false;
        //FacesContext context = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        //String direccion="null";
        this.empleado = empleadoServicio.buscarEmpleadoPorUsuario(username);
        if (this.empleado != null && this.empleado.getUsuario().equals(username) && this.empleado.getClave().equals(DigestUtils.md5Hex(password))) {
            //((HttpServletRequest) context.getExternalContext().getRequest()).getSession().setAttribute("usuario", this.cliente);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bienvenido"));
            //direccion= "inicio?faces-redirect=true";
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", username);

        } else {
            //FacesContext.getCurrentInstance().addMessage(null, 
            //      new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario y/o password incorrectos"));
            //direccion= "index";
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", loggedIn);
        if (loggedIn) {

            if (this.empleado.getTipo().equals("A")) {
                context.addCallbackParam("view", "faces/inicio.xhtml");
            } else {
                context.addCallbackParam("view", "faces/inicio.xhtml");
            }
        }

        //return direccion;
    }

    public void logout() {
        enNuuevoCliente=false;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();

        loggedIn = false;
    }

}
