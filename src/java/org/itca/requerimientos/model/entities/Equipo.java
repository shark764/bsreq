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
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findById", query = "SELECT e FROM Equipo e WHERE e.id = :id"),
    @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipo.findBySerie", query = "SELECT e FROM Equipo e WHERE e.serie = :serie"),
    @NamedQuery(name = "Equipo.findByInventario", query = "SELECT e FROM Equipo e WHERE e.inventario = :inventario"),
    @NamedQuery(name = "Equipo.findByExistencia", query = "SELECT e FROM Equipo e WHERE e.existencia = :existencia"),
    @NamedQuery(name = "Equipo.findByFechaAdquisicion", query = "SELECT e FROM Equipo e WHERE e.fechaAdquisicion = :fechaAdquisicion"),
    @NamedQuery(name = "Equipo.findByIp", query = "SELECT e FROM Equipo e WHERE e.ip = :ip"),
    @NamedQuery(name = "Equipo.findByDescripcion", query = "SELECT e FROM Equipo e WHERE e.descripcion = :descripcion")})
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_equipo",
            table = "secuencia",
            pkColumnName = "secuencia",
            valueColumnName = "valor",
            pkColumnValue = "sec_equipo")
    @Id
    @GeneratedValue(generator = "sec_equipo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "serie")
    private String serie;
    @Size(max = 45)
    @Column(name = "inventario")
    private String inventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencia")
    private short existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_adquisicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdquisicion;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<DetallePrestamo> detallePrestamoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<InventarioDefectuoso> inventarioDefectuosoList;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area idArea;
    @JoinColumn(name = "id_categoria_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaEquipo idCategoriaEquipo;
    @OneToMany(mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo idEquipo;
    @JoinColumn(name = "id_estado_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoEquipo idEstadoEquipo;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ModeloEquipo idModelo;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor idProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detalleSolicitudList;

    public Equipo() {
    }

    public Equipo(Long id) {
        this.id = id;
    }

    public Equipo(Long id, String nombre, String serie, short existencia, Date fechaAdquisicion) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.existencia = existencia;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public short getExistencia() {
        return existencia;
    }

    public void setExistencia(short existencia) {
        this.existencia = existencia;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DetallePrestamo> getDetallePrestamoList() {
        return detallePrestamoList;
    }

    public void setDetallePrestamoList(List<DetallePrestamo> detallePrestamoList) {
        this.detallePrestamoList = detallePrestamoList;
    }

    @XmlTransient
    public List<InventarioDefectuoso> getInventarioDefectuosoList() {
        return inventarioDefectuosoList;
    }

    public void setInventarioDefectuosoList(List<InventarioDefectuoso> inventarioDefectuosoList) {
        this.inventarioDefectuosoList = inventarioDefectuosoList;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public CategoriaEquipo getIdCategoriaEquipo() {
        return idCategoriaEquipo;
    }

    public void setIdCategoriaEquipo(CategoriaEquipo idCategoriaEquipo) {
        this.idCategoriaEquipo = idCategoriaEquipo;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public EstadoEquipo getIdEstadoEquipo() {
        return idEstadoEquipo;
    }

    public void setIdEstadoEquipo(EstadoEquipo idEstadoEquipo) {
        this.idEstadoEquipo = idEstadoEquipo;
    }

    public ModeloEquipo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(ModeloEquipo idModelo) {
        this.idModelo = idModelo;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
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
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.inventario + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.Equipo[ id=" + id + " ]";
    }
    
}
