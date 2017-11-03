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
import org.itca.requerimientos.model.entities.SolicitudRequerimiento;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class SolicitudRequerimientoFacade extends AbstractFacade<SolicitudRequerimiento> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudRequerimientoFacade() {
        super(SolicitudRequerimiento.class);
    }

    public List<SolicitudRequerimiento> findByArea(Short id, int[] range)
    {
        List<SolicitudRequerimiento> list = null;
        Query q = em.createNamedQuery("SolicitudRequerimiento.findByArea");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<SolicitudRequerimiento> findByEmployee(Integer id, int[] range)
    {
        List<SolicitudRequerimiento> list = null;
        Query q = em.createNamedQuery("SolicitudRequerimiento.findByEmployee");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<SolicitudRequerimiento> entryRange(Date start, Date end, int[] range)
    {
        List<SolicitudRequerimiento> list = null;
        Query q = em.createNamedQuery("SolicitudRequerimiento.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }
    
}
