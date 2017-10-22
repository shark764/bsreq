/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "solicitud_requerimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudRequerimiento.findAll", query = "SELECT s FROM SolicitudRequerimiento s"),
    @NamedQuery(name = "SolicitudRequerimiento.findById", query = "SELECT s FROM SolicitudRequerimiento s WHERE s.id = :id"),
    @NamedQuery(name = "SolicitudRequerimiento.findByFecha", query = "SELECT s FROM SolicitudRequerimiento s WHERE s.fecha = :fecha")})
public class SolicitudRequerimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_solicitud_requerimiento",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_solicitud_requerimiento")
    @Id
    @GeneratedValue(generator = "sec_solicitud_requerimiento")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area idArea;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado idEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitudRequerimiento", fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detalleSolicitudList;

    public SolicitudRequerimiento() {
    }

    public SolicitudRequerimiento(Long id) {
        this.id = id;
    }

    public SolicitudRequerimiento(Long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    public List<DetalleSolicitud> getDetalleSolicitudList() {
        return detalleSolicitudList;
    }

    public void setDetalleSolicitudList(List<DetalleSolicitud> detalleSolicitudList) {
        this.detalleSolicitudList = detalleSolicitudList;
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
        if (!(object instanceof SolicitudRequerimiento)) {
            return false;
        }
        SolicitudRequerimiento other = (SolicitudRequerimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idArea + "] " + this.fecha;
        // return "org.itca.requerimientos.model.entities.SolicitudRequerimiento[ id=" + id + " ]";
    }
    
}
