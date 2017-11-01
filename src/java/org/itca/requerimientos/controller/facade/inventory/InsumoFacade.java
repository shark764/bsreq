/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.inventory;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.Insumo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class InsumoFacade extends AbstractFacade<Insumo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumoFacade() {
        super(Insumo.class);
    }

    public List<Insumo> nonStock(Integer min)
    {
        List<Insumo> list = null;
        Query q = em.createNamedQuery("Insumo.nonStock");
        q.setParameter("min", min);
        list = q.getResultList();
        return list;
    }

    public List<Insumo> stockRange(Integer start, Integer end)
    {
        List<Insumo> list = null;
        Query q = em.createNamedQuery("Insumo.stockRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        list = q.getResultList();
        return list;
    }
    
}
