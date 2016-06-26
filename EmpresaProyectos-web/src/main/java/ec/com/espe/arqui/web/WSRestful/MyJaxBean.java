/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.web.WSRestful;


import ec.com.espe.arqui.webservices.DetalleFactura;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rdne1
 */
@XmlRootElement
public class MyJaxBean implements Serializable {
    @XmlElement public String codigoCliente;
    @XmlElement public List<DetalleFactura> detalleFactura;
}
