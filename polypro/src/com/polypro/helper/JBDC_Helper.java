/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.helper;

/**
 *
 * @author admin
 */
public class JBDC_Helper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=polypro";
    private static String usename = "sa";
    private static String password = "123";
    
    //nạp driver
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    // xây dựng prepared statement
    
    
}
