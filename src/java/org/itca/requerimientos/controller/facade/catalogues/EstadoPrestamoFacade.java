/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.catalogues;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.EstadoPrestamo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class EstadoPrestamoFacade extends AbstractFacade<EstadoPrestamo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPrestamoFacade() {
        super(EstadoPrestamo.class);
    }

    public EstadoPrestamo findByCodigo(String codigo)
    {
        Query q = em.createNamedQuery("EstadoPrestamo.findByCodigo");
        q.setParameter("codigo", codigo);
        return (EstadoPrestamo) q.getSingleResult();
    }
    
}
