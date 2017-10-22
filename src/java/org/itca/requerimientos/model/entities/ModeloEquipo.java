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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "modelo_equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModeloEquipo.findAll", query = "SELECT m FROM ModeloEquipo m"),
    @NamedQuery(name = "ModeloEquipo.findById", query = "SELECT m FROM ModeloEquipo m WHERE m.id = :id"),
    @NamedQuery(name = "ModeloEquipo.findByNombre", query = "SELECT m FROM ModeloEquipo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "ModeloEquipo.findByCodigo", query = "SELECT m FROM ModeloEquipo m WHERE m.codigo = :codigo")})
public class ModeloEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_modelo_equipo",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_modelo_equipo")
    @Id
    @GeneratedValue(generator = "sec_modelo_equipo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MarcaEquipo idMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;

    public ModeloEquipo() {
    }

    public ModeloEquipo(Short id) {
        this.id = id;
    }

    public ModeloEquipo(Short id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MarcaEquipo getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(MarcaEquipo idMarca) {
        this.idMarca = idMarca;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
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
        if (!(object instanceof ModeloEquipo)) {
            return false;
        }
        ModeloEquipo other = (ModeloEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.ModeloEquipo[ id=" + id + " ]";
    }
    
}
