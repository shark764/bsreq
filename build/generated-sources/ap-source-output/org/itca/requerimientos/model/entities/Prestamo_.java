package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Area;
import org.itca.requerimientos.model.entities.DetallePrestamo;
import org.itca.requerimientos.model.entities.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Date> fecha;
    public static volatile ListAttribute<Prestamo, DetallePrestamo> detallePrestamoList;
    public static volatile SingularAttribute<Prestamo, Area> idArea;
    public static volatile SingularAttribute<Prestamo, Empleado> idEmpleado;
    public static volatile SingularAttribute<Prestamo, Long> id;

}