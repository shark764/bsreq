/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities.jasper;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dfhernandez
 */
public class RetrasoPrestamoJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String t04codigo;
    private String t04nombre;
    private String t01nombre;
    private Date t02fechaprestamo;
    private Date t02fechaentrega;
    private Integer t02retraso;

    public RetrasoPrestamoJasper() {
    }

    public RetrasoPrestamoJasper(String t04codigo, String t04nombre, String t01nombre, Date t02fechaprestamo, Date t02fechaentrega, Integer t02retraso) {
        this.t04codigo = t04codigo;
        this.t04nombre = t04nombre;
        this.t01nombre = t01nombre;
        this.t02fechaprestamo = t02fechaprestamo;
        this.t02fechaentrega = t02fechaentrega;
        this.t02retraso = t02retraso;
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

    public String getT01nombre() {
        return t01nombre;
    }

    public void setT01nombre(String t01nombre) {
        this.t01nombre = t01nombre;
    }

    public Date getT02fechaprestamo() {
        return t02fechaprestamo;
    }

    public void setT02fechaprestamo(Date t02fechaprestamo) {
        this.t02fechaprestamo = t02fechaprestamo;
    }

    public Date getT02fechaentrega() {
        return t02fechaentrega;
    }

    public void setT02fechaentrega(Date t02fechaentrega) {
        this.t02fechaentrega = t02fechaentrega;
    }

    public Integer getT02retraso() {
        return t02retraso;
    }

    public void setT02retraso(Integer t02retraso) {
        this.t02retraso = t02retraso;
    }

    @Override
    public String toString() {
        return "[" + this.t02retraso + "] " + this.t01nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}