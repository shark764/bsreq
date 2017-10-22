package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Area;
import org.itca.requerimientos.model.entities.DetalleSolicitud;
import org.itca.requerimientos.model.entities.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(SolicitudRequerimiento.class)
public class SolicitudRequerimiento_ { 

    public static volatile SingularAttribute<SolicitudRequerimiento, Date> fecha;
    public static volatile SingularAttribute<SolicitudRequerimiento, Area> idArea;
    public static volatile SingularAttribute<SolicitudRequerimiento, Empleado> idEmpleado;
    public static volatile SingularAttribute<SolicitudRequerimiento, Long> id;
    public static volatile ListAttribute<SolicitudRequerimiento, DetalleSolicitud> detalleSolicitudList;

}