/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.loanrequest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.Prestamo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class PrestamoFacade extends AbstractFacade<Prestamo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamoFacade() {
        super(Prestamo.class);
    }

    public List<Prestamo> findByEmployee(Integer id, int[] range)
    {
        List<Prestamo> list = null;
        Query q = em.createNamedQuery("Prestamo.findByEmployee");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<Prestamo> entryRange(Date start, Date end, int[] range)
    {
        List<Prestamo> list = null;
        Query q = em.createNamedQuery("Prestamo.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }
    
}
