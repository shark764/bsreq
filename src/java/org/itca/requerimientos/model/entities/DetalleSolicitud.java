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
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.itca.requerimientos.model.entities.jasper.SolicitudEquipoJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudFallaJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudTecnicoJasper;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "detalle_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitud.limitTime", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaLimite >= :now"),
    @NamedQuery(name = "DetalleSolicitud.solvedOverTime", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaFin >= d.fechaLimite"),
    @NamedQuery(name = "DetalleSolicitud.findByEquipment", query = "SELECT d FROM DetalleSolicitud d WHERE d.idEquipo.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByEmployee", query = "SELECT d FROM DetalleSolicitud d WHERE d.idSolicitudRequerimiento.idEmpleado.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.notSolved", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaFin IS NULL"),
    @NamedQuery(name = "DetalleSolicitud.notSolvedByAssignedTechnician", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaFin IS NULL AND d.idTecnicoAsignado.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.entryRange", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio >= :start AND d.fechaInicio <= :end"),
    @NamedQuery(name = "DetalleSolicitud.findByAssignedTechnician", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTecnicoAsignado.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findBySolutionType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoSolucion.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByRequestType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoRequerimiento.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByFaultType", query = "SELECT d FROM DetalleSolicitud d WHERE d.idTipoFalla.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findAll", query = "SELECT d FROM DetalleSolicitud d"),
    @NamedQuery(name = "DetalleSolicitud.findById", query = "SELECT d FROM DetalleSolicitud d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByFechaInicio", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "DetalleSolicitud.findByFechaFin", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaFin = :fechaFin"),
    @NamedQuery(name = "DetalleSolicitud.findByDescripcion", query = "SELECT d FROM DetalleSolicitud d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetalleSolicitud.findByComentario", query = "SELECT d FROM DetalleSolicitud d WHERE d.comentario = :comentario"),
    @NamedQuery(name = "DetalleSolicitud.findByFechaLimite", query = "SELECT d FROM DetalleSolicitud d WHERE d.fechaLimite = :fechaLimite")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "DetalleSolicitud.requestByEquipmentModelReport", query = "SELECT t01.codigo AS t01codigo, t01.nombre AS t01nombre, t02.codigo AS t02codigo, t02.nombre AS t02nombre, COUNT(t04.id) AS t04conteo FROM marca_equipo AS t01 JOIN modelo_equipo AS t02 ON t01.id = t02.id_marca JOIN equipo AS t03 ON t02.id = t03.id_modelo JOIN detalle_solicitud t04 ON t03.id = t04.id_equipo GROUP BY 1, 2, 3, 4 ORDER BY 5 DESC, 2, 4", resultSetMapping = "SolicitudEquipoJasperValueMapping"),
    @NamedNativeQuery(name = "DetalleSolicitud.requestByEquipmentFailureReport", query = "SELECT t05.codigo as t05codigo, t05.nombre as t05nombre, t04.codigo as t04codigo, t04.nombre as t04nombre, t01.codigo as t01codigo, t01.nombre as t01nombre, COUNT(t02.id) as t02fallas FROM tipo_falla AS t01 LEFT JOIN detalle_solicitud AS t02 ON t01.id = t02.id_tipo_falla LEFT JOIN equipo AS t03 ON t03.id = t02.id_equipo LEFT JOIN modelo_equipo AS t04 ON t04.id = t03.id_modelo LEFT JOIN marca_equipo AS t05 ON t05.id = t04.id_marca GROUP BY 1, 2, 3, 4, 5, 6 ORDER BY 7 DESC, 2, 4, 6", resultSetMapping = "SolicitudFallaJasperValueMapping"),
    @NamedNativeQuery(name = "DetalleSolicitud.requestByAssignedTechnicianReport", query = "SELECT CONCAT(t04.nombre, t04.apellido) AS t04tecnico, t01.codigo as t01codigo, t01.nombre as t01nombre, t03.codigo as t03codigo, t03.nombre as t03nombre, COUNT(t02.id) as t02mantenimientos FROM tipo_falla AS t01 JOIN detalle_solicitud AS t02 ON t01.id = t02.id_tipo_falla JOIN tipo_solucion AS t03 ON t03.id = t02.id_tipo_solucion JOIN empleado AS t04 ON t04.id = t02.id_tecnico_asignado GROUP BY 1, 2, 3, 4, 5 ORDER BY 6 DESC, 1, 3, 5", resultSetMapping = "SolicitudTecnicoJasperValueMapping"),
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
	    name = "SolicitudEquipoJasperValueMapping",
	    classes = @ConstructorResult(
		    targetClass = SolicitudEquipoJasper.class,
		    columns = {
			@ColumnResult(name = "t01codigo"),
			@ColumnResult(name = "t01nombre"),
			@ColumnResult(name = "t02codigo"),
			@ColumnResult(name = "t02nombre"),
			@ColumnResult(name = "t04conteo", type = Long.class)
		    }
	    )
    ),
    @SqlResultSetMapping(
	    name = "SolicitudFallaJasperValueMapping",
	    classes = @ConstructorResult(
		    targetClass = SolicitudFallaJasper.class,
		    columns = {
			@ColumnResult(name = "t05codigo"),
			@ColumnResult(name = "t05nombre"),
			@ColumnResult(name = "t04codigo"),
			@ColumnResult(name = "t04nombre"),
			@ColumnResult(name = "t01codigo"),
			@ColumnResult(name = "t01nombre"),
			@ColumnResult(name = "t02fallas", type = Long.class)
		    }
	    )
    ),
    @SqlResultSetMapping(
	    name = "SolicitudTecnicoJasperValueMapping",
	    classes = @ConstructorResult(
		    targetClass = SolicitudTecnicoJasper.class,
		    columns = {
			@ColumnResult(name = "t04tecnico"),
			@ColumnResult(name = "t01codigo"),
			@ColumnResult(name = "t01nombre"),
			@ColumnResult(name = "t03codigo"),
			@ColumnResult(name = "t03nombre"),
			@ColumnResult(name = "t02mantenimientos", type = Long.class)
		    }
	    )
    )
})
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
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleSolicitud", fetch = FetchType.LAZY)
    private List<InsumoUtilizado> insumoUtilizadoList;
    @JoinColumn(name = "id_tecnico_asignado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
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
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoFalla idTipoFalla;
    @JoinColumn(name = "id_tipo_requerimiento", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoRequerimiento idTipoRequerimiento;
    @JoinColumn(name = "id_tipo_solucion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoSolucion idTipoSolucion;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Long id) {
        this.id = id;
    }

    public DetalleSolicitud(Long id, Date fechaInicio) {
        this.id = id;
        this.fechaInicio = fechaInicio;
    }
    
    public DetalleSolicitud(Long id, Date fechaInicio, Date fechaFin, Date fechaLimite) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimite = fechaLimite;
    }

    public DetalleSolicitud(SolicitudRequerimiento idSolicitudRequerimiento) {
        this.idSolicitudRequerimiento = idSolicitudRequerimiento;
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

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
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
