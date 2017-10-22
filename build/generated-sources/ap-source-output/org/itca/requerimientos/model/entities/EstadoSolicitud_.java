package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.DetalleSolicitud;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(EstadoSolicitud.class)
public class EstadoSolicitud_ { 

    public static volatile SingularAttribute<EstadoSolicitud, String> codigo;
    public static volatile SingularAttribute<EstadoSolicitud, Short> id;
    public static volatile ListAttribute<EstadoSolicitud, DetalleSolicitud> detalleSolicitudList;
    public static volatile SingularAttribute<EstadoSolicitud, String> nombre;

}