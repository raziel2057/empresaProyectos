/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.servicio;

import ec.com.espe.arqui.dao.DetalleRecursoDAO;
import ec.com.espe.arqui.dao.ProyectoDAO;
import ec.com.espe.arqui.exception.ValidacionException;
import ec.com.espe.arqui.modelo.DetalleRecurso;
import ec.com.espe.arqui.modelo.Proyecto;
import ec.com.espe.arqui.webservices.DetalleFactura;
import ec.com.espe.arqui.webservices.Producto;
import java.util.ArrayList;
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
public class ProyectoServicio {
    @EJB
    private ProyectoDAO proyectoDAO;
    
    @EJB
    private DetalleRecursoDAO detalleRecursoDAO;
    
     public List<Proyecto> obtenerTodas() {
        return this.proyectoDAO.findAll();
    }

    public Proyecto obtenerPorID(String codigoProyecto) {
        return this.proyectoDAO.findById(codigoProyecto, false);
    }

    public void crearProyecto(Proyecto proyecto) throws ValidacionException {

        this.proyectoDAO.insert(proyecto);
        this.proyectoDAO.flush();
    }
    
     public void crearDetalleRecurso(DetalleRecurso detalleRecurso) throws ValidacionException {

        this.detalleRecursoDAO.insert(detalleRecurso);
        this.detalleRecursoDAO.flush();
    }

    public void actualiarProyecto(Proyecto proyecto) {
        this.proyectoDAO.update(proyecto);
    }

    public void eliminarProyecto(String codigoProyecto) {
        try {
            Proyecto proyectoTmp = this.obtenerPorID(codigoProyecto);
            this.proyectoDAO.remove(proyectoTmp);
        } catch (Exception e) {
            throw new ValidacionException("El proyecto " + codigoProyecto + " esta asociado otra tabla");
        }
    }
    
     public List<DetalleRecurso> buscarRecursosPorProyecto(String codigoProyecto) {
        List<DetalleRecurso> recursos;
       
        DetalleRecurso detalleRecursoTmp = new DetalleRecurso();
        detalleRecursoTmp.setCodigoProyecto(codigoProyecto);
        recursos = this.detalleRecursoDAO.find(detalleRecursoTmp);
        

        return recursos;
    }
     
     public List<Producto> buscarProductosWS(String cadenaB)
     {
         
         try { // Call Web Service Operation
            ec.com.espe.arqui.webservices.SuministrosWS_Service service = new ec.com.espe.arqui.webservices.SuministrosWS_Service();
            ec.com.espe.arqui.webservices.SuministrosWS port = service.getSuministrosWSPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            java.util.List<ec.com.espe.arqui.webservices.Producto> result = port.busquedaProductos(cadenaB);
            System.out.println("Result = "+result);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
         
     }
     
     public Boolean realizarPedido(String codigoCliente,List<DetalleFactura> detalleFactura)
     {
         
         try { // Call Web Service Operation
            ec.com.espe.arqui.webservices.FacturaWS_Service service = new ec.com.espe.arqui.webservices.FacturaWS_Service();
            ec.com.espe.arqui.webservices.FacturaWS port = service.getFacturaWSPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            java.lang.Boolean result = port.guardarFactura(codigoCliente, detalleFactura);
            System.out.println("Result = "+result);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return false;
        }
         
         
     }
}
