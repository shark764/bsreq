/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.inventory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.CategoriaEquipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CategoriaEquipoFacade extends AbstractFacade<CategoriaEquipo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaEquipoFacade() {
        super(CategoriaEquipo.class);
    }
    
}
