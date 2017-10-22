package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Equipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(CategoriaEquipo.class)
public class CategoriaEquipo_ { 

    public static volatile SingularAttribute<CategoriaEquipo, String> codigo;
    public static volatile ListAttribute<CategoriaEquipo, Equipo> equipoList;
    public static volatile SingularAttribute<CategoriaEquipo, Short> id;
    public static volatile SingularAttribute<CategoriaEquipo, String> nombre;

}