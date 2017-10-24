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

    public List<DetallePrestamo> returnedOverTime()
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.returnedOverTime");
        q.setParameter("limit", new Date());
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturnedLimit()
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturnedLimit");
        q.setParameter("now", new Date());
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturned()
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturned");
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturnedByEmployee(Integer id)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturnedByEmployee");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> entryRange(Date start, Date end)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

}