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
    @NamedQuery(name = "InsumoUtilizado.findByEquipmentUsed", query = "SELECT i FROM InsumoUtilizado i WHERE i.idEquipoIntercambio = :id"),
    @NamedQuery(name = "InsumoUtilizado.findByResourceUsed", query = "SELECT i FROM InsumoUtilizado i WHERE i.idInsumo = :id"),
    @NamedQuery(name = "InsumoUtilizado.findByEquipment", query = "SELECT i FROM InsumoUtilizado i WHERE i.idDetalleSolicitud.idEquipo = :id"),
    @NamedQuery(name = "InsumoUtilizado.usedRange", query = "SELECT i FROM InsumoUtilizado i WHERE i.utlilizado >= :start AND i.utlilizado <= :end"),
    @NamedQuery(name = "InsumoUtilizado.wastedRange", query = "SELECT i FROM InsumoUtilizado i WHERE i.desperdicio >= :start AND i.desperdicio <= :end"),
    @NamedQuery(name = "InsumoUtilizado.entryRange", query = "SELECT i FROM InsumoUtilizado i WHERE i.idDetalleSolicitud.fechaInicio >= :start AND i.idDetalleSolicitud.fechaInicio <= :end"),
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
    @Column(name = "desperdicio")
    private Short desperdicio;
    @JoinColumn(name = "id_detalle_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleSolicitud idDetalleSolicitud;
    @JoinColumn(name = "id_equipo_intercambio", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo idEquipoIntercambio;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Insumo idInsumo;

    public InsumoUtilizado() {
    }

    public InsumoUtilizado(Long id) {
        this.id = id;
    }

    public InsumoUtilizado(Long id, short desperdicio) {
        this.id = id;
        this.desperdicio = desperdicio;
    }

    public InsumoUtilizado(DetalleSolicitud idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
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

    public Short getDesperdicio() {
        return desperdicio;
    }

    public void setDesperdicio(Short desperdicio) {
        this.desperdicio = desperdicio;
    }

    public DetalleSolicitud getIdDetalleSolicitud() {
        return idDetalleSolicitud;
    }

    public void setIdDetalleSolicitud(DetalleSolicitud idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
    }

    public Equipo getIdEquipoIntercambio() {
        return idEquipoIntercambio;
    }

    public void setIdEquipoIntercambio(Equipo idEquipoIntercambio) {
        this.idEquipoIntercambio = idEquipoIntercambio;
    }

    public Insumo getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumo idInsumo) {
        this.idInsumo = idInsumo;
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
        return "[Utilizado: " + this.utlilizado + "] " + this.idInsumo;
        // return "org.itca.requerimientos.model.entities.InsumoUtilizado[ id=" + id + " ]";
    }
    
}
