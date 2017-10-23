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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "detalle_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitud.notSolved", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio IS NULL"),
    @NamedQuery(name = "DetalleSolicitud.notSolvedByAssignedTechnician", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio IS NULL AND  d.idTecnicoAsignado = :id"),
    @NamedQuery(name = "DetalleSolicitud.entryRange", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio >= :start AND d.fechaInicio <= :end"),
    @NamedQuery(name = "DetalleSolicitud.findByAssignedTechnician", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTecnicoAsignado = :id"),
    @NamedQuery(name = "DetalleSolicitud.findBySolutionType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoSolucion = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByRequestType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoRequerimiento = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByFaultType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoFalla = :id"),
    @NamedQuery(name = "DetalleSolicitud.findAll", query = "SELECT d FROM DetalleSolicitud d"),
    @NamedQuery(name = "DetalleSolicitud.findById", query = "SELECT d FROM DetalleSolicitud d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByFechaInicio", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "DetalleSolicitud.findByFechaFin", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaFin = :fechaFin"),
    @NamedQuery(name = "DetalleSolicitud.findByDescripcion", query = "SELECT d FROM DetalleSolicitud d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetalleSolicitud.findByComentario", query = "SELECT d FROM DetalleSolicitud d WHERE d.comentario = :comentario")})
public class DetalleSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_detalle_solicitud",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_detalle_solicitud")
    @Id
    @GeneratedValue(generator = "sec_detalle_solicitud")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleSolicitud", fetch = FetchType.LAZY)
    private List<InsumoUtilizado> insumoUtilizadoList;
    @JoinColumn(name = "id_tecnico_asignado", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado idTecnicoAsignado;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipo idEquipo;
    @JoinColumn(name = "id_estado_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoSolicitud idEstadoSolicitud;
    @JoinColumn(name = "id_solicitud_requerimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SolicitudRequerimiento idSolicitudRequerimiento;
    @JoinColumn(name = "id_tipo_falla", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFalla idTipoFalla;
    @JoinColumn(name = "id_tipo_requerimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRequerimiento idTipoRequerimiento;
    @JoinColumn(name = "id_tipo_solucion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoSolucion idTipoSolucion;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Long id) {
        this.id = id;
    }

    public DetalleSolicitud(Long id, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @XmlTransient
    public List<InsumoUtilizado> getInsumoUtilizadoList() {
        return insumoUtilizadoList;
    }

    public void setInsumoUtilizadoList(List<InsumoUtilizado> insumoUtilizadoList) {
        this.insumoUtilizadoList = insumoUtilizadoList;
    }

    public Empleado getIdTecnicoAsignado() {
        return idTecnicoAsignado;
    }

    public void setIdTecnicoAsignado(Empleado idTecnicoAsignado) {
        this.idTecnicoAsignado = idTecnicoAsignado;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public EstadoSolicitud getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(EstadoSolicitud idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public SolicitudRequerimiento getIdSolicitudRequerimiento() {
        return idSolicitudRequerimiento;
    }

    public void setIdSolicitudRequerimiento(SolicitudRequerimiento idSolicitudRequerimiento) {
        this.idSolicitudRequerimiento = idSolicitudRequerimiento;
    }

    public TipoFalla getIdTipoFalla() {
        return idTipoFalla;
    }

    public void setIdTipoFalla(TipoFalla idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
    }

    public TipoRequerimiento getIdTipoRequerimiento() {
        return idTipoRequerimiento;
    }

    public void setIdTipoRequerimiento(TipoRequerimiento idTipoRequerimiento) {
        this.idTipoRequerimiento = idTipoRequerimiento;
    }

    public TipoSolucion getIdTipoSolucion() {
        return idTipoSolucion;
    }

    public void setIdTipoSolucion(TipoSolucion idTipoSolucion) {
        this.idTipoSolucion = idTipoSolucion;
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
        if (!(object instanceof DetalleSolicitud)) {
            return false;
        }
        DetalleSolicitud other = (DetalleSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idEquipo + "] " + this.idTecnicoAsignado;
        // return "org.itca.requerimientos.model.entities.DetalleSolicitud[ id=" + id + " ]";
    }
    
}
