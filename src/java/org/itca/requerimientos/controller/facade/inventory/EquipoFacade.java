/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.inventory;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.Equipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {
    @PersistenceContext(unitName = "BsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public List<Equipo> nonStock(Integer min)
    {
        List<Equipo> list = null;
        Query q = em.createNamedQuery("Equipo.nonStock");
        q.setParameter("min", min);
        list = q.getResultList();
        return list;
    }

    public List<Equipo> entryRange(Date start, Date end)
    {
        List<Equipo> list = null;
        Query q = em.createNamedQuery("Equipo.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<Equipo> findByProvider(Integer id)
    {
        List<Equipo> list = null;
        Query q = em.createNamedQuery("Equipo.findByProvider");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<Equipo> stockRange(Integer start, Integer end)
    {
        List<Equipo> list = null;
        Query q = em.createNamedQuery("Equipo.stockRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<Equipo> findByModel(Integer id)
    {
        List<Equipo> list = null;
        Query q = em.createNamedQuery("Equipo.findByModel");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }
    
}
