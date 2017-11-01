/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "insumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumo.stockRange", query = "SELECT i FROM Insumo i WHERE i.existencia >= :start AND i.existencia <= :end"),
    @NamedQuery(name = "Insumo.nonStock", query = "SELECT i FROM Insumo i WHERE i.existencia <= :min"),
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i"),
    @NamedQuery(name = "Insumo.findById", query = "SELECT i FROM Insumo i WHERE i.id = :id"),
    @NamedQuery(name = "Insumo.findByNombre", query = "SELECT i FROM Insumo i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Insumo.findByExistencia", query = "SELECT i FROM Insumo i WHERE i.existencia = :existencia")})
public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_insumo",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_insumo")
    @Id
    @GeneratedValue(generator = "sec_insumo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "existencia")
    private Short existencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo", fetch = FetchType.LAZY)
    private List<InsumoUtilizado> insumoUtilizadoList;

    public Insumo() {
    }

    public Insumo(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getExistencia() {
        return existencia;
    }

    public void setExistencia(Short existencia) {
        this.existencia = existencia;
    }

    @XmlTransient
    public List<InsumoUtilizado> getInsumoUtilizadoList() {
        return insumoUtilizadoList;
    }

    public void setInsumoUtilizadoList(List<InsumoUtilizado> insumoUtilizadoList) {
        this.insumoUtilizadoList = insumoUtilizadoList;
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
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Existencia: " + this.existencia + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.Insumo[ id=" + id + " ]";
    }
    
}
