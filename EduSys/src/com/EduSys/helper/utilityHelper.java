/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.helper;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sieu Nhan Bay
 */
public class utilityHelper {

    public static String getRank(double diem) {
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
        return xepLoai;
    }
    public static boolean checkDiem(JTextField txt) {
        txt.setBackground(white);
        try {
            float hp = Float.parseFloat(txt.getText());
            if ((hp >= 0 && hp <= 10)||hp==-1) {
                return true;
            } else {
                txt.setBackground(pink);
                DialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là trong khoảng 0-10 hoặc chưa nhập (-1).");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            DialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là số thực.");
            return false;
        }
    }
    


    public static boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

 
}
