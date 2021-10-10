/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.helper;

import com.EduSys.model.NhanVien;

/**
 *
 * @author admin
 */
public class ShareHelper {

    //doi tuong nay chua thong tin nguoi su dung sau khi dang nhap
    public static NhanVien USER = null;

    //Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void logoOff(){
        ShareHelper.USER = null;
    }

    //Kiểm tra xem đăng nhập hay chưa
    public static boolean auth(){
        return ShareHelper.USER != null;
    }
    
    public static boolean isManager(){
        return ShareHelper.auth() && USER.getVaiTro();
    }

}
