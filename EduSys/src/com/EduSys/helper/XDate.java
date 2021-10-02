/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class XDate {
    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("MM/dd/yyyy");
    
    // convert String to Date
    public static Date toDate(String date, String...pattern){
        try {
            if(pattern.length > 0){
                DATE_FORMATER.applyPattern(pattern[0]);
            }
            if(date == null){
                return XDate.now();
            }
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
// convert Date to String    
    public static String toString(Date date, String...pattern){
        if(pattern.length > 0){
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if(date == null){
            date = XDate.now();
        }
        return DATE_FORMATER.format(date);
    }
    
    // lấy thời gian hiện tại
    public static Date now(){
        return new Date();
    }
    
    // bổ sung số ngày vào thời gian
    public static Date addDays(Date date, int days){
        date.setTime(date.getTime() + days * 24 * 60 * 1000);
        return date;
    }
    
    // bổ sung số ngày vào thời gian hiện hành
    public static Date add(int days){
        Date now = XDate.now();
        now.setTime(now.getTime() + days * 24 * 60 * 1000);
        return now;
    }
}
