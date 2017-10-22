package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> iduser;
    public static volatile SingularAttribute<Usuarios, String> clave;
    public static volatile SingularAttribute<Usuarios, Integer> estado;
    public static volatile SingularAttribute<Usuarios, String> nombUser;
    public static volatile SingularAttribute<Usuarios, Empleado> idEmpleado;

}