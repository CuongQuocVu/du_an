/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.model;

import com.polypro.helper.XDate;
import java.util.Date;

/**
 *
 * @author admin
 */
public class KhoaHoc {
    private int maKh;
    private String maCD;
    private double hocPhi;
    private int thoiLuonog;
    private Date ngayKG;
    private String ghiChu;
    private String maNv;
    private Date ngayTao = XDate.now();

    public KhoaHoc() {
    }

    public KhoaHoc(int maKh, String maCD, double hocPhi, int thoiLuonog, Date ngayKG, String ghiChu, String maNv) {
        this.maKh = maKh;
        this.maCD = maCD;
        this.hocPhi = hocPhi;
        this.thoiLuonog = thoiLuonog;
        this.ngayKG = ngayKG;
        this.ghiChu = ghiChu;
        this.maNv = maNv;
    }

    @Override
    public String toString() {
        return this.maCD + "(" + this.ngayKG +")";
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuonog() {
        return thoiLuonog;
    }

    public void setThoiLuonog(int thoiLuonog) {
        this.thoiLuonog = thoiLuonog;
    }

    public Date getNgayKG() {
        return ngayKG;
    }

    public void setNgayKG(Date ngayKG) {
        this.ngayKG = ngayKG;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
    
}
