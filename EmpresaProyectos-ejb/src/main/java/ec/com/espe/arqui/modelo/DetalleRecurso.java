/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rdne1
 */
@Entity
@Table(name = "DETALLE_RECURSO")
public class DetalleRecurso implements Serializable{
    @Id
    @Column(name = "CODIGO_DETALLE_RECURSO", nullable = false)
    private String codigo;
    
    @Column(name = "CODIGO_PROYECTO", nullable = false)
    private String codigoProyecto;
    
    @JoinColumn(name = "CODIGO_PROYECTO", referencedColumnName = "CODIGO_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    
    @Column(name = "CODIGO_PRODUCTO", nullable = false)
    private String codigoProducto;
    
    @Column(name = "NOMBRE_PRODUCTO", nullable = false)
    private String descripcionProducto;
    
    @Column(name = "COSTO_UNITARIO_PRODUCTO", nullable = false)
    private BigDecimal costoUnitarioProducto;
    
    @Column(name = "CANTIDAD_PRODUCTO", nullable = false)
    private Integer cantidadProducto; 
    
    @Column(name = "CODIGO_EMPRESA", nullable = false)
    private String codigoProveedor;
    
    @Column(name = "NOMBRE_EMPRESA", nullable = false)
    private String nombreProveedor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_COMPRA", nullable = false)
    private Date fechaCompra;

    public DetalleRecurso() {
    }

    public DetalleRecurso(String codigo, String codigoProyecto, String codigoProducto, String descripcionProducto, BigDecimal costoUnitarioProducto, Integer cantidadProducto, String codigoProveedor, String nombreProveedor, Date fechaCompra) {
        this.codigo = codigo;
        this.codigoProyecto = codigoProyecto;
        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
        this.costoUnitarioProducto = costoUnitarioProducto;
        this.cantidadProducto = cantidadProducto;
        this.codigoProveedor = codigoProveedor;
        this.nombreProveedor = nombreProveedor;
        this.fechaCompra = fechaCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getCostoUnitarioProducto() {
        return costoUnitarioProducto;
    }

    public void setCostoUnitarioProducto(BigDecimal costoUnitarioProducto) {
        this.costoUnitarioProducto = costoUnitarioProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
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
        final DetalleRecurso other = (DetalleRecurso) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleRecurso{" + "codigo=" + codigo + ", codigoProyecto=" + codigoProyecto + ", proyecto=" + proyecto + ", codigoProducto=" + codigoProducto + ", descripcionProducto=" + descripcionProducto + ", costoUnitarioProducto=" + costoUnitarioProducto + ", cantidadProducto=" + cantidadProducto + ", codigoProveedor=" + codigoProveedor + ", nombreProveedor=" + nombreProveedor + ", fechaCompra=" + fechaCompra + '}';
    }
    
    
}
