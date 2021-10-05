/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.dao;

import com.EduSys.helper.JDBC_Helper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ThongKeDao {

    public List<Object[]> getNguoiHoc() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeNguoiHoc}";
                rs = JDBC_Helper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt("Nam"),
                        rs.getInt("SoLuong"),
                        rs.getDate("DauTien"),
                        rs.getDate("CuoiCung")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }

    public List<Object[]> getBangDiem(Integer makh) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_BangDiem (?)}";
                rs = JDBC_Helper.executeQuery(sql, makh);
                while (rs.next()) {
                    double diem = rs.getDouble("Diem");
                    String xepLoai = "Xuất sắc";
                    if (diem < 0) {
                        xepLoai = "Chưa nhập";

                    } else if (diem < 3) {
                        xepLoai = "Kém";

                    } else if (diem < 5) {
                        xepLoai = "Yếu";

                    } else if (diem < 6.5) {

                        xepLoai = "Trung bình";

                    } else if (diem < 7.5) {
                        xepLoai = "Khá";

                    } else if (diem < 9) {
                        xepLoai = "Giỏi";

                    }
                    Object[] model = {
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        diem,
                        xepLoai
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getDiemTheoChuyenDe() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDiem}";
                rs = JDBC_Helper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoHV"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getDoanhThu(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu (?)}";
                rs = JDBC_Helper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoKH"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
       public List<Integer> getNamKhaiGiang() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC_Helper.executeQuery("select distinct year(NgayKG) as nam from KhoaHoc order by year(NgayKG) desc");
                while (rs.next()) {
                    int nam = rs.getInt(1);
                    list.add(nam);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
