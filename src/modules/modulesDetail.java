/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modules;

import authentication.ConnectDatabase;
import authentication.LoginForm1;
import modules.addModules;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
public class modulesDetail extends javax.swing.JFrame{
    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    LoginForm1 lf = new LoginForm1();
    adminCourses am =new adminCourses();
    int hours = -1;
    static int occ = -1,ecap =-1,edit =0;
    static String type="",eday ="", etime ="",etime1 = "",etime2="",elec ="";
    

    public void updateTable(){
            String update = "SELECT * FROM APP.ALL_MODULES ORDER BY OCCURENCE";
        try {
            con.prepareStatement(update);
            rs=ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(modulesDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    public void displayModules(){
        String dm = "SELECT * FROM all_MODULES where modules='"+am.getModuleCode()+"' ORDER BY OCCURENCE";
        try {
            ps = con.prepareStatement(dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String OCCURENCE = rs.getString("OCCURENCE");
                String DAY = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String LECTURER = rs.getString("LECTURER");
                String TARGET = rs.getString("STUDENTCAP");
                

                String tbData[] = {MODULES, OCCURENCE, DAY, TIMESTART, TIMEEND, ACTIVITY, LECTURER,TARGET};
                DefaultTableModel tblModel = (DefaultTableModel) modulesTable.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    /**
     * Creates new form module_Details
     */
    public modulesDetail(){
        
        initComponents();
        displayModules();
        setCode();
        setCredit();
        updateTable();
        
    }
    
    public void setCode(){
        courseCode.setText("COURSE: "+am.getModuleCode());
    }
    
    public void setCredit(){
        courseCredit.setText("CREDIT: "+am.getCredit());
    }
    public void setEdit(int occ,int scap,String type,String day,String time,int edit,String time1,String time2){
        this.occ = occ;
        this.type = type;
        this.eday = day;
        this.etime = time;
        //this.elec = lec;
        this.ecap = scap;
        this.edit = edit;
        this.etime1 = time1;
        this.etime2 = time2;
    }
    public int getEOcc(){
        return occ;
    }
    public String getEType(){
        return type;
    }
    public String getEDay(){
        return eday;
    }
    public String getETime(){
        return etime;
    }
    public String getETime1(){
        return etime1;
    }
    public String getETime2(){
        return etime2;
    }
    public String getELec(){
        return elec;
    }
    public int getECap(){
        return ecap;
    }
    public int getEdit(){
        return edit;
    }
    public void deleteB(){
        DefaultTableModel dtm = (DefaultTableModel) modulesTable.getModel();
        try{
            /*int SelectedRowIndex = jTable1.getSelectedRow();
            model.removeRow(SelectedRowIndex);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.valid_modules WHERE modules = " + name + "");
            st.executeUpdate();*/
            int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String type = model.getValueAt(index, 5).toString();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
            
            //System.out.println(code);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.all_modules WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='"+type+"'");
            st.executeUpdate();
            dtm.removeRow(index);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"NO COURSE SELECTED");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        modulesTable = new javax.swing.JTable();
        courseCredit = new javax.swing.JLabel();
        backBut = new javax.swing.JButton();
        courseCode = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocation(new java.awt.Point(0, 0));

        modulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULES", "OCC", "DAY", "TIME START", "TIME END", "ACTIVITY", "MODULE TUTOR", "TARGET"
            }
        ));
        modulesTable.setColumnSelectionAllowed(true);
        modulesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modulesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(modulesTable);
        modulesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (modulesTable.getColumnModel().getColumnCount() > 0) {
            modulesTable.getColumnModel().getColumn(1).setMaxWidth(75);
            modulesTable.getColumnModel().getColumn(3).setMinWidth(90);
            modulesTable.getColumnModel().getColumn(3).setMaxWidth(90);
            modulesTable.getColumnModel().getColumn(4).setMinWidth(90);
            modulesTable.getColumnModel().getColumn(4).setMaxWidth(90);
            modulesTable.getColumnModel().getColumn(5).setResizable(false);
            modulesTable.getColumnModel().getColumn(6).setMinWidth(150);
            modulesTable.getColumnModel().getColumn(7).setMinWidth(75);
            modulesTable.getColumnModel().getColumn(7).setMaxWidth(75);
        }

        courseCredit.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        courseCredit.setText("CREDIT    :");

        backBut.setText("Back");
        backBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButActionPerformed(evt);
            }
        });

        courseCode.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        courseCode.setText("COURSE  : ");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(courseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(backBut)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(courseCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addComponent(deleteButton)
                        .addComponent(editButton))
                    .addComponent(courseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(courseCredit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modulesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modulesTableMouseClicked
        // TODO add your handling code here:
        

    }//GEN-LAST:event_modulesTableMouseClicked

    private void backButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButActionPerformed
        // TODO add your handling code here:
        new adminCourses().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backButActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        edit = 0;
        new addModules().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) modulesTable.getModel();
        try{
            /*int SelectedRowIndex = jTable1.getSelectedRow();
            model.removeRow(SelectedRowIndex);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.valid_modules WHERE modules = " + name + "");
            st.executeUpdate();*/
            int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String type = model.getValueAt(index, 5).toString();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
            
            //System.out.println(code);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.all_modules WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='"+type+"'");
            st.executeUpdate();
            dtm.removeRow(index);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"NO COURSE SELECTED");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        addModules as = new addModules();
        
        try{
        int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
            int scap = Integer.parseInt(model.getValueAt(index, 7).toString());
            int edit = 1;
            String typ = model.getValueAt(index, 5).toString();
            String day = model.getValueAt(index, 2).toString();
            String time1 = model.getValueAt(index, 3).toString();
            String time2 = model.getValueAt(index, 3).toString();
            String time = time1.substring(0,5);
            //String lec = model.getValueAt(index, 6).toString();
            setEdit(occ,scap,typ,day,time,edit,time1,time2);
            as.editMod();
            as.setVisible(true);
            this.setVisible(false);
        }catch(Exception e){
            System.out.println("273 md");
            JOptionPane.showMessageDialog(null,"NO COURSE SELECTED");
        }
    }//GEN-LAST:event_editButtonActionPerformed

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
            java.util.logging.Logger.getLogger(modulesDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modulesDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modulesDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modulesDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modulesDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBut;
    private javax.swing.JLabel courseCode;
    private javax.swing.JLabel courseCredit;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modulesTable;
    // End of variables declaration//GEN-END:variables
}
