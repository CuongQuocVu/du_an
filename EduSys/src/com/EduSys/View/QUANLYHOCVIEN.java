package com.EduSys.View;

import com.EduSys.dao.HocVienDao;
import com.EduSys.dao.NguoiHocDao;
import com.EduSys.helper.DialogHelper;
import com.EduSys.helper.JDBC_Helper;
import com.EduSys.helper.ShareHelper;
import com.EduSys.model.HocVien;
import static java.awt.Color.white;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import com.EduSys.model.NguoiHoc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sieu Nhan Bay
 */
public class QUANLYHOCVIEN extends javax.swing.JFrame {

    public int MaKH;
    HocVienDao hvdao = new HocVienDao();
    NguoiHocDao nhdao = new NguoiHocDao();

    public QUANLYHOCVIEN(Integer idKH) {
        initComponents();
        init();
        this.MaKH = idKH;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlHVKhac = new javax.swing.JPanel();
        cboNguoiHoc = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        pnlHVKH = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaNhap = new javax.swing.JRadioButton();
        rdoChuaNhap = new javax.swing.JRadioButton();
        btnCapnhat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ HỌC VIÊN");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlHVKhac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN KHÁC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        cboNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNguoiHocActionPerformed(evt);
            }
        });

        txtDiem.setName("Điểm"); // NOI18N

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHVKhacLayout = new javax.swing.GroupLayout(pnlHVKhac);
        pnlHVKhac.setLayout(pnlHVKhacLayout);
        pnlHVKhacLayout.setHorizontalGroup(
            pnlHVKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHVKhacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnThem)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        pnlHVKhacLayout.setVerticalGroup(
            pnlHVKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHVKhacLayout.createSequentialGroup()
                .addGroup(pnlHVKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlHVKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN CỦA KHÓA HỌC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,  new Boolean(false)},
                {null, null, null, null,  new Boolean(false)},
                {null, null, null, null,  new Boolean(false)},
                {null, null, null, null,  new Boolean(false)}
            },
            new String [] {
                "MÃ HV", "MÃ NH", "HỌ VÀ TÊN", "ĐIỂM (sửa đc)", "XÓA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGridView.setRowHeight(25);
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTatCaMouseClicked(evt);
            }
        });
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaNhap);
        rdoDaNhap.setText("Đã nhập điểm");
        rdoDaNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDaNhapMouseClicked(evt);
            }
        });
        rdoDaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhap);
        rdoChuaNhap.setText("Chưa nhập điểm");
        rdoChuaNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChuaNhapMouseClicked(evt);
            }
        });
        rdoChuaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaNhapActionPerformed(evt);
            }
        });

        btnCapnhat.setText("Cập nhật");
        btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHVKHLayout = new javax.swing.GroupLayout(pnlHVKH);
        pnlHVKH.setLayout(pnlHVKHLayout);
        pnlHVKHLayout.setHorizontalGroup(
            pnlHVKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHVKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHVKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHVKHLayout.createSequentialGroup()
                        .addComponent(rdoTatCa)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDaNhap)
                        .addGap(18, 18, 18)
                        .addComponent(rdoChuaNhap)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHVKHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapnhat)
                .addGap(59, 59, 59))
        );
        pnlHVKHLayout.setVerticalGroup(
            pnlHVKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHVKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlHVKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoDaNhap)
                    .addComponent(rdoChuaNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapnhat)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlHVKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHVKhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHVKhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHVKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void init() {
        setLocationRelativeTo(null);
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNguoiHoc.getModel();
        model.removeAllElements();
        try {
            List<NguoiHoc> list = nhdao.selectByCourse(MaKH);
            for (NguoiHoc nh : list) {
                model.addElement(nh);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên vào combobox!");
        }
    }

    //lấy tất cả đối tượng nguoiHoc không thuộc khoaHoc từ CSDL (theo maKH)
    //rồi thêm vào combobox
    //lấy về MaHV, MaKH, MaNH, Diem, HoTen từ các bảng trong CSDL của các học viên thuộc khóa học (theo maKH)
    //điền các bản ghi tương ứng vào bảng theo: tất cả, chưa nhập điểm, đã nhập điểm
    void fillGridView() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            String sql = "SELECT hv.*, nh.HoTen FROM HocVien hv "
                    + " JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH WHERE MaKH=?";
            ResultSet rs = JDBC_Helper.executeQuery(sql, MaKH);
            while (rs.next()) {
                double diem = rs.getDouble("Diem");
                Object[] row = {
                    rs.getInt("MaHV"), rs.getString("MaNH"),
                    rs.getString("HoTen"), diem, false
                };
                if (rdoTatCa.isSelected()) {
                    model.addRow(row);
                } else if (rdoDaNhap.isSelected() && diem >= 0) {
                    model.addRow(row);
                } else if (rdoChuaNhap.isSelected() && diem < 0) {
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên vào bảng!");
        }
    }


    /*
    thêm đt hocVien vào CSDL bảng hocVien
    load lại combobox, bảng
    MaHV là tự sinh từ 1 đến ... trong CSDL ko cần nhập
    mã tự sinh này là cố định không đổi kể cả khi bản ghi phía trước bị xóa làm stt thay đổi
     */
    void insert() {
        NguoiHoc nguoiHoc = (NguoiHoc) cboNguoiHoc.getSelectedItem();
        HocVien model = new HocVien();

        try {
            if (model.getDiem() <= 10 || model.getDiem() >= 0) {
//                model.setMaKH(MaKH);
//                model.setMaNH(nguoiHoc.getMaNH());
//                model.setDiem(Double.valueOf(txtDiem.getText()));                
//                hvdao.insert(model);                
//                this.fillGridView();
            } else {
                model.setMaKH(MaKH);
                model.setMaNH(nguoiHoc.getMaNH());
                // model.setDiem();
                hvdao.insert2(model);
                //this.fillComboBox();
                this.fillGridView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi thêm học viên vào khóa học!");
        }
    }

    void update() {
        for (int i = 0; i < tblGridView.getRowCount(); i++) {
            Integer maHV = (Integer) tblGridView.getValueAt(i, 0);
            String maNH = (String) tblGridView.getValueAt(i, 1);
            Double diem = (Double) tblGridView.getValueAt(i, 3);
            Boolean isDelete = (Boolean) tblGridView.getValueAt(i, 4);
            //System.out.println(diem);
            if (!ShareHelper.isManager()) {
                DialogHelper.alert(this, "Bạn không có quyền xóa");
                return;
            } else {
                if (isDelete) {
                    hvdao.delete(maHV);
                }

                if (diem >= 0 || diem <= 10) {
                    HocVien model = new HocVien();
                    model.setMaHV(maHV);
                    model.setMaKH(MaKH);
                    model.setMaNH(maNH);
                    model.setDiem(diem);
                    hvdao.update(model);

                } else {
                    HocVien model = new HocVien();
                    model.setMaHV(maHV);
                    model.setMaKH(MaKH);
                    //model.setDiem(diem);
                    model.setMaNH(maNH);
                    hvdao.update2(model);

                }

                DialogHelper.alert(this, "Cập nhật thành công!");
                this.fillComboBox();
                this.fillGridView();
            }
        }
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.fillComboBox();
        this.fillGridView();
    }//GEN-LAST:event_formWindowOpened

    private void rdoTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTatCaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTatCaMouseClicked

    private void rdoDaNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDaNhapMouseClicked
        // TODO add your handling code here:    
    }//GEN-LAST:event_rdoDaNhapMouseClicked

    private void rdoChuaNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChuaNhapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChuaNhapMouseClicked

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
        this.fillGridView();                   //ActionPerformed
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoDaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaNhapActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoDaNhapActionPerformed

    private void rdoChuaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaNhapActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoChuaNhapActionPerformed

    private void cboNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNguoiHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNguoiHocActionPerformed

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed
        update();
    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkDiem()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked

    }//GEN-LAST:event_tblGridViewMouseClicked

    /**
     * @param args the command line arguments
     */
    //không cần đến cái này, nó sẽ gây lỗi khi sửa constructor của JFrame
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QUANLYHOCVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QUANLYHOCVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QUANLYHOCVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QUANLYHOCVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                new QUANLYHOCVIEN().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNguoiHoc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlHVKH;
    private javax.swing.JPanel pnlHVKhac;
    private javax.swing.JRadioButton rdoChuaNhap;
    private javax.swing.JRadioButton rdoDaNhap;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables

    public boolean checkDiem() {
        if (txtDiem.getText().isEmpty()) {
            return true;
        } else {
            double diem = Double.parseDouble(txtDiem.getText());
            if (diem >= 10 || diem <= 0) {
                DialogHelper.alert(this, "diem phải trong khoang tư 0 đến 10");
                return false;
            }
        }
        return true;
    }

}
