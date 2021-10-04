/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.dao;

import com.EduSys.helper.JDBC_Helper;
import com.EduSys.model.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NhanVienDao {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        JDBC_Helper.executeUpdate(sql, model.getMaNV(), model.getMatKhau(), model.getHoTen(), model.getVaiTro());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
        JDBC_Helper.executeUpdate(sql, model.getMatKhau(), model.getHoTen(), model.getVaiTro(), model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JDBC_Helper.executeUpdate(sql, MaNV);
    }

    private List<NhanVien> select(String sql, Object args) {
        List<NhanVien> listNV = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC_Helper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
                    listNV.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listNV;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setHoTen(rs.getString("HoTen"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }

    public NhanVien findByID(String MaNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> listNV = select(sql, MaNV);
        if(listNV.isEmpty()){
            return null;
        }
        return listNV.get(0);
    }

    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.select(sql,this);
    }

}
