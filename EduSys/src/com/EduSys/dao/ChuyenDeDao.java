/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.EduSys.helper.JDBC_Helper;
import com.EduSys.model.ChuyenDe;

/**
 *
 * @author admin
 */
public class ChuyenDeDao {
    public void insert(ChuyenDe model){
        String sql = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?,?, ?, ?, ?)";
        JDBC_Helper.executeUpdate(sql, model.getMaCD(), model.getTenCD(), model.getHocPhi(), model.getThoiLuong(),
            model.getHinh(), model.getMoTa());             
    }

    public void update(ChuyenDe model){
        String sql = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
        JDBC_Helper.executeUpdate(sql, model.getTenCD(), model.getHocPhi(), model.getThoiLuong(), model.getHinh(),
            model.getMoTa(), model.getMaCD());
    }

    public void delete(String MaCD){
        String sql = "DELETE FROM ChuyenDe WHERE MaCD=?";
        JDBC_Helper.executeUpdate(sql, MaCD);
    }

    public List<ChuyenDe> selectAll(){
        String sql = "SELECT * FROM ChuyenDe";
        return select(sql);
    }
    
    public ChuyenDe findById(String MaCD){
        String sql = "SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> listCD = select(sql, MaCD);
        return listCD.size() > 0 ? listCD.get(0) : null;
    }

    private ChuyenDe readFromResultSet(ResultSet rs) throws SQLException{
        ChuyenDe model = new ChuyenDe();
        model.setMaCD(rs.getString("MaCD"));
        model.setHinh(rs.getString("Hinh"));
        model.setHocPhi(rs.getDouble("HocPhi"));
        model.setMoTa(rs.getString("MoTa"));
        model.setTenCD(rs.getString("TenCD"));
        model.setThoiLuong(rs.getInt("ThoiLuong"));
        return model;
    }

    private List<ChuyenDe> select(String sql, Object...args){
        List<ChuyenDe> listCD = new ArrayList<>();
        try {
            ResultSet rs = null;
            try{
                rs = JDBC_Helper.executeQuery(sql, args);
                while(rs.next()){
                    ChuyenDe model = readFromResultSet(rs);
                    listCD.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCD;
    }
}
