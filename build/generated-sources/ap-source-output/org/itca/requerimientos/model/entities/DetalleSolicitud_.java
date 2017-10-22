package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Empleado;
import org.itca.requerimientos.model.entities.Equipo;
import org.itca.requerimientos.model.entities.EstadoSolicitud;
import org.itca.requerimientos.model.entities.InsumoUtilizado;
import org.itca.requerimientos.model.entities.SolicitudRequerimiento;
import org.itca.requerimientos.model.entities.TipoFalla;
import org.itca.requerimientos.model.entities.TipoRequerimiento;
import org.itca.requerimientos.model.entities.TipoSolucion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(DetalleSolicitud.class)
public class DetalleSolicitud_ { 

    public static volatile SingularAttribute<DetalleSolicitud, String> descripcion;
    public static volatile ListAttribute<DetalleSolicitud, InsumoUtilizado> insumoUtilizadoList;
    public static volatile SingularAttribute<DetalleSolicitud, Equipo> idEquipo;
    public static volatile SingularAttribute<DetalleSolicitud, TipoRequerimiento> idTipoRequerimiento;
    public static volatile SingularAttribute<DetalleSolicitud, Empleado> idTecnicoAsignado;
    public static volatile SingularAttribute<DetalleSolicitud, String> comentario;
    public static volatile SingularAttribute<DetalleSolicitud, SolicitudRequerimiento> idSolicitudRequerimiento;
    public static volatile SingularAttribute<DetalleSolicitud, Date> fechaFin;
    public static volatile SingularAttribute<DetalleSolicitud, EstadoSolicitud> idEstadoSolicitud;
    public static volatile SingularAttribute<DetalleSolicitud, TipoFalla> idTipoFalla;
    public static volatile SingularAttribute<DetalleSolicitud, TipoSolucion> idTipoSolucion;
    public static volatile SingularAttribute<DetalleSolicitud, Date> fechaInicio;
    public static volatile SingularAttribute<DetalleSolicitud, Long> id;

}