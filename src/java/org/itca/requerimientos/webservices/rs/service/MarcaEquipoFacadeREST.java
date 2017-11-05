/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.webservices.rs.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.itca.requerimientos.model.entities.MarcaEquipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
@Path("branch")
public class MarcaEquipoFacadeREST extends AbstractFacade<MarcaEquipo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    public MarcaEquipoFacadeREST() {
        super(MarcaEquipo.class);
    }

    @POST
    @Override
    @Consumes({"application/json", "application/xml"})
    public void create(MarcaEquipo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json", "application/xml"})
    public void edit(@PathParam("id") Short id, MarcaEquipo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public MarcaEquipo find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json", "application/xml"})
    public List<MarcaEquipo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/xml"})
    public List<MarcaEquipo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
