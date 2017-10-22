/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "insumo_utilizado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsumoUtilizado.findAll", query = "SELECT i FROM InsumoUtilizado i"),
    @NamedQuery(name = "InsumoUtilizado.findById", query = "SELECT i FROM InsumoUtilizado i WHERE i.id = :id"),
    @NamedQuery(name = "InsumoUtilizado.findByUtlilizado", query = "SELECT i FROM InsumoUtilizado i WHERE i.utlilizado = :utlilizado"),
    @NamedQuery(name = "InsumoUtilizado.findByDesperdicio", query = "SELECT i FROM InsumoUtilizado i WHERE i.desperdicio = :desperdicio")})
public class InsumoUtilizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_insumo_utilizado",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_insumo_utilizado")
    @Id
    @GeneratedValue(generator = "sec_insumo_utilizado")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "utlilizado")
    private Short utlilizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "desperdicio")
    private short desperdicio;
    @JoinColumn(name = "id_detalle_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleSolicitud idDetalleSolicitud;

    public InsumoUtilizado() {
    }

    public InsumoUtilizado(Long id) {
        this.id = id;
    }

    public InsumoUtilizado(Long id, short desperdicio) {
        this.id = id;
        this.desperdicio = desperdicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getUtlilizado() {
        return utlilizado;
    }

    public void setUtlilizado(Short utlilizado) {
        this.utlilizado = utlilizado;
    }

    public short getDesperdicio() {
        return desperdicio;
    }

    public void setDesperdicio(short desperdicio) {
        this.desperdicio = desperdicio;
    }

    public DetalleSolicitud getIdDetalleSolicitud() {
        return idDetalleSolicitud;
    }

    public void setIdDetalleSolicitud(DetalleSolicitud idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
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
        if (!(object instanceof InsumoUtilizado)) {
            return false;
        }
        InsumoUtilizado other = (InsumoUtilizado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Utilizado: " + this.utlilizado + "] " + this.idDetalleSolicitud;
        // return "org.itca.requerimientos.model.entities.InsumoUtilizado[ id=" + id + " ]";
    }
    
}
