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
public class SolicitudFallaJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String t05codigo;
    private String t05nombre;
    private String t04codigo;
    private String t04nombre;
    private String t01codigo;
    private String t01nombre;
    private Long t02fallas;

    public SolicitudFallaJasper() {
    }

    public SolicitudFallaJasper(String t05codigo, String t05nombre, String t04codigo, String t04nombre, String t01codigo, String t01nombre, Long t02fallas) {
        this.t05codigo = t05codigo;
        this.t05nombre = t05nombre;
        this.t04codigo = t04codigo;
        this.t04nombre = t04nombre;
        this.t01codigo = t01codigo;
        this.t01nombre = t01nombre;
        this.t02fallas = t02fallas;
    }

    public String getT05codigo() {
        return t05codigo;
    }

    public void setT05codigo(String t05codigo) {
        this.t05codigo = t05codigo;
    }

    public String getT05nombre() {
        return t05nombre;
    }

    public void setT05nombre(String t05nombre) {
        this.t05nombre = t05nombre;
    }

    public String getT04codigo() {
        return t04codigo;
    }

    public void setT04codigo(String t04codigo) {
        this.t04codigo = t04codigo;
    }

    public String getT04nombre() {
        return t04nombre;
    }

    public void setT04nombre(String t04nombre) {
        this.t04nombre = t04nombre;
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

    public Long getT02fallas() {
        return t02fallas;
    }

    public void setT02fallas(Long t02fallas) {
        this.t02fallas = t02fallas;
    }

    @Override
    public String toString() {
        return "[" + this.t02fallas + "] " + this.t01nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}