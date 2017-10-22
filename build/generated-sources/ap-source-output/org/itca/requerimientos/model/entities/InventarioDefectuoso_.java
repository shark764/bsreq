package org.itca.requerimientos.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Equipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(InventarioDefectuoso.class)
public class InventarioDefectuoso_ { 

    public static volatile SingularAttribute<InventarioDefectuoso, String> descripcion;
    public static volatile SingularAttribute<InventarioDefectuoso, Date> fechaIngreso;
    public static volatile SingularAttribute<InventarioDefectuoso, Equipo> idEquipo;
    public static volatile SingularAttribute<InventarioDefectuoso, Long> id;

}