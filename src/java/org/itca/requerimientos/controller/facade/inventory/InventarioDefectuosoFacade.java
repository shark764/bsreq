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
import org.itca.requerimientos.model.entities.InventarioDefectuoso;
import org.itca.requerimientos.model.entities.jasper.InventarioDefectuosoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class InventarioDefectuosoFacade extends AbstractFacade<InventarioDefectuoso> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioDefectuosoFacade() {
        super(InventarioDefectuoso.class);
    }

    public List<InventarioDefectuoso> entryRange(Date start, Date end, int[] range)
    {
        List<InventarioDefectuoso> list = null;
        Query q = em.createNamedQuery("InventarioDefectuoso.entryRange");
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<InventarioDefectuoso> findByEquipmentModel(Short id, int[] range)
    {
        List<InventarioDefectuoso> list = null;
        Query q = em.createNamedQuery("InventarioDefectuoso.findByEquipmentModel");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }

    public List<InventarioDefectuosoJasper> findAllForDefectiveInventoryReport() {
        List<InventarioDefectuosoJasper> list = null;
        Query q = em.createNamedQuery("InventarioDefectuoso.defectiveInventoryReport");
        list = q.getResultList();
        return list;
    }
    
}
