/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.View;

import com.EduSys.dao.ChuyenDeDao;
import com.EduSys.helper.DialogHelper;
import com.EduSys.helper.XImage;
import com.EduSys.model.ChuyenDe;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class QuanLyChuyenDe extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyNhanVien
     */
    int index = 0;
    ChuyenDeDao dao = new ChuyenDeDao();
    String path = null;

    public QuanLyChuyenDe() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        load();
    }

    public void ResizeImage(String imageName) {
        ImageIcon icon = new ImageIcon("image" + imageName);
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), image.SCALE_SMOOTH));
        lblHinh.setIcon(icon1);

    }

    ChuyenDe getModel() {
        ChuyenDe model = new ChuyenDe();
        model.setMaCD(txtMaCD.getText());
        model.setTenCD(txtTenCD.getText());
        model.setHocPhi(Double.valueOf(txtHocPhi.getText()));
        model.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
        if (path == null) {
            model.setHinh("No avatar");
        } else {
            model.setHinh(path);
        }
        //model.setHinh(lblHinh.getToolTipText());
        model.setMoTa(txtMoTaCD.getText());
        return model;
    }

    public boolean check() {

        if (txtMaCD.getText().equals("") || txtTenCD.getText().equals("") || txtThoiLuong.getText().equals("")
                || txtHocPhi.getText().equals("") || txtMoTaCD.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ dữ liệu sau đó ấn Save", "Error", 1);
            return false;
        } else if (!(txtThoiLuong.getText()).matches("[0-9]{1,99}")) {
            JOptionPane.showMessageDialog(rootPane, "Thời lượng phải nhập số dương", "Error", 1);
            txtThoiLuong.requestFocus();
            return false;
        } else if (!(txtHocPhi.getText()).matches("[0-9]{1,99}")) {
            JOptionPane.showMessageDialog(rootPane, "Học phí phải nhập số dương", "Error", 1);
            txtHocPhi.requestFocus();
            return false;
        }

        List<ChuyenDe> list = dao.selectAll();
        for (int i = 0; i < list.size(); i++) {
            if (txtMaCD.getText().equalsIgnoreCase(list.get(i).getMaCD())) {
                JOptionPane.showMessageDialog(this, "Trùng Mã Chuyên Đề", "Error", 1);
                return false;
            }
        }

        return true;
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblQLCD.getModel();
        model.setRowCount(0);

        try {
            List<ChuyenDe> list = dao.selectAll();
            for (ChuyenDe cd : list) {
                Object[] row = {
                    cd.getMaCD(),
                    cd.getTenCD(),
                    cd.getHocPhi(),
                    cd.getThoiLuong(),
                    cd.getHinh(),
                    cd.getMoTa()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    void insert() {
        ChuyenDe model = getModel();
        if (model.getHinh() == null) {
            DialogHelper.alert(this, "Hình Không Được Để Trống");
            return;
        }
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void clear() {
        this.setModel(new ChuyenDe());
        this.setStatus(true);
    }

    void setModel(ChuyenDe model) {
        txtMaCD.setText(model.getMaCD());
        txtTenCD.setText(model.getTenCD());
        txtHocPhi.setText(String.valueOf(model.getHocPhi()));
        txtThoiLuong.setText(String.valueOf(model.getThoiLuong()));
        lblHinh.setToolTipText(model.getHinh());
        if (model.getHinh() == null) {
            lblHinh.setText("no avartar");
            lblHinh.setIcon(null);
        } else {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(model.getHinh()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon  = new ImageIcon(dimg);
            lblHinh.setIcon(icon);
            lblHinh.setText("");
        }
        txtMoTaCD.setText(model.getMoTa());
    }

    void setStatus(boolean insertable) {
        txtMaCD.setEditable(insertable);
       // btnThem.setEnabled(insertable);
        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblQLCD.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrivios.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void update() {
        ChuyenDe model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn có muốn xóa hay không?")) {
            String macd = txtMaCD.getText();
            try {
                dao.delete(macd);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblQLNVQT = new javax.swing.JLabel();
        tabQLNV = new javax.swing.JTabbedPane();
        pnCapNhat = new javax.swing.JPanel();
        lblHinhLogo = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        lblConfirmPassword = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        lblHoVaTen = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrivios = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblMaNhanVien1 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaCD = new javax.swing.JTextArea();
        txtThoiLuong = new javax.swing.JTextField();
        txtTenCD = new javax.swing.JTextField();
        pnDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLCD = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ CHUYÊN ĐỀ");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblQLNVQT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQLNVQT.setForeground(new java.awt.Color(51, 51, 255));
        lblQLNVQT.setText("QUẢN LÝ CHUYÊN ĐỀ");

        lblHinhLogo.setText("Hình logo");

        lblMatKhau.setText("Tên chuyên đề");

        lblConfirmPassword.setText("Thời  lượng");

        txtHocPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHocPhiActionPerformed(evt);
            }
        });

        lblHoVaTen.setText("Học phí");

        lblVaiTro.setText("Mô tả chuyên đề");

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/EduSys/icon/Save.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/EduSys/icon/Upload.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/EduSys/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoa);

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/EduSys/icon/Add.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel2.add(btnMoi);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel4.add(btnFirst);

        btnPrivios.setText("<<");
        btnPrivios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPriviosActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrivios);

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel4.add(btnLast);

        lblMaNhanVien1.setText("Mã chuyên đề");

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        txtMoTaCD.setColumns(20);
        txtMoTaCD.setLineWrap(true);
        txtMoTaCD.setRows(5);
        txtMoTaCD.setToolTipText("");
        txtMoTaCD.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtMoTaCD);

        txtThoiLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoiLuongActionPerformed(evt);
            }
        });

        txtTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCapNhatLayout = new javax.swing.GroupLayout(pnCapNhat);
        pnCapNhat.setLayout(pnCapNhatLayout);
        pnCapNhatLayout.setHorizontalGroup(
            pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnCapNhatLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnCapNhatLayout.createSequentialGroup()
                        .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenCD, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThoiLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnCapNhatLayout.createSequentialGroup()
                                .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtHocPhi)
                            .addComponent(txtMaCD))))
                .addContainerGap())
        );
        pnCapNhatLayout.setVerticalGroup(
            pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHinhLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCapNhatLayout.createSequentialGroup()
                        .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCapNhatLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pnCapNhatLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel4});

        tabQLNV.addTab("CẬP NHẬT", pnCapNhat);

        tblQLCD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CĐ", "TÊN CĐ", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLCD.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQLCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLCDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLCD);

        javax.swing.GroupLayout pnDanhSachLayout = new javax.swing.GroupLayout(pnDanhSach);
        pnDanhSach.setLayout(pnDanhSachLayout);
        pnDanhSachLayout.setHorizontalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnDanhSachLayout.setVerticalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabQLNV.addTab("DANH SÁCH", pnDanhSach);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQLNVQT, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblQLNVQT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblQLCD.getRowCount() - 1;
        this.edit();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtHocPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHocPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHocPhiActionPerformed

    private void txtThoiLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoiLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiLuongActionPerformed

    private void txtTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCDActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int kq = jfc.showOpenDialog(this);
            if (kq == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                path = file.getAbsolutePath();
                BufferedImage b;
                b = ImageIO.read(file);
                Image dimg = b.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(dimg);
                lblHinh.setIcon(icon);
                lblHinh.setText("");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn ảnh...");
            }
        } catch (Exception a) {
            a.printStackTrace();
        }


    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (check()) {
                this.insert();
                this.load();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi, Vui lòng xem lại");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.update();
        this.load();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
        this.load();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clear();
        lblHinh.setIcon(null);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblQLCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLCDMouseClicked

        this.index = tblQLCD.rowAtPoint(evt.getPoint());
        if (this.index >= 0) {
            this.edit();
            tabQLNV.setSelectedIndex(0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tblQLCDMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.edit(); // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPriviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPriviosActionPerformed
        this.index--;
        this.edit();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPriviosActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.edit();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChuyenDe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrivios;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHinhLogo;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblMaNhanVien1;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblQLNVQT;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnCapNhat;
    private javax.swing.JPanel pnDanhSach;
    private javax.swing.JTabbedPane tabQLNV;
    private javax.swing.JTable tblQLCD;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextArea txtMoTaCD;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables

    void edit() {
        try {
            String macd = (String) tblQLCD.getValueAt(this.index, 0);
            ChuyenDe model = dao.findById(macd);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
}
