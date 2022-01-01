/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modules;

import authentication.ConnectDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
public class addModules extends javax.swing.JFrame {

    adminCourses am = new adminCourses();
    modulesDetail md = new modulesDetail();
    java.sql.PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = ConnectDatabase.connectdb();

    /**
     * Creates new form addModules
     */
    public addModules() {
        initComponents();
        setCode();
        setActivity();
        setLec();
    }

    public void setCode() {
        codePlace.setText(am.getModuleCode());
    }

    public void setLec() {
        try {
            String slc = "SELECT * FROM LOGINTABLE WHERE STDNT_TYPE=" + 0 + "";
            ps = con.prepareStatement(slc);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString("FULLNAME");
                txtLec.addItem(fullname);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editMod() {
        try {
            int oc = md.getEOcc();
            txtOcc.setSelectedIndex((oc - 1));
            txtAct.setSelectedItem(md.getEType());
            txtDay.setSelectedItem(md.getEDay());
            txtTime1.setSelectedItem(md.getETime());
            //txtLec.setSelectedItem(md.getELec());
            txtSCap.setValue(md.getECap());

        } catch (Exception e) {
            System.out.println("ops");
        }
    }

    public void setActivity() {

        if (am.getAct().equals("LECTURE, TUTORIAL")) {
            txtAct.removeItem("LAB");
            txtAct.addItem("LECTURE");
            txtAct.addItem("TUTORIAL");
        } else if (am.getAct().equals("TUTORIAL")) {
            txtAct.removeItem("LECTURE");
            txtAct.removeItem("LAB");
            txtAct.addItem("TUTORIAL");
        } else if (am.getAct().equals("LECTURE")) {
            txtAct.removeItem("TUTORIAL");
            txtAct.removeItem("LAB");
            txtAct.addItem("LECTURE");
        } else if (am.getAct().equals("LECTURE, LAB")) {
            txtAct.addItem("LECTURE");
            txtAct.addItem("LAB");
            txtAct.removeItem("TUTORIAL");
        } else {
            txtAct.addItem("LECTURE");
            txtAct.addItem("TUTORIAL");
            txtAct.addItem("LAB");
        }
    }

    public void setTime(int tm1, int hours, int ap) {
        if (tm1 < 12 - hours) {
            String time2 = txtTime1.getItemAt(tm1 + hours).toString();
            txtTime2.setText(time2);
            String sfx2 = ampm1.getSelectedItem().toString();
            ampm2.setText(sfx2);
        } else {
            String time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
            txtTime2.setText(time2);
            String sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
            ampm2.setText(sfx2);
        }
    }

    public void setHour() {
        String time1 = txtTime1.getSelectedItem().toString();
        int tm1 = txtTime1.getSelectedIndex();
        int ap = ampm1.getSelectedIndex();
        int hours = -1;
        if (txtAct.getSelectedItem().toString().equals("LECTURE")) {
            //Getting Lecture Hour:
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LECTUREHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork1");
            }
            //Setting Time Start & Time End:
            setTime(tm1, hours, ap);

        } else if (txtAct.getSelectedItem().toString().equals("TUTORIAL")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("TUTORIALHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork2");
            }
            setTime(tm1, hours, ap);
        } else if (txtAct.getSelectedItem().toString().equals("LAB")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LABHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork3");
            }
            setTime(tm1, hours, ap);
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

        jLabel2 = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codePlace = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtAct = new javax.swing.JComboBox<>();
        txtDay = new javax.swing.JComboBox<>();
        txtLec = new javax.swing.JComboBox<>();
        txtOcc = new javax.swing.JComboBox<>();
        txtTime1 = new javax.swing.JComboBox<>();
        ampm1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtTime2 = new javax.swing.JLabel();
        ampm2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSCap = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADD MODULES INFORMATION");

        Exit.setText("Back");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel3.setText("Course Code");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel4.setText("Occurrence");

        codePlace.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        codePlace.setText("Code placeholder");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel6.setText("Class Activity");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel7.setText("Day");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel8.setText("Assign Lecturer");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel9.setText("Timeslot");

        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActActionPerformed(evt);
            }
        });

        txtDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" }));

        txtLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLecActionPerformed(evt);
            }
        });

        txtOcc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        txtOcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOccActionPerformed(evt);
            }
        });

        txtTime1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12:00", "01:00", "02:00\t", "03:00\t", "04:00\t", "05:00", "06:00", "07:00\t", "08:00\t", "09:00", "10:00", "11:00" }));
        txtTime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTime1ActionPerformed(evt);
            }
        });

        ampm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        ampm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ampm1ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        txtTime2.setText("END TIME");

        ampm2.setText("AM/PM");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel10.setText("Target Students");

        txtSCap.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(codePlace, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                        .addGap(43, 43, 43))
                                    .addComponent(txtAct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtOcc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ampm1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ampm2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Exit)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(codePlace))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtOcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        new modulesDetail().updateTable();
        modulesDetail wp2 = new modulesDetail();
        wp2.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ExitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int edit = md.getEdit();
        String coursecode = am.getModuleCode();
        String oc = txtOcc.getSelectedItem().toString();
        String type = txtAct.getSelectedItem().toString();
        String day = txtDay.getSelectedItem().toString();
        String time1 = txtTime1.getSelectedItem().toString();
        String time2 = "";
        String sfx2 = "";
        String sfx1 = ampm1.getSelectedItem().toString();
        int tm1 = txtTime1.getSelectedIndex();
        int ap = ampm1.getSelectedIndex();
        int cred = Integer.parseInt(am.getCredit());
        int scap = (Integer) txtSCap.getValue();
        int hours = -1;

        if (txtAct.getSelectedItem().toString().equals("LECTURE")) {
            //Getting Lecture Hour:
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LECTUREHOUR");
                }
                if (tm1 < 12 - hours) {
                    time2 = txtTime1.getItemAt(tm1 + hours).toString();
                    sfx2 = ampm1.getSelectedItem().toString();
                } else {
                    time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                    sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                }
            } catch (SQLException ex) {
                System.out.println("didntwork1");
            }
            //Setting Time Start & Time End:
            setTime(tm1, hours, ap);

        } else if (txtAct.getSelectedItem().toString().equals("TUTORIAL")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("TUTORIALHOUR");
                }
                if (tm1 < 12 - hours) {
                    time2 = txtTime1.getItemAt(tm1 + hours).toString();
                    sfx2 = ampm1.getSelectedItem().toString();
                } else {
                    time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                    sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                }
            } catch (SQLException ex) {
                System.out.println("didntwork2");
            }
            setTime(tm1, hours, ap);
        } else if (txtAct.getSelectedItem().toString().equals("LAB")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LABHOUR");
                }
                if (tm1 < 12 - hours) {
                    time2 = txtTime1.getItemAt(tm1 + hours).toString();
                    sfx2 = ampm1.getSelectedItem().toString();
                } else {
                    time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                    sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                }
            } catch (SQLException ex) {
                System.out.println("didntwork3");
            }
            setTime(tm1, hours, ap);
        }

        String t1 = time1 + " " + sfx1;
        String t2 = time2 + " " + sfx2;
        //boolean lim = false;

        int occ = Integer.parseInt(oc);
        String lec = txtLec.getSelectedItem().toString();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/userlogin", "app", "app");
            Statement st;
            st = con.createStatement();
            if (edit == 1) {
                try {
                    PreparedStatement set = con.prepareStatement("DELETE FROM app.all_modules WHERE modules ='" + am.getModuleCode() + "' and occurence =" + md.getEOcc() + " and activitytype ='" + md.getEType() + "'");
                    set.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("480 am");
                }
            }
            String strQuery3 = "SELECT COUNT(*) FROM app.ALL_modules where occurence=" + occ + " and activitytype='" + type + "' and modules='" + am.getModuleCode() + "'";
            ResultSet rs3 = st.executeQuery(strQuery3);
            rs3.next();
            String Countrow3 = rs3.getString(1);
            System.out.println(Countrow3);
            if (Countrow3.equals("0")) {
                st.execute("INSERT INTO APP.all_modules(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP)VALUES('" + coursecode + "'," + occ + ",'" + day + "','" + t1 + "','" + t2 + "','" + type + "','" + lec + "'," + scap + ")");
                if(edit==1){
                    JOptionPane.showMessageDialog(this, "Module Updated");
                }else{
                JOptionPane.showMessageDialog(this, "Module Added");
                }
            } else {

                JOptionPane.showMessageDialog(this, "Module already exists!");
                if(edit==1){
                    st.execute("INSERT INTO APP.all_modules(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP)VALUES('" + am.getModuleCode() + "'," + md.getEOcc() + ",'" + md.getEDay() + "','" + md.getETime1() + "','" + md.getETime2() + "','" + md.getEType() + "','" + md.getELec() + "'," + md.getECap() + ")");
                    
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to Add Course");
        }
        new modulesDetail().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTime1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTime1ActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_txtTime1ActionPerformed

    private void ampm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ampm1ActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_ampm1ActionPerformed

    private void txtActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_txtActActionPerformed

    private void txtLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLecActionPerformed

    private void txtOccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOccActionPerformed

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
            java.util.logging.Logger.getLogger(addModules.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addModules.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addModules.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addModules.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addModules().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JComboBox<String> ampm1;
    private javax.swing.JLabel ampm2;
    private javax.swing.JLabel codePlace;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> txtAct;
    private javax.swing.JComboBox<String> txtDay;
    private javax.swing.JComboBox<String> txtLec;
    private javax.swing.JComboBox<String> txtOcc;
    private javax.swing.JSpinner txtSCap;
    private javax.swing.JComboBox<String> txtTime1;
    private javax.swing.JLabel txtTime2;
    // End of variables declaration//GEN-END:variables
}
