/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.model.entities.DetalleSolicitud;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class DetalleSolicitudFacade extends AbstractFacade<DetalleSolicitud> {
    @PersistenceContext(unitName = "BsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleSolicitudFacade() {
        super(DetalleSolicitud.class);
    }

    public List<DetalleSolicitud> notSolved()
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.notSolved");
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> notSolvedByAssignedTechnician(Integer id)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.notSolvedByAssignedTechnician");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> entryRange(Date start, Date end)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByAssignedTechnician(Integer id)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByAssignedTechnician");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findBySolutionType(Integer id)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findBySolutionType");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByRequestType(Integer id)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByRequestType");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByFaultType(Integer id)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByFaultType");
        q.setParameter("id", id);
        list = q.getResultList();
        return list;
    }
    
}