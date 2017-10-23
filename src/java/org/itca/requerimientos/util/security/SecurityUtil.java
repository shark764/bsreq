/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
//import sun.misc.CharacterEncoder;

/**
 *
 * @author dfhernandez
 */
public final class SecurityUtil {

    private static SecurityUtil instance;

    public SecurityUtil() {
    }

    public synchronized String cifrar(String plaintext) throws SystemUnavailableException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256"); //step 2 
        } catch (NoSuchAlgorithmException e) {
            throw new SystemUnavailableException(e.getMessage());
        }
        try {
            md.update(plaintext.getBytes("UTF-8")); //step 3 
        } catch (UnsupportedEncodingException e) {
            throw new SystemUnavailableException(e.getMessage());
        }
        byte raw[] = md.digest();//step 4 
        String hash = (new BASE64Encoder()).encode(raw);//step 5 
        return hash;//step 6 
    }

    public static synchronized SecurityUtil getInstance() //step 1 
    {
        if (instance == null) {
            instance = new SecurityUtil();
        }
        return instance;
    }

}