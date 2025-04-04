/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Model.Logs;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Model.User;

/**
 *
 * @author beepxD
 */
public class MgmtLogs extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;
    private User currentUser;
    
    public MgmtLogs(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel)table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        
//        UNCOMMENT TO DISABLE BUTTONS
//        clearBtn.setVisible(false);
//        debugBtn.setVisible(false);
    }

    public void init(){
        //      CLEAR TABLE
        for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
            tableModel.removeRow(0);
        }
        
//      LOAD CONTENTS
        ArrayList<Logs> logs = sqlite.getLogs();
        for(int nCtr = 0; nCtr < logs.size(); nCtr++){
            tableModel.addRow(new Object[]{
                logs.get(nCtr).getEvent(), 
                logs.get(nCtr).getUsername(), 
                logs.get(nCtr).getDesc(), 
                logs.get(nCtr).getTimestamp()});
        }
    }
    
    public void init(User currentUser){
        this.currentUser = currentUser;
        init();
        
        // Only admins can access debug and clear functionality
        boolean isAdmin = currentUser != null && currentUser.getRole() == Model.Role.ADMIN;
        debugBtn.setVisible(isAdmin);
        clearBtn.setVisible(isAdmin);
        
        // Update button text to show current debug mode
        if (isAdmin) {
            debugBtn.setText(sqlite.DEBUG_MODE == 1 ? "DISABLE DEBUG MODE" : "ENABLE DEBUG MODE");
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
        table = new javax.swing.JTable();
        clearBtn = new javax.swing.JButton();
        debugBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Event", "Username", "Description", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
            table.getColumnModel().getColumn(1).setPreferredWidth(160);
            table.getColumnModel().getColumn(2).setPreferredWidth(400);
            table.getColumnModel().getColumn(3).setPreferredWidth(240);
        }

        clearBtn.setBackground(new java.awt.Color(255, 255, 255));
        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        debugBtn.setBackground(new java.awt.Color(255, 255, 255));
        debugBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        debugBtn.setText("ENABLE/DISABLE DEBUG MODE");
        debugBtn.setToolTipText("");
        debugBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(debugBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debugBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // Only allow admins to clear logs
        int result = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to clear all logs?",
            "Clear Logs",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            // Implement clearing logs - would need a method in SQLite
            // For now, just log the attempt
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            String username = (currentUser != null) ? currentUser.getUsername() : "unknown";
            sqlite.addLogs("LOGS_CLEAR", username, "Attempted to clear logs", timestamp);
            
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "ONly a SUPER ADMIN can clear logs..",
                "NOT ALLOWED",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            
            // Refresh logs display
            init();
        }
    }//GEN-LAST:event_clearBtnActionPerformed

    private void debugBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugBtnActionPerformed
        // Only allow admins to toggle debug mode
        if (currentUser == null || currentUser.getRole() != Model.Role.ADMIN) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Only administrators can toggle debug mode.",
                "Permission Denied",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        boolean newDebugMode = sqlite.DEBUG_MODE == 0;
        sqlite.DEBUG_MODE = newDebugMode ? 1 : 0;
        
        // Update button text
        debugBtn.setText(newDebugMode ? "DISABLE DEBUG MODE" : "ENABLE DEBUG MODE");
        
        // Log the debug mode change
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String action = newDebugMode ? "ENABLED" : "DISABLED";
        sqlite.addLogs("DEBUG_MODE_" + action, currentUser.getUsername(), 
                      "Debug mode " + action.toLowerCase() + " by administrator", timestamp);
        
        // Refresh logs to show the new entry
        init();
        
        // Show status message
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Debug mode has been " + action.toLowerCase() + ".\nCurrent status: " + 
            (newDebugMode ? "ENABLED" : "DISABLED"),
            "Debug Mode",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
        
        // Print to console for additional debugging
        System.out.println("DEBUG MODE " + action + ": " + sqlite.DEBUG_MODE);
    }//GEN-LAST:event_debugBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton debugBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
