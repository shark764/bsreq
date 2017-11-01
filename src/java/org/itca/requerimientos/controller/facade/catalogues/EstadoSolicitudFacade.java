/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.catalogues;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.EstadoSolicitud;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class EstadoSolicitudFacade extends AbstractFacade<EstadoSolicitud> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSolicitudFacade() {
        super(EstadoSolicitud.class);
    }

    public EstadoSolicitud findByCodigo(String codigo)
    {
        Query q = em.createNamedQuery("EstadoSolicitud.findByCodigo");
        q.setParameter("codigo", codigo);
        return (EstadoSolicitud) q.getSingleResult();
    }
    
}
