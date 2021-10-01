/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.helper;

import javax.swing.ImageIcon;

import com.polypro.model.NhanVien;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/**
 *
 * @author admin
 */
public class ShareHelper {
    // biểu tượng ứng dụng, xuất hiện trên mọi cửa sổ
    public static final Image APP_ICON;
    static{
        // tai bieu tuong ung dung
        String file = "com/polypro/icon/fpt.png";
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();        
    }

    //sao chep file logo chuyende vao thu muc logo
    public static boolean saveLogo(File file){
        File dir = new File("img");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File newfile = new File(dir, file.getName());
        try {
            //coppy vao thu muc img, de neu da ton tai
            Path source = Paths.get(file.getAbsolutePath());
            Path  destination = Paths.get(newfile.getCanonicalPath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;            
        }
    }

    //doc hinh anh logo chuyen de
    public static ImageIcon readLogo(String filename){
        File path = new File("img", filename);
        return new ImageIcon(path.getAbsolutePath());
    }

    //doi tuong nay chua thong tin nguoi su dung sau khi dang nhap
    public static NhanVien USER = null;

    //Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void logoOff(){
        ShareHelper.USER = null;
    }

    //Kiểm tra xem đăng nhập hay chưa
    public static boolean authenticated(){
        return ShareHelper.USER != null;
    }

}
