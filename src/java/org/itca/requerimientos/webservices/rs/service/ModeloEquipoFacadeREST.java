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
import org.itca.requerimientos.model.entities.ModeloEquipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
@Path("model")
public class ModeloEquipoFacadeREST extends AbstractFacade<ModeloEquipo> {
    @PersistenceContext(unitName = "SysBsReqPU")
    private EntityManager em;

    public ModeloEquipoFacadeREST() {
        super(ModeloEquipo.class);
    }

    @POST
    @Override
    @Consumes({"application/json", "application/xml"})
    public void create(ModeloEquipo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json", "application/xml"})
    public void edit(@PathParam("id") Short id, ModeloEquipo entity) {
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
    public ModeloEquipo find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json", "application/xml"})
    public List<ModeloEquipo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/xml"})
    public List<ModeloEquipo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
