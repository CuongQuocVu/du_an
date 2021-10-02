/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.helper;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class XImage {

    // biểu tượng ứng dụng, xuất hiện trên mọi cửa sổ    
    public static Image getAppIcon(){
        // tai bieu tuong ung dung
        URL url = XImage.class.getResource("com/EduSys/icon/fpt.png");       
        return new ImageIcon(url).getImage();
    }

    //sao chep file logo chuyende vao thu muc logo
    public static void saveLogo(File file) {
        
        File dst = new File("img", file.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }

        try {
            //coppy vao thu muc img, de neu da ton tai
            Path from = Paths.get(file.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //doc hinh anh logo chuyen de
    public static ImageIcon readLogo(String fileName) {
        File path = new File("img", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
