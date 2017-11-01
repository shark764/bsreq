/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.request;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.InsumoUtilizado;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class InsumoUtilizadoFacade extends AbstractFacade<InsumoUtilizado> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumoUtilizadoFacade() {
        super(InsumoUtilizado.class);
    }

    public List<InsumoUtilizado> usedRange(Integer start, Integer end)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.usedRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<InsumoUtilizado> wastedRange(Integer start, Integer end)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.wastedRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<InsumoUtilizado> findByEquipment(Integer id)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.findByEquipment");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<InsumoUtilizado> entryRange(Date start, Date end)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<InsumoUtilizado> findByEquipmentUsed(Integer id)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.findByEquipmentUsed");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<InsumoUtilizado> findByResourceUsed(Integer id)
    {
        List<InsumoUtilizado> list = null;
        Query q = em.createNamedQuery("InsumoUtilizado.findByResourceUsed");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }
    
}
