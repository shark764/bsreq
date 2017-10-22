package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Area;
import org.itca.requerimientos.model.entities.CategoriaEquipo;
import org.itca.requerimientos.model.entities.DetallePrestamo;
import org.itca.requerimientos.model.entities.DetalleSolicitud;
import org.itca.requerimientos.model.entities.Equipo;
import org.itca.requerimientos.model.entities.EstadoEquipo;
import org.itca.requerimientos.model.entities.InventarioDefectuoso;
import org.itca.requerimientos.model.entities.ModeloEquipo;
import org.itca.requerimientos.model.entities.Proveedor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, Date> fechaAdquisicion;
    public static volatile SingularAttribute<Equipo, String> descripcion;
    public static volatile SingularAttribute<Equipo, Equipo> idEquipo;
    public static volatile ListAttribute<Equipo, InventarioDefectuoso> inventarioDefectuosoList;
    public static volatile SingularAttribute<Equipo, Area> idArea;
    public static volatile SingularAttribute<Equipo, String> ip;
    public static volatile SingularAttribute<Equipo, String> nombre;
    public static volatile SingularAttribute<Equipo, EstadoEquipo> idEstadoEquipo;
    public static volatile SingularAttribute<Equipo, Short> existencia;
    public static volatile ListAttribute<Equipo, DetallePrestamo> detallePrestamoList;
    public static volatile SingularAttribute<Equipo, Proveedor> idProveedor;
    public static volatile SingularAttribute<Equipo, String> serie;
    public static volatile ListAttribute<Equipo, Equipo> equipoList;
    public static volatile SingularAttribute<Equipo, Long> id;
    public static volatile SingularAttribute<Equipo, CategoriaEquipo> idCategoriaEquipo;
    public static volatile SingularAttribute<Equipo, ModeloEquipo> idModelo;
    public static volatile ListAttribute<Equipo, DetalleSolicitud> detalleSolicitudList;
    public static volatile SingularAttribute<Equipo, String> inventario;

}