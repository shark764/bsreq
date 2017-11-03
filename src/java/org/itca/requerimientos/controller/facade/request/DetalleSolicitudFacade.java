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
import org.itca.requerimientos.model.entities.DetalleSolicitud;
import org.itca.requerimientos.model.entities.jasper.SolicitudEquipoJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudFallaJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudTecnicoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class DetalleSolicitudFacade extends AbstractFacade<DetalleSolicitud> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleSolicitudFacade() {
        super(DetalleSolicitud.class);
    }

    public List<DetalleSolicitud> notSolved(int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.notSolved");
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> notSolvedByAssignedTechnician(Integer id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.notSolvedByAssignedTechnician");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> entryRange(Date start, Date end, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByAssignedTechnician(Integer id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByAssignedTechnician");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findBySolutionType(Short id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findBySolutionType");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByRequestType(Short id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByRequestType");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByFaultType(Short id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByFaultType");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByEquipment(Long id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByEquipment");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> findByEmployee(Integer id, int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.findByEmployee");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> limitTime(int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.limitTime");
        q.setParameter("now", new Date());
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<DetalleSolicitud> solvedOverTime(int[] range)
    {
        List<DetalleSolicitud> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.solvedOverTime");
        // q.setParameter("limit", new Date());
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<SolicitudEquipoJasper> findAllForRequestByEquipmentModelReport() {
        List<SolicitudEquipoJasper> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.requestByEquipmentModelReport");
        list = q.getResultList();
        return list;
    }

    public List<SolicitudFallaJasper> findAllForRequestByEquipmentFailureReport() {
        List<SolicitudFallaJasper> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.requestByEquipmentFailureReport");
        list = q.getResultList();
        return list;
    }

    public List<SolicitudTecnicoJasper> findAllForRequestByAssignedTechnicianReport() {
        List<SolicitudTecnicoJasper> list = null;
        Query q = em.createNamedQuery("DetalleSolicitud.requestByAssignedTechnicianReport");
        list = q.getResultList();
        return list;
    }
    
}