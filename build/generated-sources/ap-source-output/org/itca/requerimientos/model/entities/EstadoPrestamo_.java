package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.DetallePrestamo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(EstadoPrestamo.class)
public class EstadoPrestamo_ { 

    public static volatile ListAttribute<EstadoPrestamo, DetallePrestamo> detallePrestamoList;
    public static volatile SingularAttribute<EstadoPrestamo, String> codigo;
    public static volatile SingularAttribute<EstadoPrestamo, Short> id;
    public static volatile SingularAttribute<EstadoPrestamo, String> nombre;

}