package org.itca.requerimientos.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itca.requerimientos.model.entities.ModeloEquipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T13:57:01")
@StaticMetamodel(MarcaEquipo.class)
public class MarcaEquipo_ { 

    public static volatile SingularAttribute<MarcaEquipo, String> codigo;
    public static volatile ListAttribute<MarcaEquipo, ModeloEquipo> modeloEquipoList;
    public static volatile SingularAttribute<MarcaEquipo, Short> id;
    public static volatile SingularAttribute<MarcaEquipo, String> nombre;

}