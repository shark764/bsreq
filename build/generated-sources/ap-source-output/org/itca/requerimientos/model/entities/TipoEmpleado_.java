package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(TipoEmpleado.class)
public class TipoEmpleado_ { 

    public static volatile SingularAttribute<TipoEmpleado, String> codigo;
    public static volatile ListAttribute<TipoEmpleado, Empleado> empleadoList;
    public static volatile SingularAttribute<TipoEmpleado, Short> id;
    public static volatile SingularAttribute<TipoEmpleado, String> nombre;

}