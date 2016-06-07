/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.servicio;

import ec.com.espe.arqui.dao.SedeDepartamentoDAO;
import ec.com.espe.arqui.modelo.SedeDepartamento;
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
public class SedeServicio {
    @EJB
    private SedeDepartamentoDAO sedeDepartamentoDAO;
    
     public List<SedeDepartamento> obtenerTodas() {
        return this.sedeDepartamentoDAO.findAll();
    }
}
