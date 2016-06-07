/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.web;

import ec.com.espe.arqui.modelo.Cargo;
import ec.com.espe.arqui.modelo.Empleado;
import ec.com.espe.arqui.servicio.CargoServicio;
import ec.com.espe.arqui.servicio.EmpleadoServicio;
import ec.com.espe.arqui.util.RandomString;
import ec.com.espe.arqui.util.ValidacionesInputBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author RAUL
 */
@ViewScoped
@ManagedBean
public class EmpleadoBean extends BaseBean implements Serializable {

    @EJB
    private EmpleadoServicio empleadoServicio;
    private List<Empleado> empleados;
    private Empleado empleado;
    private Empleado empleadoSelected;

    @EJB
    private CargoServicio cargoServicio;
    private List<Cargo> cargos;

    ValidacionesInputBean validacion = new ValidacionesInputBean();

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;

    private Empleado empleadoSesion;
    private String viejaClave;
    private String nuevaClave; //para cuando se actualizan los datos del empleado
    //Para el nuevocliente
    private boolean enRegistro;

    private Date fechaMaxima;

    public boolean isEnRegistro() {
        return enRegistro;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public void setEnRegistro(boolean enRegistro) {
        this.enRegistro = enRegistro;
    }

    public LoginBean getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginBean datosLogin) {
        this.datosLogin = datosLogin;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(Empleado empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    public Empleado getEmpleadoSesion() {
        return empleadoSesion;
    }

    public void setEmpleadoSesion(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getViejaClave() {
        return viejaClave;
    }

    public void setViejaClave(String viejaClave) {
        this.viejaClave = viejaClave;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    @PostConstruct
    public void inicializar() {
        empleados = empleadoServicio.obtenerTodas();
        enRegistro = false;

        this.cargos = this.cargoServicio.obtenerTodas();

        this.fechaMaxima = new Date();
        this.fechaMaxima.setYear(this.fechaMaxima.getYear() - 16);

        if (this.getDatosLogin().isEnNuuevoCliente()) {

        } else {
            //Para tener los datos del empleado de la sesion.
            empleadoSesion = new Empleado();
            try {
                BeanUtils.copyProperties(this.empleadoSesion, this.getDatosLogin().getEmpleado());

            } catch (Exception e) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
            }
        }
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.empleado = new Empleado();
        this.empleado.setCodigo("");
        this.empleado.setCodigoCargo(this.cargos.get(0).getCodigo());
    }

    @Override
    public void modificar() {

        super.modificar();
        this.empleado = new Empleado();
        try {
            BeanUtils.copyProperties(this.empleado, this.empleadoSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }
    }

    @Override
    public void eliminar() {
        super.eliminar();
        this.empleado = new Empleado();
        try {
            BeanUtils.copyProperties(this.empleado, this.empleadoSelected);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }
    }

    public void aceptar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (super.isEnNuevo()) {

            try {
                String claveEncriptada = DigestUtils.md5Hex(this.empleado.getClave());
                this.empleado.setClave(claveEncriptada);
                this.empleadoServicio.crearEmpleado(this.empleado);
                //this.empleados.add(0, this.empleado);
                this.inicializar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el empleado: " + this.empleado.getNombre(), null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }

        } else if (super.isEnModificar()) {

            try {

                String claveEncriptada = DigestUtils.md5Hex(this.empleado.getClave());
                this.empleado.setClave(claveEncriptada);
                this.empleadoServicio.actualiarEmpleado(this.empleado);
                BeanUtils.copyProperties(this.empleadoSelected, this.empleado);
                this.inicializar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se actualizo el empleado: " + this.empleado.getNombre(), null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }

        } else if (super.isEnEliminar()) {
            try {
                this.empleadoServicio.eliminarEmpleado(this.empleado.getCodigo());
                //this.empleados.remove(this.empleado);
                this.inicializar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino el empleado: " + this.empleado.getNombre(), null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        }
        this.cancelar();
    }

    public void resgistroCliente() {
        FacesContext context = FacesContext.getCurrentInstance();

        String nombre = empleado.getNombre();
        String resultado1 = validacion.validateTextoSoloLetras(nombre, 50);
        try {
            String resultado2 = validacion.validateNumeroEntero(empleado.getNumeroSegSocial(), 13);
        } catch (Exception e) {

        }
        String resultado3 = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getDireccion(), 100);
        try{
        String resultado4 = validacion.validateNumeroDecimal(empleado.getSalario().toString(), 8);
        } catch (Exception e){
            
        }
        
        String resultado5 = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getUsuario(), 50);
        String resultado6 = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getClave(), 32);

        if (super.isEnNuevo()) {
            /*
             if (!"se".equals(resultado1)) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en nombre", resultado1));
             } else if (!resultado2.equals("se")) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en identificación", resultado2));
             } else if (!resultado3.equals("se")) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en dirección", resultado3));
             } else if (!resultado4.equals("se")) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en salario", resultado4));
             } else if (!resultado5.equals("se")) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en usuario", resultado5));
             } else if (!resultado6.equals("se")) {
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", resultado6));
             } else*/ if (empleadoServicio.buscarEmpleadoPorUsuario(empleado.getUsuario()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Nombre de usuario ya utilizado ", null));
            } else {
                try {
                    RandomString randomString = new RandomString(5); //Clave de 5 caracteres
                    String claveTemporal = randomString.nextString();
                    String claveEncriptada = DigestUtils.md5Hex(claveTemporal);
                    this.empleado.setClave(claveEncriptada);
                    this.empleado.setTipo("A");
                    this.empleadoServicio.crearEmpleado(this.empleado);

                    //Enviar el correo de comprobacion
                   /* String subject = "Activicación de cuenta SAIV";

                     String cuerpo = "<h2>Activicación de cuenta SAIV</h2>"
                     + "<h4>Felicidades usuario " + this.empleado.getUsuario() + ", su cuenta ha sido activada. Se le ha asignado la siguiente clave automaticamente: " + claveTemporal + " </h4>"
                     + "<h4>Por favor ingrese con el usuario que registró  y esta clave. Posteriormente diríjase a la pestaña de "
                     + "Información personal y cambie su clave</h4>";

                     correo.EnviarCorreoSinArchivoAdjunto(this.empleado.getCorreoElectronico(), subject, cuerpo);*/
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el cliente: " + this.empleado.getNombre() + "Clave temporal: " + claveTemporal, null));
                    enRegistro = true;
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
                }
            }

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No esta en nuevo", null));
        }

    }

    public void cancelar() {
        super.reset();
        this.empleado = null;
        this.empleadoSelected = null;

    }

    public void confirmarClaveModificarDatosClienteSesion() {

        String claveEncriptada = DigestUtils.md5Hex(this.viejaClave.trim());
        if (claveEncriptada.equals(this.empleadoSesion.getClave())) {
            nuevaClave = viejaClave;
            modificarDatosClienteSesion();

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Las claves no coinciden "));
            this.cerrar();
        }
    }

    public void cerrar() {
        this.viejaClave = "";
        super.reset();
        this.empleado = null;
    }

    public void modificarDatosClienteSesion() {

        super.modificar();

        this.empleado = new Empleado();
        try {
            BeanUtils.copyProperties(this.empleado, this.empleadoSesion);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void aceptarDatosClienteSesion() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (super.isEnModificar()) {

            try {
                String claveEncriptada = DigestUtils.md5Hex(nuevaClave);
                this.empleado.setClave(claveEncriptada);
                this.empleadoServicio.actualiarEmpleado(this.empleado);
                BeanUtils.copyProperties(this.empleadoSesion, this.empleado);
                viejaClave = nuevaClave;
                nuevaClave = "";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Su informacion ha sido actualizada", null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }

        }
        this.cerrar();
    }

    public void validateNombre() {

        String resultado = validacion.validateTextoSoloLetras(empleado.getNombre(), 50);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en nombre", resultado));
        }
    }

    public void validateIdentificacion() {

        String resultado = validacion.validateNumeroEntero(empleado.getNumeroSegSocial(), 13);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en identificación", resultado));
        } else if (!validacion.validadorDeCedula(empleado.getNumeroSegSocial())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en identificación", "Cédula no válida"));

        }
    }

    public void validateDireccion() {

        String resultado = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getDireccion(), 100);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en dirección", resultado));
        }
    }

    public void validateSalario() {

        String resultado = validacion.validateNumeroDecimal(empleado.getSalario().toString(), 8);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en salario", resultado));
        }
    }

    public void validateUsuario() {

        String resultado = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getUsuario(), 50);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", resultado));
        } else if (empleadoServicio.buscarEmpleadoPorUsuario(empleado.getUsuario()) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre de usuario ya esta registrado"));
        }
    }

    public void validateClave() {

        String resultado = validacion.validateTextoLetrasNumerosCaracteresEspeciales(empleado.getClave(), 32);

        if (!resultado.equals("se")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en clave", resultado));
        }
    }

}
