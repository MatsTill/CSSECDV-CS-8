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
import Model.User;
import Model.Role;

/**
 *
 * @author beepxD
 */
public class MgmtProduct extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;
    private User currentUser;
    
    // Add product field declarations
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
        
        // Add fields panel to the top of the component
        this.setLayout(new BorderLayout());
        this.add(fieldsPanel, BorderLayout.NORTH);
        this.add(jScrollPane1, BorderLayout.CENTER);
        
        // Add buttons panel at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(purchaseBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void init(SQLite sqlite) {
        this.sqlite = sqlite;
        loadTable();
        setupValidation();
    }

    public void init(SQLite sqlite, User user) {
        this.sqlite = sqlite;
        this.currentUser = user;
        loadTable();
        setupValidation();
        setupButtonsByRole();
    }
    
    private void setupButtonsByRole() {
        if (currentUser == null) {
            // If no user is provided, show all buttons (default behavior)
            return;
        }
        
        Role role = currentUser.getRole();
        
        // Configure button visibility based on role
        if (role == Role.CLIENT) {
            // Clients can only purchase
            purchaseBtn.setVisible(true);
            addBtn.setVisible(false);
            editBtn.setVisible(false);
            deleteBtn.setVisible(false);
            
            // Make fields read-only for clients
            productNameFld.setEditable(false);
            productStockFld.setEditable(false);
            productPriceFld.setEditable(false);
        } else if (role == Role.STAFF || role == Role.MANAGER || role == Role.ADMIN) {
            // Staff, Managers and Admins can add, edit, delete, but not purchase
            purchaseBtn.setVisible(false);
            addBtn.setVisible(true);
            editBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }
    }

    private void setupValidation() {
        ValidationUtils.applyProductFilters(
            productNameFld,
            productStockFld,
            productPriceFld
        );
    }

    public void loadTable(){
        //      CLEAR TABLE
        for(int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--){
            tableModel.removeRow(0);
        }
        
        //      LOAD CONTENTS
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
            // Get the selected product details
            int productId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            String productName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
            int currentStock = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 2).toString());
            double price = Double.parseDouble(tableModel.getValueAt(table.getSelectedRow(), 3).toString());
            
            // Create input field for quantity
            JTextField stockFld = new JTextField("1");
            designer(stockFld, "QUANTITY TO PURCHASE");
            
            // Apply numeric validation filter to quantity field
            ValidationUtils.applyNumericFilter(stockFld, 7);
            
            // Validate stock is available
            if(currentStock <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Sorry, this product is out of stock.", 
                    "Out of Stock", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Object[] message = {
                "How many " + productName + " do you want to purchase?", 
                stockFld
            };

            int result = JOptionPane.showConfirmDialog(null, message, "PURCHASE PRODUCT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

            if (result == JOptionPane.OK_OPTION) {
                // Validate quantity input
                String quantityStr = stockFld.getText();
                String quantityError = DataValidator.validateProductStock(quantityStr);
                
                if (quantityError != null) {
                    showErrorMessage(quantityError);
                    return;
                }
                
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    
                    // Validate against available stock
                    if (quantity <= 0) {
                        showErrorMessage("Quantity must be greater than zero");
                        return;
                    }
                    
                    if (quantity > currentStock) {
                        showErrorMessage("Cannot purchase more than available stock (" + currentStock + ")");
                        return;
                    }
                    
                    // Update product stock
                    int newStock = currentStock - quantity;
                    sqlite.updateProduct(productId, productName, newStock, price);
                    
                    // Get current timestamp
                    String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                    
                    // Record the purchase in history
                    String username = currentUser != null ? currentUser.getUsername() : "client";
                    sqlite.addHistory(username, productName, quantity, timestamp);
                    
                    // Also log the purchase in logs
                    sqlite.addLogs("PURCHASE", username, 
                        "Purchased " + quantity + " " + productName + "(s) at $" + price + " each. Total: $" + (price * quantity), 
                        timestamp);
                    
                    // Show success message
                    JOptionPane.showMessageDialog(
                        this,
                        "Successfully purchased " + quantity + " " + productName + "(s)",
                        "Purchase Complete",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    // Refresh table
                    loadTable();
                    
                } catch (NumberFormatException e) {
                    showErrorMessage("Invalid quantity format");
                }
            }
        } else {
            showErrorMessage("Please select a product to purchase");
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
            
            // Log the addition
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            String username = currentUser != null ? currentUser.getUsername() : "system";
            sqlite.addLogs("ADD", username, "Added product: " + name + ", Stock: " + stock + ", Price: " + price, timestamp);
            
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
            
            // Get original product values to log the changes
            int selectedRow = table.getSelectedRow();
            String originalName = tableModel.getValueAt(selectedRow, 1).toString();
            int originalStock = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
            double originalPrice = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString());
            
            // All validation passed, update the product
            sqlite.updateProduct(productId, name, stock, price);
            
            // Log the edit
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            String username = currentUser != null ? currentUser.getUsername() : "system";
            String changes = "Changed product: " + originalName + " (ID: " + productId + ")";
            
            if (!originalName.equals(name)) {
                changes += ", Name: " + originalName + " -> " + name;
            }
            if (originalStock != stock) {
                changes += ", Stock: " + originalStock + " -> " + stock;
            }
            if (originalPrice != price) {
                changes += ", Price: " + originalPrice + " -> " + price;
            }
            
            sqlite.addLogs("EDIT", username, changes, timestamp);
            
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
            int productId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            String productName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
            
            int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete " + productName + "?", 
                "DELETE PRODUCT", 
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                // Delete the product
                sqlite.deleteProduct(productId);
                
                // Log the deletion
                String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                String username = currentUser != null ? currentUser.getUsername() : "system";
                sqlite.addLogs("DELETE", username, "Deleted product: " + productName, timestamp);
                
                // Show success message
                JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                // Refresh table
                loadTable();
                
                // Clear fields
                productIdFld.setText("");
                productNameFld.setText("");
                productStockFld.setText("");
                productPriceFld.setText("");
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
