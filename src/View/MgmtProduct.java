/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Model.Product;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Controller.DataValidator;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import View.ValidationUtils;

/**
 *
 * @author beepxD
 */
public class MgmtProduct extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;
    
    // Product field declarations
    private JTextField productIdFld;
    private JTextField productNameFld;
    private JTextField productStockFld;
    private JTextField productPriceFld;
    private JPanel fieldsPanel;
    
    public MgmtProduct(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel)table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        
        // Create the product fields panel
        setupProductFields();
    }
    
    private void setupProductFields() {
        // Initialize fields
        productIdFld = new JTextField();
        productNameFld = new JTextField();
        productStockFld = new JTextField();
        productPriceFld = new JTextField();
        
        // Style fields
        designer(productIdFld, "PRODUCT ID");
        designer(productNameFld, "PRODUCT NAME");
        designer(productStockFld, "PRODUCT STOCK");
        designer(productPriceFld, "PRODUCT PRICE");
        
        // Make ID field read-only
        productIdFld.setEditable(false);
        
        // Create fields panel
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        
        // Add fields to panel
        fieldsPanel.add(productIdFld);
        fieldsPanel.add(productNameFld);
        fieldsPanel.add(productStockFld);
        fieldsPanel.add(productPriceFld);
        
        // Add fields panel to the component
        this.setLayout(new BorderLayout());
        this.add(fieldsPanel, BorderLayout.NORTH);
        this.add(jScrollPane1, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(purchaseBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void init(SQLite sqlite){
        this.sqlite = sqlite;
        loadTable();
        setupValidation();
        
        // Configure button visibility based on user role
        configureButtonVisibility();
    }
    
    private void configureButtonVisibility() {
        // Default state - hide management buttons for clients
        boolean isManagementAllowed = false;
        
        // Get current user role from sqlite
        int userRole = sqlite.getCurrentUserRole();
        
        // Role-based visibility
        // 5 = Admin, 4 = Manager, 3 = Staff, 2 = Client
        if (userRole >= 3) { // Staff, Manager, Admin
            isManagementAllowed = true;
        }
        
        // Configure button visibility
        addBtn.setVisible(isManagementAllowed);
        editBtn.setVisible(isManagementAllowed);
        deleteBtn.setVisible(isManagementAllowed);
        
        // Clients can only purchase
        purchaseBtn.setVisible(true);
    }

    private void setupValidation() {
        ValidationUtils.applyProductFilters(
            productNameFld,
            productStockFld,
            productPriceFld
        );
    }

    public void loadTable(){
        // Clear table
        for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
            tableModel.removeRow(0);
        }
        
        // Load contents
        ArrayList<Product> products = sqlite.getProduct();
        for(int nCtr = 0; nCtr < products.size(); nCtr++){
            tableModel.addRow(new Object[]{
                products.get(nCtr).getId(),
                products.get(nCtr).getName(), 
                products.get(nCtr).getStock(), 
                products.get(nCtr).getPrice()});
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
        purchaseBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Stock", "Price"
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMinWidth(150);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        purchaseBtn.setBackground(new java.awt.Color(255, 255, 255));
        purchaseBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        purchaseBtn.setText("PURCHASE");
        purchaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(255, 255, 255));
        addBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(255, 255, 255));
        editBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editBtn.setText("EDIT");
        editBtn.setToolTipText("");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(255, 255, 255));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            productIdFld.setText(table.getValueAt(row, 0).toString());
            productNameFld.setText(table.getValueAt(row, 1).toString());
            productStockFld.setText(table.getValueAt(row, 2).toString());
            productPriceFld.setText(table.getValueAt(row, 3).toString());
        }
    }
    
    private void purchaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseBtnActionPerformed
        if(table.getSelectedRow() >= 0){
            JTextField stockFld = new JTextField("0");
            designer(stockFld, "PRODUCT STOCK");

            Object[] message = {
                "How many " + tableModel.getValueAt(table.getSelectedRow(), 1) + " do you want to purchase?", stockFld
            };

            int result = JOptionPane.showConfirmDialog(null, message, "PURCHASE PRODUCT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

            if (result == JOptionPane.OK_OPTION) {
                // Validate input
                String stockStr = stockFld.getText();
                String stockError = DataValidator.validateProductStock(stockStr);
                if (stockError != null) {
                    showErrorMessage(stockError);
                    return;
                }
                
                try {
                    int purchaseAmount = Integer.parseInt(stockStr);
                    int productId = (int)tableModel.getValueAt(table.getSelectedRow(), 0);
                    String productName = (String)tableModel.getValueAt(table.getSelectedRow(), 1);
                    int currentStock = (int)tableModel.getValueAt(table.getSelectedRow(), 2);
                    
                    // Check if there's enough stock
                    if (purchaseAmount > currentStock) {
                        showErrorMessage("Not enough stock available. Current stock: " + currentStock);
                        return;
                    }
                    
                    // Process purchase
                    sqlite.purchaseProduct(productId, purchaseAmount);
                    
                    // Log the purchase
                    String timestamp = new java.sql.Timestamp(System.currentTimeMillis()).toString();
                    sqlite.addHistory(sqlite.getCurrentUsername(), productName, purchaseAmount, timestamp);
                    
                    // Reload table
                    loadTable();
                    
                    // Show success message
                    JOptionPane.showMessageDialog(
                        this,
                        "Purchase successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (NumberFormatException e) {
                    showErrorMessage("Invalid number format");
                }
            }
        }
    }//GEN-LAST:event_purchaseBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String name = productNameFld.getText();
        String stockStr = productStockFld.getText();
        String priceStr = productPriceFld.getText();
        
        // Validate product fields
        String nameError = DataValidator.validateProductName(name);
        if (nameError != null) {
            showErrorMessage(nameError);
            return;
        }
        
        String stockError = DataValidator.validateProductStock(stockStr);
        if (stockError != null) {
            showErrorMessage(stockError);
            return;
        }
        
        String priceError = DataValidator.validateProductPrice(priceStr);
        if (priceError != null) {
            showErrorMessage(priceError);
            return;
        }
        
        try {
            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);
            
            // All validation passed, add the product
            sqlite.addProduct(name, stock, price);
            
            // Log the action
            String timestamp = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            sqlite.addLogs("PRODUCT", sqlite.getCurrentUsername(), "Added product: " + name, timestamp);
            
            // Reload table
            loadTable();
            
            // Show success message
            JOptionPane.showMessageDialog(
                this,
                "Product added successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Clear fields
            productIdFld.setText("");
            productNameFld.setText("");
            productStockFld.setText("");
            productPriceFld.setText("");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid number format");
        }
    }

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String id = productIdFld.getText();
        String name = productNameFld.getText();
        String stockStr = productStockFld.getText();
        String priceStr = productPriceFld.getText();
        
        if (id.isEmpty()) {
            showErrorMessage("Please select a product to edit");
            return;
        }
        
        // Validate product fields
        String nameError = DataValidator.validateProductName(name);
        if (nameError != null) {
            showErrorMessage(nameError);
            return;
        }
        
        String stockError = DataValidator.validateProductStock(stockStr);
        if (stockError != null) {
            showErrorMessage(stockError);
            return;
        }
        
        String priceError = DataValidator.validateProductPrice(priceStr);
        if (priceError != null) {
            showErrorMessage(priceError);
            return;
        }
        
        try {
            int productId = Integer.parseInt(id);
            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);
            
            // All validation passed, update the product
            sqlite.updateProduct(productId, name, stock, price);
            
            // Log the action
            String timestamp = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            sqlite.addLogs("PRODUCT", sqlite.getCurrentUsername(), "Updated product: " + name, timestamp);
            
            // Reload table
            loadTable();
            
            // Show success message
            JOptionPane.showMessageDialog(
                this,
                "Product updated successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Clear fields
            productIdFld.setText("");
            productNameFld.setText("");
            productStockFld.setText("");
            productPriceFld.setText("");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid number format");
        }
    }

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(table.getSelectedRow() >= 0){
            int productId = (int)tableModel.getValueAt(table.getSelectedRow(), 0);
            String productName = (String)tableModel.getValueAt(table.getSelectedRow(), 1);
            
            int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete " + productName + "?", 
                "DELETE PRODUCT", 
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                // Delete the product
                sqlite.deleteProduct(productId);
                
                // Log the action
                String timestamp = new java.sql.Timestamp(System.currentTimeMillis()).toString();
                sqlite.addLogs("PRODUCT", sqlite.getCurrentUsername(), "Deleted product: " + productName, timestamp);
                
                // Reload table
                loadTable();
                
                // Clear fields
                productIdFld.setText("");
                productNameFld.setText("");
                productStockFld.setText("");
                productPriceFld.setText("");
                
                // Show success message
                JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Product Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton purchaseBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
