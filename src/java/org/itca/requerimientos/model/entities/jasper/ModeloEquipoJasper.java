/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities.jasper;

import java.io.Serializable;

/**
 *
 * @author dfhernandez
 */
public class ModeloEquipoJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Short t01id;
    private String t01codigo;
    private String t01nombre;
    private Short t02id;
    private String t02codigo;
    private String t02nombre;
    private Short t02existencia;

    public ModeloEquipoJasper() {
    }

    public ModeloEquipoJasper(Short t01id, String t01codigo, String t01nombre, Short t02id, String t02codigo, String t02nombre, Short t02existencia) {
        this.t01id = t01id;
        this.t01codigo = t01codigo;
        this.t01nombre = t01nombre;
        this.t02id = t02id;
        this.t02codigo = t02codigo;
        this.t02nombre = t02nombre;
        this.t02existencia = t02existencia;
    }

    public Short getT01id() {
        return t01id;
    }

    public void setT01id(Short t01id) {
        this.t01id = t01id;
    }

    public String getT01codigo() {
        return t01codigo;
    }

    public void setT01codigo(String t01codigo) {
        this.t01codigo = t01codigo;
    }

    public String getT01nombre() {
        return t01nombre;
    }

    public void setT01nombre(String t01nombre) {
        this.t01nombre = t01nombre;
    }

    public Short getT02id() {
        return t02id;
    }

    public void setT02id(Short t02id) {
        this.t02id = t02id;
    }

    public String getT02codigo() {
        return t02codigo;
    }

    public void setT02codigo(String t02codigo) {
        this.t02codigo = t02codigo;
    }

    public String getT02nombre() {
        return t02nombre;
    }

    public void setT02nombre(String t02nombre) {
        this.t02nombre = t02nombre;
    }

    public Short getT02existencia() {
        return t02existencia;
    }

    public void setT02existencia(Short t02existencia) {
        this.t02existencia = t02existencia;
    }

    @Override
    public String toString() {
        return "[" + this.t02codigo + "] " + this.t02nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}