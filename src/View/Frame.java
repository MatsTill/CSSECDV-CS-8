package View;

import Controller.Main;
import Controller.AuthStatus;
import Controller.DataValidator;
import Model.User;
import Model.Role;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Frame extends javax.swing.JFrame {

    private Main main;
    private User currentUser;
    public Login loginPnl = new Login();
    public Register registerPnl = new Register();

    private AdminHome adminHomePnl = new AdminHome();
    private ManagerHome managerHomePnl = new ManagerHome();
    private StaffHome staffHomePnl = new StaffHome();
    private ClientHome clientHomePnl = new ClientHome();

    private CardLayout contentView = new CardLayout();
    private CardLayout frameView = new CardLayout();

    public Frame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Container = new javax.swing.JPanel();
        HomePnl = new javax.swing.JPanel();
        Content = new javax.swing.JPanel();
        Navigation = new javax.swing.JPanel();
        adminBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        managerBtn = new javax.swing.JButton();
        staffBtn = new javax.swing.JButton();
        clientBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        changePassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setMinimumSize(new java.awt.Dimension(800, 450));

        HomePnl.setBackground(new java.awt.Color(102, 102, 102));
        HomePnl.setPreferredSize(new java.awt.Dimension(801, 500));

        Navigation.setBackground(new java.awt.Color(204, 204, 204));

        adminBtn.setBackground(new java.awt.Color(250, 250, 250));
        adminBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        adminBtn.setText("Admin Home");
        adminBtn.setFocusable(false);
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SECURITY Svcs");
        jLabel1.setToolTipText("");

        managerBtn.setBackground(new java.awt.Color(250, 250, 250));
        managerBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        managerBtn.setText("Manager Home");
        managerBtn.setFocusable(false);
        managerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerBtnActionPerformed(evt);
            }
        });

        staffBtn.setBackground(new java.awt.Color(250, 250, 250));
        staffBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        staffBtn.setText("Staff Home");
        staffBtn.setFocusable(false);
        staffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffBtnActionPerformed(evt);
            }
        });

        clientBtn.setBackground(new java.awt.Color(250, 250, 250));
        clientBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        clientBtn.setText("Client Home");
        clientBtn.setFocusable(false);
        clientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientBtnActionPerformed(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(250, 250, 250));
        logoutBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logoutBtn.setText("LOGOUT");
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        changePassword.setBackground(new java.awt.Color(250, 250, 250));
        changePassword.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        changePassword.setText("Change Password");
        changePassword.setFocusable(false);
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(managerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staffBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(managerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(changePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout HomePnlLayout = new javax.swing.GroupLayout(HomePnl);
        HomePnl.setLayout(HomePnlLayout);
        HomePnlLayout.setHorizontalGroup(
            HomePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomePnlLayout.createSequentialGroup()
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HomePnlLayout.setVerticalGroup(
            HomePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ContainerLayout = new javax.swing.GroupLayout(Container);
        Container.setLayout(ContainerLayout);
        ContainerLayout.setHorizontalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
            .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(HomePnl, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
        );
        ContainerLayout.setVerticalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(HomePnl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        // Redirect to Admin Home without additional checks
        adminHomePnl.showPnl("home");
        contentView.show(Content, "adminHomePnl");
    }//GEN-LAST:event_adminBtnActionPerformed

    private void managerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerBtnActionPerformed
        // Redirect to Manager Home without additional checks
        managerHomePnl.showPnl("home");
        contentView.show(Content, "managerHomePnl");
    }//GEN-LAST:event_managerBtnActionPerformed

    private void staffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffBtnActionPerformed
        // Redirect to Staff Home without additional checks
        staffHomePnl.showPnl("home");
        contentView.show(Content, "staffHomePnl");
    }//GEN-LAST:event_staffBtnActionPerformed

    private void clientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientBtnActionPerformed
        // Redirect to Client Home without additional checks
        clientHomePnl.showPnl("home");
        contentView.show(Content, "clientHomePnl");
    }//GEN-LAST:event_clientBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // Log logout event before actually logging out
        if (main.getCurrentUser() != null) {
            String username = main.getCurrentUser().getUsername();
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            main.sqlite.addLogs("USER_LOGOUT", username, "User logged out", timestamp);
        }
        
        main.logout();
        frameView.show(Container, "loginPnl");
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        // Create and show a password change dialog
        javax.swing.JPasswordField currentPasswordField = new javax.swing.JPasswordField();
        javax.swing.JPasswordField newPasswordField = new javax.swing.JPasswordField();
        javax.swing.JPasswordField confirmPasswordField = new javax.swing.JPasswordField();
        
        Object[] message = {
            "Current Password:", currentPasswordField,
            "New Password:", newPasswordField,
            "Confirm New Password:", confirmPasswordField
        };
        
        int option = javax.swing.JOptionPane.showConfirmDialog(
            this, 
            message, 
            "Change Password", 
            javax.swing.JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == javax.swing.JOptionPane.OK_OPTION) {
            char[] currentPassword = currentPasswordField.getPassword();
            char[] newPassword = newPasswordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();
            
            // Validate current password not empty
            if (currentPassword.length == 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Current password is required",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            // Validate new password
            String passwordError = DataValidator.validatePassword(newPassword.clone());
            if (passwordError != null) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    passwordError,
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            // Check if passwords match
            String passwordMatchError = DataValidator.validatePasswordMatch(newPassword.clone(), confirmPassword.clone());
            if (passwordMatchError != null) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    passwordMatchError,
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            // Try to change password
            boolean success = changePassword(currentPassword, newPassword, confirmPassword);
            
            if (success) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Password changed successfully",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Failed to change password. Please verify your current password.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_changePasswordActionPerformed

    public void init(Main controller){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("CSSECDV - SECURITY Svcs");
        this.setLocationRelativeTo(null);
        
        this.main = controller;
        loginPnl.frame = this;
        registerPnl.frame = this;
        
        // Initialize panels with user
        adminHomePnl.init(main.sqlite);
        clientHomePnl.init(main.sqlite);
        managerHomePnl.init(main.sqlite, currentUser);
        staffHomePnl.init(main.sqlite, currentUser);
        
        Container.setLayout(frameView);
        Container.add(loginPnl, "loginPnl");
        Container.add(registerPnl, "registerPnl");
        Container.add(HomePnl, "homePnl");
        frameView.show(Container, "loginPnl");
        
        Content.setLayout(contentView);
        Content.add(adminHomePnl, "adminHomePnl");
        Content.add(managerHomePnl, "managerHomePnl");
        Content.add(staffHomePnl, "staffHomePnl");
        Content.add(clientHomePnl, "clientHomePnl");
        
        this.setVisible(true);
    }
    
    public void mainNav(){
        if (!main.isUserAuthenticated()) {
            frameView.show(Container, "loginPnl");
            return;
        }
        
        User user = main.getCurrentUser();
        Role role = user.getRole();
        
        // Update home panels with current user
        if (role == Role.ADMIN) {
            adminHomePnl.init(main.sqlite, user);
        } else if (role == Role.MANAGER) {
            managerHomePnl.init(main.sqlite, user);
        } else if (role == Role.STAFF) {
            staffHomePnl.init(main.sqlite, user);
        } else {
            clientHomePnl.init(main.sqlite, user);
        }
        
        // Show appropriate UI elements based on role
        adminBtn.setVisible(role == Role.ADMIN);
        managerBtn.setVisible(role == Role.MANAGER);
        staffBtn.setVisible(role == Role.STAFF);
        clientBtn.setVisible(role == Role.CLIENT);
        
        frameView.show(Container, "homePnl");
        
        // Direct to appropriate home panel based on role
        if (role == Role.ADMIN) {
            adminHomePnl.showPnl("home");
            contentView.show(Content, "adminHomePnl");
        } else if (role == Role.MANAGER) {
            managerHomePnl.showPnl("home");
            contentView.show(Content, "managerHomePnl");
        } else if (role == Role.STAFF) {
            staffHomePnl.showPnl("home");
            contentView.show(Content, "staffHomePnl");
        } else {
            clientHomePnl.showPnl("home");
            contentView.show(Content, "clientHomePnl");
        }
    }
    
    public void loginNav(){
        frameView.show(Container, "loginPnl");
    }
    
    public void registerNav(){
        frameView.show(Container, "registerPnl");
    }
    
    public void registerAction(String username, char[] password, char[] confpass, String ques1, String ques2){
        String passwordStr = new String(password);

        main.sqlite.addUser(username, passwordStr, Role.CLIENT, ques1, ques2); // Assuming default values for the additional arguments

        // Log registration event
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        main.sqlite.addLogs("USER_REGISTER", username, "New user registered with username: " + username, timestamp);

        // for clearing out memory
        java.util.Arrays.fill(password, '0');
        java.util.Arrays.fill(confpass, '0');
    }

    public AuthStatus loginAction(String username, char[] password) {
        AuthStatus status = main.sqlite.authenticate(username, password);
        
        if (status == AuthStatus.SUCCESS) {
            User user = main.sqlite.getUserByUsername(username);
            if (user != null) {
                main.setCurrentUser(user);
                
                // Log successful login
                String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                main.sqlite.addLogs("USER_LOGIN", username, "User logged in successfully", timestamp);
            }
        } else {
            // Log failed login attempt
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            main.sqlite.addLogs("LOGIN_FAILED", username, "Failed login attempt for username: " + username, timestamp);
        }
        
        return status;
    }

    public boolean isUsernameTaken(String username) {
        // Check if username exists in database
        return main.sqlite.checkUsernameExists(username);
    }
    
    public boolean changePassword(char[] currentPassword, char[] newPassword, char[] confirmPassword) {
        if (main.getCurrentUser() == null) {
            return false;
        }
        
        String username = main.getCurrentUser().getUsername();
        
        // Verify current password
        boolean passwordVerified = main.sqlite.verifyCurrentPassword(username, currentPassword);
        if (!passwordVerified) {
            return false;
        }
        
        // Validate new password
        String passwordError = DataValidator.validatePassword(newPassword.clone());
        if (passwordError != null) {
            return false;
        }
        
        // Check if passwords match
        String passwordMatchError = DataValidator.validatePasswordMatch(newPassword.clone(), confirmPassword.clone());
        if (passwordMatchError != null) {
            return false;
        }
        
        // Update password in database
        main.sqlite.updatePassword(username, new String(newPassword));
        
        // Log password change event
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        main.sqlite.addLogs("PASSWORD_CHANGE", username, "User changed password", timestamp);
        
        // Clear sensitive data from memory
        java.util.Arrays.fill(currentPassword, '0');
        java.util.Arrays.fill(newPassword, '0');
        java.util.Arrays.fill(confirmPassword, '0');
        
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JPanel Content;
    private javax.swing.JPanel HomePnl;
    private javax.swing.JPanel Navigation;
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton changePassword;
    private javax.swing.JButton clientBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton managerBtn;
    private javax.swing.JButton staffBtn;
    // End of variables declaration//GEN-END:variables
}
