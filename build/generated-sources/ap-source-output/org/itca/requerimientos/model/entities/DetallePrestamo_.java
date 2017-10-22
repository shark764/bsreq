package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Equipo;
import org.itca.requerimientos.model.entities.EstadoPrestamo;
import org.itca.requerimientos.model.entities.Prestamo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(DetallePrestamo.class)
public class DetallePrestamo_ { 

    public static volatile SingularAttribute<DetallePrestamo, String> descripcion;
    public static volatile SingularAttribute<DetallePrestamo, Equipo> idEquipo;
    public static volatile SingularAttribute<DetallePrestamo, Date> fechaPrestamo;
    public static volatile SingularAttribute<DetallePrestamo, Date> fechaEntrega;
    public static volatile SingularAttribute<DetallePrestamo, EstadoPrestamo> idEstadoPrestamo;
    public static volatile SingularAttribute<DetallePrestamo, Prestamo> idPrestamo;
    public static volatile SingularAttribute<DetallePrestamo, Long> id;

}