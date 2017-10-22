package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Area;
import org.itca.requerimientos.model.entities.DetalleSolicitud;
import org.itca.requerimientos.model.entities.Empleado;
import org.itca.requerimientos.model.entities.Prestamo;
import org.itca.requerimientos.model.entities.SolicitudRequerimiento;
import org.itca.requerimientos.model.entities.TipoEmpleado;
import org.itca.requerimientos.model.entities.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile ListAttribute<Empleado, Usuarios> usuariosList;
    public static volatile SingularAttribute<Empleado, Area> idArea;
    public static volatile ListAttribute<Empleado, Empleado> empleadoList;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile ListAttribute<Empleado, Prestamo> prestamoList;
    public static volatile ListAttribute<Empleado, SolicitudRequerimiento> solicitudRequerimientoList;
    public static volatile SingularAttribute<Empleado, Empleado> idEmpleado;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, Character> genero;
    public static volatile SingularAttribute<Empleado, String> nit;
    public static volatile SingularAttribute<Empleado, String> dui;
    public static volatile SingularAttribute<Empleado, TipoEmpleado> idTipoEmpleado;
    public static volatile SingularAttribute<Empleado, Integer> id;
    public static volatile ListAttribute<Empleado, DetalleSolicitud> detalleSolicitudList;

}