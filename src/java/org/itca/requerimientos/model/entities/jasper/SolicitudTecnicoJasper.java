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
public class SolicitudTecnicoJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String t04tecnico;
    private String t01codigo;
    private String t01nombre;
    private String t03codigo;
    private String t03nombre;
    private Long t02mantenimientos;

    public SolicitudTecnicoJasper() {
    }

    public SolicitudTecnicoJasper(String t04tecnico, String t01codigo, String t01nombre, String t03codigo, String t03nombre, Long t02mantenimientos) {
        this.t04tecnico = t04tecnico;
        this.t01codigo = t01codigo;
        this.t01nombre = t01nombre;
        this.t03codigo = t03codigo;
        this.t03nombre = t03nombre;
        this.t02mantenimientos = t02mantenimientos;
    }

    public String getT04tecnico() {
        return t04tecnico;
    }

    public void setT04tecnico(String t04tecnico) {
        this.t04tecnico = t04tecnico;
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

    public String getT03codigo() {
        return t03codigo;
    }

    public void setT03codigo(String t03codigo) {
        this.t03codigo = t03codigo;
    }

    public String getT03nombre() {
        return t03nombre;
    }

    public void setT03nombre(String t03nombre) {
        this.t03nombre = t03nombre;
    }

    public Long getT02mantenimientos() {
        return t02mantenimientos;
    }

    public void setT02mantenimientos(Long t02mantenimientos) {
        this.t02mantenimientos = t02mantenimientos;
    }

    @Override
    public String toString() {
        return "[" + this.t02mantenimientos + "] " + this.t04tecnico; //To change body of generated methods, choose Tools | Templates.
    }
    
}