package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.Equipo;
import org.itca.requerimientos.model.entities.MarcaEquipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(ModeloEquipo.class)
public class ModeloEquipo_ { 

    public static volatile SingularAttribute<ModeloEquipo, String> codigo;
    public static volatile ListAttribute<ModeloEquipo, Equipo> equipoList;
    public static volatile SingularAttribute<ModeloEquipo, Short> id;
    public static volatile SingularAttribute<ModeloEquipo, MarcaEquipo> idMarca;
    public static volatile SingularAttribute<ModeloEquipo, String> nombre;

}