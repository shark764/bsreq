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
public class SolicitudEquipoJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String t01codigo;
    private String t01nombre;
    private String t02codigo;
    private String t02nombre;
    private Long t04conteo;

    public SolicitudEquipoJasper() {
    }

    public SolicitudEquipoJasper(String t01codigo, String t01nombre, String t02codigo, String t02nombre, Long t04conteo) {
        this.t01codigo = t01codigo;
        this.t01nombre = t01nombre;
        this.t02codigo = t02codigo;
        this.t02nombre = t02nombre;
        this.t04conteo = t04conteo;
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

    public Long getT04conteo() {
        return t04conteo;
    }

    public void setT04conteo(Long t04conteo) {
        this.t04conteo = t04conteo;
    }

    @Override
    public String toString() {
        return "[" + this.t04conteo + "] " + this.t02nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}