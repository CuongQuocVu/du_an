/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.dao;



import com.polypro.helper.JBDC_Helper;
import com.polypro.model.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NhanVienDao {
    
    private void insert(NhanVien model){
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        JBDC_Helper.excuteUpdate(sql, model.getMaNV(), model.getMatKhau(), model.getHoTen(), model.getVaiTro());
    }
    private void update(NhanVien model){
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
        JBDC_Helper.excuteUpdate(sql, model.getMatKhau(), model.getHoTen(), model.getVaiTro(), model.getMaNV());
    }
    private void delete(String MaNV){
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JBDC_Helper.excuteUpdate(sql, MaNV);
    }
    private List<NhanVien> select(){
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }
    
    private NhanVien findById(String MaNV){
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> listNV = select(sql, MaNV);
        return listNV.size() > 0 ?  listNV.get(0) : null;
    }
    
    private List<NhanVien> select(String sql, Object args){
        List<NhanVien> listNV = new ArrayList<>();
        try {
            ResultSet rs = null;
            try{
                rs = JBDC_Helper.excuteQuery(sql, args);
                while(rs.next()){
                    NhanVien model = readFromResultSet(rs);
                    listNV.add(model);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listNV;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException{
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setHoTen(rs.getString("HoTen"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
}
