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
import org.itca.requerimientos.model.entities.ModeloEquipo;
import org.itca.requerimientos.model.entities.jasper.ModeloEquipoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class ModeloEquipoFacade extends AbstractFacade<ModeloEquipo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloEquipoFacade() {
        super(ModeloEquipo.class);
    }

    public List<ModeloEquipoJasper> findAllForStockByEquipmentModelReport() {
        List<ModeloEquipoJasper> list = null;
        Query q = em.createNamedQuery("ModeloEquipo.stockByEquipmentModelReport");
        list = q.getResultList();
        return list;
    }
    
}
