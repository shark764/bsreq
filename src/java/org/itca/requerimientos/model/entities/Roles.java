/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rolesPK.rol = :rol"),
    @NamedQuery(name = "Roles.findByIduser", query = "SELECT r FROM Roles r WHERE r.rolesPK.iduser = :iduser"),
    @NamedQuery(name = "Roles.findByEstado", query = "SELECT r FROM Roles r WHERE r.estado = :estado")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPK rolesPK;
    @Column(name = "Estado")
    private Integer estado;

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(String rol, String iduser) {
        this.rolesPK = new RolesPK(rol, iduser);
    }

    public Roles(String rol, String iduser, Integer estado) {
        this.rolesPK = new RolesPK(rol, iduser);
        this.estado = estado;
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPK != null ? rolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesPK == null && other.rolesPK != null) || (this.rolesPK != null && !this.rolesPK.equals(other.rolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.Roles[ rolesPK=" + rolesPK + " ]";
    }
    
}
