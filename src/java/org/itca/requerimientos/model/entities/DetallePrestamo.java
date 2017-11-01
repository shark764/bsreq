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
@Table(name = "detalle_prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePrestamo.limitTime", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaLimite >= :now"),
    @NamedQuery(name = "DetallePrestamo.findByEmployee", query = "SELECT d FROM DetallePrestamo d WHERE d.idPrestamo.idEmpleado = :id"),
    @NamedQuery(name = "DetallePrestamo.findByEquipment", query = "SELECT d FROM DetallePrestamo d WHERE d.idEquipo = :id"),
    @NamedQuery(name = "DetallePrestamo.returnedOverTime", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaEntrega >= d.fechaLimite"),
    @NamedQuery(name = "DetallePrestamo.notReturned", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaEntrega IS NULL"),
    @NamedQuery(name = "DetallePrestamo.notReturnedByEmployee", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaEntrega IS NULL AND d.idPrestamo.idEmpleado = :id"),
    @NamedQuery(name = "DetallePrestamo.entryRange", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaPrestamo >= :start AND d.fechaPrestamo <= :end"),
    @NamedQuery(name = "DetallePrestamo.findAll", query = "SELECT d FROM DetallePrestamo d"),
    @NamedQuery(name = "DetallePrestamo.findById", query = "SELECT d FROM DetallePrestamo d WHERE d.id = :id"),
    @NamedQuery(name = "DetallePrestamo.findByFechaPrestamo", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "DetallePrestamo.findByFechaEntrega", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "DetallePrestamo.findByDescripcion", query = "SELECT d FROM DetallePrestamo d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetallePrestamo.findByFechaLimite", query = "SELECT d FROM DetallePrestamo d WHERE d.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "DetallePrestamo.findByComentario", query = "SELECT d FROM DetallePrestamo d WHERE d.comentario = :comentario")})
public class DetallePrestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_detalle_prestamo",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_detalle_prestamo")
    @Id
    @GeneratedValue(generator = "sec_detalle_prestamo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimite;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipo idEquipo;
    @JoinColumn(name = "id_estado_prestamo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoPrestamo idEstadoPrestamo;
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prestamo idPrestamo;

    public DetallePrestamo() {
    }

    public DetallePrestamo(Long id) {
        this.id = id;
    }

    public DetallePrestamo(Long id, Date fechaPrestamo) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
    }

    public DetallePrestamo(Prestamo idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public EstadoPrestamo getIdEstadoPrestamo() {
        return idEstadoPrestamo;
    }

    public void setIdEstadoPrestamo(EstadoPrestamo idEstadoPrestamo) {
        this.idEstadoPrestamo = idEstadoPrestamo;
    }

    public Prestamo getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Prestamo idPrestamo) {
        this.idPrestamo = idPrestamo;
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
        if (!(object instanceof DetallePrestamo)) {
            return false;
        }
        DetallePrestamo other = (DetallePrestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idPrestamo + "] " + this.idEquipo;
        // return "org.itca.requerimientos.model.entities.DetallePrestamo[ id=" + id + " ]";
    }
    
}
