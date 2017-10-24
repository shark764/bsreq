/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.model.entities.Usuarios;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "BsReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public boolean login(String username, String password)
    {
        try {
            Usuarios u = em.createNamedQuery("Usuarios.login", Usuarios.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return u != null;
        } catch (Exception e) {
            return false;
        }
    }
    
}
