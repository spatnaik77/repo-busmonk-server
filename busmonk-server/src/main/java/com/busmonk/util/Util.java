package com.busmonk.util;

import com.busmonk.service.bo.User;

import java.security.MessageDigest;

/**
 * Created by sr250345 on 11/17/16.
 */
public class Util {

    public static String getHashedPassword(String password)
    {
        MessageDigest md = null;
        try
        {
             md = MessageDigest.getInstance("MD5");

        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        //Add password bytes to digest
        md.update(password.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        String hashedPassword = sb.toString();

        return hashedPassword;
    }


}
