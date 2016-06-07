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
@Table(name = "EMPLEADO")
public class Empleado implements Serializable{
    @Id
    @Column(name = "CODIGO_EMPLEADO", nullable = false)
    private String codigo;
    
    @Column(name = "CODIGO_CARGO", nullable = false)
    private String codigoCargo;
    
    @JoinColumn(name = "CODIGO_CARGO", referencedColumnName = "CODIGO_CARGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "NUMERO_SEGURIDAD_SOCIAL", nullable = false)
    private String numeroSegSocial;
    
    @Column(name = "DIRECCION", nullable = false)
    private String direccion;
    
    @Column(name = "SALARIO", nullable = false)
    private BigDecimal salario;
    
    @Column(name = "SEXO", nullable = false)
    private String sexo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    
    @Column(name = "USUARIO", nullable = false)
    private String usuario;
    
    @Column(name = "CLAVE", nullable = false)
    private String clave;
    
    @Column(name = "TIPO", nullable = false)
    private String tipo;

    public Empleado() {
    }

    public Empleado(String codigo, String codigoCargo, String nombre, String numeroSegSocial, String direccion, BigDecimal salario, String sexo, Date fechaNacimiento, String usuario, String clave, String tipo) {
        this.codigo = codigo;
        this.codigoCargo = codigoCargo;
        this.nombre = nombre;
        this.numeroSegSocial = numeroSegSocial;
        this.direccion = direccion;
        this.salario = salario;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.clave = clave;
        this.tipo = tipo;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroSegSocial() {
        return numeroSegSocial;
    }

    public void setNumeroSegSocial(String numeroSegSocial) {
        this.numeroSegSocial = numeroSegSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigo);
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
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" + "codigo=" + codigo + ", codigoCargo=" + codigoCargo + ", cargo=" + cargo + ", nombre=" + nombre + ", numeroSegSocial=" + numeroSegSocial + ", direccion=" + direccion + ", salario=" + salario + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", usuario=" + usuario + ", clave=" + clave + ", tipo=" + tipo + '}';
    }
    
    
}
