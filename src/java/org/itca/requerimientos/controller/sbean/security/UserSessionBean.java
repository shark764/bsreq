/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.sbean.security;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dfhernandez
 */
@ManagedBean(name = "userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {
    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("user: " + context.getExternalContext().getUserPrincipal());
        System.out.println("session: " + context.getExternalContext().getSession(false));
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/views/login.xhtml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new instance of JasperBean
     */
    public UserSessionBean() {
    }
    
}