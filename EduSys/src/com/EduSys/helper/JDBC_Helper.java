/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class JDBC_Helper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=EduSys";
    private static String username = "sa";
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
    public static PreparedStatement preparedStatement(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement pstm = null;
        if(sql.trim().startsWith("{")){
            pstm = connection.prepareCall(sql);            
        } else {
            pstm = connection.prepareStatement(sql);
        } 
        for(int i = 0; i < args.length; i++){
            pstm.setObject(i+1, args[i]);
        }
        return pstm;
    }
    // cau lenh exxcuteUpdate
    public static int executeUpdate(String sql, Object...args){
        try {
            PreparedStatement pstm = JDBC_Helper.preparedStatement(sql, args);
            try{
                return pstm.executeUpdate();
            } 
            finally{
                pstm.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    // cau lenh select 
    public static ResultSet executeQuery(String sql, Object...args){
        try {
            PreparedStatement pstm = JDBC_Helper.preparedStatement(sql, args);
            return pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    // câu lệnh value
    public static Object value(String sql, Object...args){
        try {
            ResultSet rs = JDBC_Helper.executeQuery(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


