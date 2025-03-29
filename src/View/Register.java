package View;

import Controller.DataValidator;
import javax.swing.JOptionPane;
import View.ValidationUtils;

public class Register extends javax.swing.JPanel {

    public Frame frame;
    
    public Register() {
        initComponents();
        setupValidation();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        passwordFld = new javax.swing.JPasswordField();
        usernameFld = new javax.swing.JTextField();
        confpassFld = new javax.swing.JPasswordField();
        backBtn = new javax.swing.JButton();
        showConfPassBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        securityQues2 = new javax.swing.JTextField();
        securityQues1 = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Show Password");

        setLayout(null);

        registerBtn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        registerBtn.setText("REGISTER");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        add(registerBtn);
        registerBtn.setBounds(278, 383, 220, 52);

        passwordFld.setBackground(new java.awt.Color(240, 240, 240));
        passwordFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "PASSWORD", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        add(passwordFld);
        passwordFld.setBounds(200, 112, 380, 45);

        usernameFld.setBackground(new java.awt.Color(240, 240, 240));
        usernameFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "USERNAME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        add(usernameFld);
        usernameFld.setBounds(200, 61, 380, 45);

        confpassFld.setBackground(new java.awt.Color(240, 240, 240));
        confpassFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confpassFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        confpassFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CONFIRM PASSWORD", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        add(confpassFld);
        confpassFld.setBounds(200, 163, 380, 45);

        backBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        backBtn.setText("<Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        add(backBtn);
        backBtn.setBounds(6, 6, 72, 21);

        showConfPassBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showConfPassBoxActionPerformed(evt);
            }
        });
        add(showConfPassBox);
        showConfPassBox.setBounds(453, 214, 19, 19);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Show Password");
        add(jLabel3);
        jLabel3.setBounds(478, 214, 90, 16);

        securityQues2.setBackground(new java.awt.Color(240, 240, 240));
        securityQues2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        securityQues2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        securityQues2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "What was the name of your elementary school?", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        securityQues1.setBackground(new java.awt.Color(240, 240, 240));
        securityQues1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        securityQues1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        securityQues1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "What was the name of your first pet?", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        securityQues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                securityQues1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(securityQues2)
                    .addComponent(securityQues1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityQues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(securityQues2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        securityQues2.getAccessibleContext().setAccessibleName("securityQues2");
        securityQues1.getAccessibleContext().setAccessibleName("SecurityQues1");

        add(jPanel1);
        jPanel1.setBounds(200, 251, 380, 114);
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameFld.getText();
        char[] password = passwordFld.getPassword();
        char[] confpass = confpassFld.getPassword();
        String answer1 = securityQues1.getText();
        String answer2 = securityQues2.getText();
        
        // Validate all fields
        String usernameError = DataValidator.validateUsername(username);
        if (usernameError != null) {
            showErrorMessage(usernameError);
            return;
        }
        
        // Check if username already exists
        if (frame.isUsernameTaken(username)) {
            showErrorMessage("Username already exists");
            return;
        }
        
        String passwordError = DataValidator.validatePassword(password.clone());
        if (passwordError != null) {
            showErrorMessage(passwordError);
            return;
        }
        
        String passwordMatchError = DataValidator.validatePasswordMatch(password.clone(), confpass.clone());
        if (passwordMatchError != null) {
            showErrorMessage(passwordMatchError);
            return;
        }
        
        String answer1Error = DataValidator.validateSecurityAnswer(answer1);
        if (answer1Error != null) {
            showErrorMessage("Security answer 1: " + answer1Error);
            return;
        }
        
        String answer2Error = DataValidator.validateSecurityAnswer(answer2);
        if (answer2Error != null) {
            showErrorMessage("Security answer 2: " + answer2Error);
            return;
        }
        
        // All validation passed, register the user
        frame.registerAction(username, password, confpass, answer1, answer2);
        
        // Show success message
        JOptionPane.showMessageDialog(
            this,
            "Registration successful",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        // Clear fields and return to login
        usernameFld.setText("");
        passwordFld.setText("");
        confpassFld.setText("");
        securityQues1.setText("");
        securityQues2.setText("");
        
        frame.loginNav();
    }

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        usernameFld.setText("");
        passwordFld.setText("");
        confpassFld.setText("");
        securityQues1.setText("");
        securityQues2.setText("");
        
        frame.loginNav();
    }//GEN-LAST:event_backBtnActionPerformed

    private void showConfPassBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showConfPassBoxActionPerformed
        if (showConfPassBox.isSelected()) {
            passwordFld.setEchoChar((char) 0);
            confpassFld.setEchoChar((char) 0);
        } else {
            passwordFld.setEchoChar('•');
            confpassFld.setEchoChar('•');
        }
    }//GEN-LAST:event_showConfPassBoxActionPerformed

    private void securityQues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_securityQues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_securityQues1ActionPerformed

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Registration Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    private void setupValidation() {
        ValidationUtils.applyRegistrationFilters(
            usernameFld,
            passwordFld,
            confpassFld,
            securityQues1,
            securityQues2
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPasswordField confpassFld;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField securityQues1;
    private javax.swing.JTextField securityQues2;
    private javax.swing.JCheckBox showConfPassBox;
    private javax.swing.JTextField usernameFld;
    // End of variables declaration//GEN-END:variables
}
