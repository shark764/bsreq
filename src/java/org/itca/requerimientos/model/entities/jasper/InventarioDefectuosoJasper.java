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
public class InventarioDefectuosoJasper implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Short t01id;
    private String t01codigo;
    private String t01nombre;
    private Short t02id;
    private String t02codigo;
    private String t02nombre;
    private Short t02existencia;
    private Long t03id;
    private String t03nombre;
    private String t03inventario;
    private String t03serie;
    private Date t04fecha;

    public InventarioDefectuosoJasper() {
    }

    public InventarioDefectuosoJasper(Short t01id, String t01codigo, String t01nombre, Short t02id, String t02codigo, String t02nombre, Short t02existencia, Long t03id, String t03nombre, String t03inventario, String t03serie, Date t04fecha) {
        this.t01id = t01id;
        this.t01codigo = t01codigo;
        this.t01nombre = t01nombre;
        this.t02id = t02id;
        this.t02codigo = t02codigo;
        this.t02nombre = t02nombre;
        this.t02existencia = t02existencia;
        this.t03id = t03id;
        this.t03nombre = t03nombre;
        this.t03inventario = t03inventario;
        this.t03serie = t03serie;
        this.t04fecha = t04fecha;
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

    public Long getT03id() {
        return t03id;
    }

    public void setT03id(Long t03id) {
        this.t03id = t03id;
    }

    public String getT03nombre() {
        return t03nombre;
    }

    public void setT03nombre(String t03nombre) {
        this.t03nombre = t03nombre;
    }

    public String getT03inventario() {
        return t03inventario;
    }

    public void setT03inventario(String t03inventario) {
        this.t03inventario = t03inventario;
    }

    public String getT03serie() {
        return t03serie;
    }

    public void setT03serie(String t03serie) {
        this.t03serie = t03serie;
    }

    public Date getT04fecha() {
        return t04fecha;
    }

    public void setT04fecha(Date t04fecha) {
        this.t04fecha = t04fecha;
    }

    @Override
    public String toString() {
        return "[" + this.t02codigo + "] " + this.t03nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}