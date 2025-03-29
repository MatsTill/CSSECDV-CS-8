/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Model.History;
import Model.Product;
import Model.User;
import Model.Role;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author beepxD
 */
public class MgmtHistory extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;
    private User currentUser;
    
    public MgmtHistory(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel)table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        javax.swing.table.DefaultTableCellRenderer rightAlign = new javax.swing.table.DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        table.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        table.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        table.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        
//        UNCOMMENT TO DISABLE BUTTONS
//        searchBtn.setVisible(false);
//        reportBtn.setVisible(false);
    }

    public void init(SQLite sqlite){
        this.sqlite = sqlite;
        loadAllHistory();
    }
    
    public void init(SQLite sqlite, User user){
        this.sqlite = sqlite;
        this.currentUser = user;
        
        if (user != null && user.getRole() == Role.CLIENT) {
            // For client users, only show their own history
            loadUserHistory(user.getUsername());
            
            // Clients should not be able to search for other users' history
            searchBtn.setVisible(false);
        } else {
            // Admin, Manager, and Staff can see all history
            loadAllHistory();
            searchBtn.setVisible(true);
        }
    }
    
    private void loadAllHistory() {
        // CLEAR TABLE
        for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
            tableModel.removeRow(0);
        }
        
        // LOAD CONTENTS
        ArrayList<History> history = sqlite.getHistory();
        for(int nCtr = 0; nCtr < history.size(); nCtr++){
            Product product = sqlite.getProduct(history.get(nCtr).getName());
            tableModel.addRow(new Object[]{
                history.get(nCtr).getUsername(), 
                history.get(nCtr).getName(), 
                history.get(nCtr).getStock(), 
                product.getPrice(), 
                product.getPrice() * history.get(nCtr).getStock(), 
                history.get(nCtr).getTimestamp()
            });
        }
    }
    
    private void loadUserHistory(String username) {
        // CLEAR TABLE
        for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
            tableModel.removeRow(0);
        }
        
        // LOAD CONTENTS
        ArrayList<History> history = sqlite.getHistory();
        for(int nCtr = 0; nCtr < history.size(); nCtr++){
            if (history.get(nCtr).getUsername().equals(username)) {
                Product product = sqlite.getProduct(history.get(nCtr).getName());
                tableModel.addRow(new Object[]{
                    history.get(nCtr).getUsername(), 
                    history.get(nCtr).getName(), 
                    history.get(nCtr).getStock(), 
                    product.getPrice(), 
                    product.getPrice() * history.get(nCtr).getStock(), 
                    history.get(nCtr).getTimestamp()
                });
            }
        }
    }
    
    // Keep the no-parameter version for backward compatibility
    public void init(){
        if (currentUser != null && currentUser.getRole() == Role.CLIENT) {
            loadUserHistory(currentUser.getUsername());
        } else {
            loadAllHistory();
        }
    }
    
    public void designer(JTextField component, String text){
        component.setSize(70, 600);
        component.setFont(new java.awt.Font("Tahoma", 0, 18));
        component.setBackground(new java.awt.Color(240, 240, 240));
        component.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        component.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), text, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
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
        searchBtn = new javax.swing.JButton();
        reloadBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Name", "Stock", "Price", "Total", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(160);
            table.getColumnModel().getColumn(1).setPreferredWidth(160);
            table.getColumnModel().getColumn(2).setMinWidth(80);
            table.getColumnModel().getColumn(3).setMinWidth(100);
            table.getColumnModel().getColumn(4).setMinWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(240);
        }

        searchBtn.setBackground(new java.awt.Color(255, 255, 255));
        searchBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchBtn.setText("SEARCH USERNAME OR PRODUCT");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        reloadBtn.setBackground(new java.awt.Color(255, 255, 255));
        reloadBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reloadBtn.setText("RELOAD");
        reloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(reloadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(reloadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        JTextField searchFld = new JTextField("");
        designer(searchFld, "SEARCH USERNAME OR PRODUCT");

        Object[] message = {
            searchFld
        };

        int result = JOptionPane.showConfirmDialog(null, message, "SEARCH HISTORY", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

        if (result == JOptionPane.OK_OPTION) {
            // CLEAR TABLE
            for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
                tableModel.removeRow(0);
            }

            // LOAD CONTENTS
            ArrayList<History> history = sqlite.getHistory();
            for(int nCtr = 0; nCtr < history.size(); nCtr++){
                // If user is client, only show their history
                if (currentUser != null && currentUser.getRole() == Role.CLIENT) {
                    if (!history.get(nCtr).getUsername().equals(currentUser.getUsername())) {
                        continue; // Skip entries that don't belong to this client
                    }
                }
                
                if(searchFld.getText().isEmpty() || 
                   searchFld.getText().toLowerCase().contains(history.get(nCtr).getUsername().toLowerCase()) || 
                   history.get(nCtr).getUsername().toLowerCase().contains(searchFld.getText().toLowerCase()) || 
                   searchFld.getText().toLowerCase().contains(history.get(nCtr).getName().toLowerCase()) || 
                   history.get(nCtr).getName().toLowerCase().contains(searchFld.getText().toLowerCase())){
                
                    Product product = sqlite.getProduct(history.get(nCtr).getName());
                    tableModel.addRow(new Object[]{
                        history.get(nCtr).getUsername(), 
                        history.get(nCtr).getName(), 
                        history.get(nCtr).getStock(), 
                        product.getPrice(), 
                        product.getPrice() * history.get(nCtr).getStock(), 
                        history.get(nCtr).getTimestamp()
                    });
                }
            }
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void reloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadBtnActionPerformed
        init();
    }//GEN-LAST:event_reloadBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reloadBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
