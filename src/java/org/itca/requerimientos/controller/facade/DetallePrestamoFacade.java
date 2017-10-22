/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.model.entities.DetallePrestamo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class DetallePrestamoFacade extends AbstractFacade<DetallePrestamo> {
    @PersistenceContext(unitName = "BsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePrestamoFacade() {
        super(DetallePrestamo.class);
    }
    
}
