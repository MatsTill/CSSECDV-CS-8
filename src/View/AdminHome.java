/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
//[255,102,51]
import Controller.SQLite;
import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BeepXD
 */
public class AdminHome extends javax.swing.JPanel {

    public MgmtHistory mgmtHistory;
    public MgmtLogs mgmtLogs;
    public MgmtProduct mgmtProduct;
    public MgmtUser mgmtUser;
    public SQLite sqlite;
    private User currentUser;
    
    private CardLayout contentView = new CardLayout();
    
    public AdminHome() {
        initComponents();
    }
    
    public void init(SQLite sqlite){
        this.sqlite = sqlite;
        mgmtHistory = new MgmtHistory(sqlite);
        mgmtLogs = new MgmtLogs(sqlite);
        mgmtProduct = new MgmtProduct(sqlite);
        mgmtUser = new MgmtUser(sqlite);
        
        Content.setLayout(contentView);
        Content.add(new Home("WELCOME ADMIN!", new java.awt.Color(51, 153, 255)), "home");
        Content.add(this.mgmtUser, "mgmtUser");
        Content.add(this.mgmtHistory, "mgmtHistory");
        Content.add(this.mgmtProduct, "mgmtProduct");
        Content.add(this.mgmtLogs, "mgmtLogs");
        
//        UNCOMMENT TO DISABLE BUTTONS
//        historyBtn.setVisible(false);
//        usersBtn.setVisible(false);
//        productsBtn.setVisible(false);
//        logsBtn.setVisible(false);
    }
    
    public void init(SQLite sqlite, User user){
        this.sqlite = sqlite;
        this.currentUser = user;
        
        mgmtHistory = new MgmtHistory(sqlite);
        mgmtLogs = new MgmtLogs(sqlite);
        mgmtProduct = new MgmtProduct(sqlite);
        mgmtUser = new MgmtUser(sqlite);
        
        Content.setLayout(contentView);
        Content.add(new Home("WELCOME ADMIN!", new java.awt.Color(51, 153, 255)), "home");
        Content.add(this.mgmtUser, "mgmtUser");
        Content.add(this.mgmtHistory, "mgmtHistory");
        Content.add(this.mgmtProduct, "mgmtProduct");
        Content.add(this.mgmtLogs, "mgmtLogs");
    }
    
    public void showPnl(String pnl){
        switch(pnl){
            case "user":     mgmtUser.init(currentUser);
                             contentView.show(Content, "mgmtUser");
                             break;
            case "history":  mgmtHistory.init(sqlite, currentUser);
                             contentView.show(Content, "mgmtHistory");
                             break;
            case "product":  mgmtProduct.init(sqlite, currentUser);
                             contentView.show(Content, "mgmtProduct");
                             break;
            case "logs":     mgmtLogs.init(currentUser);
                             contentView.show(Content, "mgmtLogs");
                             break;
            default:         contentView.show(Content, "home");
                             break;
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

        usersBtn = new javax.swing.JButton();
        Content = new javax.swing.JPanel();
        logsBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 153, 255));

        usersBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usersBtn.setText("USERS");
        usersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersBtnActionPerformed(evt);
            }
        });

        Content.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout ContentLayout = new javax.swing.GroupLayout(Content);
        Content.setLayout(ContentLayout);
        ContentLayout.setHorizontalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContentLayout.setVerticalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        logsBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logsBtn.setText("LOGS");
        logsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 105, Short.MAX_VALUE)
                        .addComponent(usersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtnActionPerformed
        mgmtUser.init(currentUser);
        usersBtn.setForeground(Color.red);
        logsBtn.setForeground(Color.black);
        contentView.show(Content, "mgmtUser");
    }//GEN-LAST:event_usersBtnActionPerformed

    private void logsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsBtnActionPerformed
        mgmtLogs.init(currentUser);
        usersBtn.setForeground(Color.black);
        logsBtn.setForeground(Color.red);
        contentView.show(Content, "mgmtLogs");
    }//GEN-LAST:event_logsBtnActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JButton logsBtn;
    private javax.swing.JButton usersBtn;
    // End of variables declaration//GEN-END:variables
}
