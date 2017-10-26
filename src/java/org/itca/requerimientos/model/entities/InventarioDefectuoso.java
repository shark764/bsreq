/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "inventario_defectuoso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioDefectuoso.findByEquipment", query = "SELECT i FROM InventarioDefectuoso i WHERE i.idEquipo = :id"),
    @NamedQuery(name = "InventarioDefectuoso.entryRange", query = "SELECT i FROM InventarioDefectuoso i WHERE i.fechaIngreso >= :start AND i.fechaIngreso <= :end"),
    @NamedQuery(name = "InventarioDefectuoso.findAll", query = "SELECT i FROM InventarioDefectuoso i"),
    @NamedQuery(name = "InventarioDefectuoso.findById", query = "SELECT i FROM InventarioDefectuoso i WHERE i.id = :id"),
    @NamedQuery(name = "InventarioDefectuoso.findByDescripcion", query = "SELECT i FROM InventarioDefectuoso i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InventarioDefectuoso.findByFechaIngreso", query = "SELECT i FROM InventarioDefectuoso i WHERE i.fechaIngreso = :fechaIngreso")})
public class InventarioDefectuoso implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_inventario_defectuoso",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_inventario_defectuoso")
    @Id
    @GeneratedValue(generator = "sec_inventario_defectuoso")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipo idEquipo;

    public InventarioDefectuoso() {
    }

    public InventarioDefectuoso(Long id) {
        this.id = id;
    }

    public InventarioDefectuoso(Long id, String descripcion, Date fechaIngreso) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioDefectuoso)) {
            return false;
        }
        InventarioDefectuoso other = (InventarioDefectuoso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idEquipo + "] " + this.fechaIngreso;
        // return "org.itca.requerimientos.model.entities.InventarioDefectuoso[ id=" + id + " ]";
    }
    
}
