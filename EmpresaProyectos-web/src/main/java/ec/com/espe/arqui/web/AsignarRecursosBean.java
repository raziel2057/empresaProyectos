/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.web;

import ec.com.espe.arqui.modelo.DetalleRecurso;
import ec.com.espe.arqui.modelo.Proyecto;
import ec.com.espe.arqui.servicio.ProyectoServicio;
import ec.com.espe.arqui.webservices.DetalleFactura;
import ec.com.espe.arqui.webservices.Producto;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author rdne1
 */
@ViewScoped
@ManagedBean
public class AsignarRecursosBean {
    
     @EJB
    private ProyectoServicio proyectoServicio;
    
    private Proyecto proyecto;
    
    private List<DetalleRecurso> recursos;
    private DetalleRecurso recursoSelected;
    
    private Producto productoSelected;
    private List<Producto> productosConsulta;
    private String cadenaCOnsulta;
    
    private Boolean busqueda;
    
    private List<DetalleFactura> listaDetalleFactura;
    private Integer cantidad;
    private DetalleFactura detalleFacturaSelected;
    
    private String codigoCliente;
    
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;

    public DetalleFactura getDetalleFacturaSelected() {
        return detalleFacturaSelected;
    }

    public void setDetalleFacturaSelected(DetalleFactura detalleFacturaSelected) {
        this.detalleFacturaSelected = detalleFacturaSelected;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
    public List<DetalleFactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }
    
    

    public Producto getProductoSelected() {
        return productoSelected;
    }

    public void setProductoSelected(Producto productoSelected) {
        this.productoSelected = productoSelected;
    }
    
    
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LoginBean getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginBean datosLogin) {
        this.datosLogin = datosLogin;
    }

    public List<DetalleRecurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<DetalleRecurso> recursos) {
        this.recursos = recursos;
    }

    public DetalleRecurso getRecursoSelected() {
        return recursoSelected;
    }

    public void setRecursoSelected(DetalleRecurso recursoSelected) {
        this.recursoSelected = recursoSelected;
    }

    public List<Producto> getProductosConsulta() {
        return productosConsulta;
    }

    public void setProductosConsulta(List<Producto> productosConsulta) {
        this.productosConsulta = productosConsulta;
    }

    public String getCadenaCOnsulta() {
        return cadenaCOnsulta;
    }

    public void setCadenaCOnsulta(String cadenaCOnsulta) {
        this.cadenaCOnsulta = cadenaCOnsulta;
    }

    public Boolean getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Boolean busqueda) {
        this.busqueda = busqueda;
    }
    
    
    
    @PostConstruct
    public void inicializar(){
       
        try
        {
            //this.detalleFacturaSelected = new DetalleFactura();
            this.listaDetalleFactura = new ArrayList<>();
            this.codigoCliente="CLI0001";
            this.proyecto= new Proyecto();
            BeanUtils.copyProperties(this.proyecto, this.getDatosLogin().getProyectoSelected());
            
            this.recursos = this.proyectoServicio.buscarRecursosPorProyecto(this.proyecto.getCodigo());
            this.cadenaCOnsulta = "";
            this.productosConsulta = this.proyectoServicio.buscarProductosWS(this.cadenaCOnsulta);
            this.busqueda=false;
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error al cargar los datos");
        }
    }
    
    public void consultaProductos()
    {
        this.productosConsulta = this.proyectoServicio.buscarProductosWS(this.cadenaCOnsulta);
    }
    
    public void realizarBusqueda()
    {
        this.busqueda = true;
    }
    
    public void agregarProductoDetalle()
    {
        if(this.productoSelected!=null)
        {
            if(this.listaDetalleFactura==null)
                this.listaDetalleFactura = new ArrayList<>();
            
            DetalleFactura df = new DetalleFactura();
            df.setCodigoProducto(this.productoSelected.getCodigo());
            df.setProducto(this.productoSelected);
            df.setCantidad(this.cantidad);
            df.setCostoUnitario(this.productoSelected.getPrecioUnitario());
            BigDecimal total = this.productoSelected.getPrecioUnitario().multiply(BigDecimal.valueOf((double)cantidad));
            df.setCostoTotal(total);
            
            this.listaDetalleFactura.add(df);
        }
    }
    
    public void enviarPedido()
    {
        try { // Call Web Service Operation
            ec.com.espe.arqui.webservices.FacturaWS_Service service = new ec.com.espe.arqui.webservices.FacturaWS_Service();
            ec.com.espe.arqui.webservices.FacturaWS port = service.getFacturaWSPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            java.lang.Boolean result = port.guardarFactura(this.codigoCliente, this.listaDetalleFactura);
            System.out.println("Result = "+result);
            if(result)
                System.out.println("La Compra se generó correctamente");
            else
                System.out.println("La compra no pudo ser realizada");
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println("Hubo un error en la conexión");
        }
    }
    
    public void cancel()
    {
        this.busqueda = false;
        this.listaDetalleFactura = null;
    }
}
