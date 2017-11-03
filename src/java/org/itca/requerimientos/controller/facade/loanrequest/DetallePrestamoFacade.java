/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.loanrequest;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.DetallePrestamo;
import org.itca.requerimientos.model.entities.jasper.RetrasoPrestamoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class DetallePrestamoFacade extends AbstractFacade<DetallePrestamo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePrestamoFacade() {
        super(DetallePrestamo.class);
    }

    public List<DetallePrestamo> returnedOverTime(int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.returnedOverTime");
        // q.setParameter("limit", new Date());
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturnedLimit(int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturnedLimit");
        q.setParameter("now", new Date());
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturned(int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturned");
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> notReturnedByEmployee(Integer id, int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.notReturnedByEmployee");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> entryRange(Date start, Date end, int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> findByEquipment(Long id, int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.findByEquipment");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> findByEmployee(Integer id, int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.findByEmployee");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetallePrestamo> limitTime(int[] range)
    {
        List<DetallePrestamo> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.limitTime");
        q.setParameter("now", new Date());
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<RetrasoPrestamoJasper> findAllForEquipmentReturnedOverTimeReport() {
        List<RetrasoPrestamoJasper> list = null;
        Query q = em.createNamedQuery("DetallePrestamo.equipmentReturnedOverTimeReport");
        list = q.getResultList();
        return list;
    }

}