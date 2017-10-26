/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dfhernandez
 */
public abstract class DateUtil {
    
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * * Returns the current time as a String.
     * @param date
     * @return 
     */
    public static String getDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * * Returns the current time as a String.
     * @param date
     * @return 
     */
    public static Date getDate(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
}