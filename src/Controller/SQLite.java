package Controller;

import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import Model.Role;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SQLite {
    
    public int DEBUG_MODE = 0;
    String driverURL = "jdbc:sqlite:" + "database.db";
    
    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(driverURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " name TEXT NOT NULL,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void createLogsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " event TEXT NOT NULL,\n"
            + " username TEXT NOT NULL,\n"
            + " desc TEXT NOT NULL,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " name TEXT NOT NULL UNIQUE,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " price REAL DEFAULT 0.00\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL UNIQUE,\n"
            + " password TEXT NOT NULL,\n"
            + " salt TEXT NOT NULL, \n"
            + " role TEXT DEFAULT 'CLIENT',\n"
            + " locked INTEGER DEFAULT 0,\n"
            + " failed_attempts INTEGER DEFAULT 0, \n"
            + " lockout_time INTEGER DEFAULT 0, \n"
            + " lockout_count INTEGER DEFAULT 0, \n"                
            + " security_answer1 TEXT,\n"
            + " security_answer2 TEXT,\n"
            + " session_id TEXT,\n"
            + " session_expiry INTEGER DEFAULT 0\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropHistoryTable() {
        String sql = "DROP TABLE IF EXISTS history;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropLogsTable() {
        String sql = "DROP TABLE IF EXISTS logs;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropProductTable() {
        String sql = "DROP TABLE IF EXISTS product;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void addHistory(String username, String name, int stock, String timestamp) {
        // Sanitize inputs to prevent SQL injection
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        String sanitizedName = DataValidator.sanitizeInput(name);
        String sanitizedTimestamp = DataValidator.sanitizeInput(timestamp);
        
        String sql = "INSERT INTO history(username, name, stock, timestamp) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedUsername);
            pstmt.setString(2, sanitizedName);
            pstmt.setInt(3, stock);
            pstmt.setString(4, sanitizedTimestamp);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error adding history: " + ex.getMessage());
        }
    }
    
    public void addLogs(String event, String username, String desc, String timestamp) {
        // Sanitize inputs to prevent SQL injection
        String sanitizedEvent = DataValidator.sanitizeInput(event);
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        String sanitizedDesc = DataValidator.sanitizeInput(desc);
        String sanitizedTimestamp = DataValidator.sanitizeInput(timestamp);
        
        String sql = "INSERT INTO logs(event, username, desc, timestamp) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedEvent);
            pstmt.setString(2, sanitizedUsername);
            pstmt.setString(3, sanitizedDesc);
            pstmt.setString(4, sanitizedTimestamp);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error adding log: " + ex.getMessage());
        }
    }
    
    public void addProduct(String name, int stock, double price) {
        // Validate product data
        String nameError = DataValidator.validateProductName(name);
        String stockError = DataValidator.validateProductStock(Integer.toString(stock));
        String priceError = DataValidator.validateProductPrice(Double.toString(price));
        
        if (nameError != null || stockError != null || priceError != null) {
            System.err.println("Product validation error: " + 
                              (nameError != null ? nameError : "") + 
                              (stockError != null ? " " + stockError : "") + 
                              (priceError != null ? " " + priceError : ""));
            return;
        }
        
        // Sanitize product name
        String sanitizedName = DataValidator.sanitizeInput(name);
        
        String sql = "INSERT INTO product(name, stock, price) VALUES(?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedName);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error adding product: " + ex.getMessage());
        }
    }
    
    public ArrayList<History> getHistory(){
        String sql = "SELECT id, username, name, stock, timestamp FROM history";
        ArrayList<History> histories = new ArrayList<History>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                histories.add(new History(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return histories;
    }
    
    public ArrayList<Logs> getLogs(){
        String sql = "SELECT id, event, username, desc, timestamp FROM logs";
        ArrayList<Logs> logs = new ArrayList<Logs>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                logs.add(new Logs(rs.getInt("id"),
                                   rs.getString("event"),
                                   rs.getString("username"),
                                   rs.getString("desc"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return logs;
    }
    
    public ArrayList<Product> getProduct(){
        String sql = "SELECT id, name, stock, price FROM product";
        ArrayList<Product> products = new ArrayList<Product>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("stock"),
                    rs.getFloat("price")
                ));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return products;
    }
    
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role, locked, session_id, session_expiry FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    Role.valueOf(rs.getString("role")),
                    rs.getBoolean("locked"),
                    rs.getString("session_id"),
                    rs.getLong("session_expiry")
                ));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        
        return users;
    }
    
    public void addUser(String username, String password, Role role, String answer1, String answer2) {
        // Validate input
        String usernameError = DataValidator.validateUsername(username);
        if (usernameError != null) {
            System.err.println("Username validation error: " + usernameError);
            return;
        }

        // Sanitize inputs to prevent SQL injection
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        String sanitizedAnswer1 = DataValidator.sanitizeInput(answer1);
        String sanitizedAnswer2 = DataValidator.sanitizeInput(answer2);
        
        // Hash password with salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashPassword(password, salt);
        
        String sql = "INSERT INTO users(username, password, salt, role, locked, security_answer1, security_answer2) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedUsername);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, saltStr);
            pstmt.setString(4, role.name());
            pstmt.setInt(5, 0); // Not locked
            pstmt.setString(6, sanitizedAnswer1);
            pstmt.setString(7, sanitizedAnswer2);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error adding user: " + ex.getMessage());
        }
    }
    
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username='" + username + "';";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public Product getProduct(String name){
        String sql = "SELECT name, stock, price FROM product WHERE name='" + name + "';";
        Product product = null;
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            product = new Product(rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getFloat("price"));
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return product;
    }

    public AuthStatus authenticate(String username, char[] password) {
        // Validate username
        String usernameError = DataValidator.validateUsername(username);
        if (usernameError != null) {
            System.err.println("Username validation error: " + usernameError);
            return AuthStatus.INVALID_CREDENTIALS;
        }
        
        // Sanitize username
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        
        String sql = "SELECT username, password, salt, locked, role FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedUsername);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int locked = rs.getInt("locked");
                    Role role = Role.valueOf(rs.getString("role"));
                    if (locked == 1 || role == Role.DISABLED) {
                        System.out.println("Account is locked or disabled: " + username);
                        return AuthStatus.ACCOUNT_LOCKED;
                    }
                    
                    String storedPassword = rs.getString("password");
                    String salt = rs.getString("salt");
                    byte[] saltBytes = Base64.getDecoder().decode(salt);
                    
                    // Hash the provided password with the same salt
                    String passwordToCheck = hashPassword(new String(password), saltBytes);
                    
                    // Immediately clear the password from memory
                    java.util.Arrays.fill(password, '0');
                    
                    if (storedPassword.equals(passwordToCheck)) {
                        // Reset failed attempts on successful login
                        resetFailedAttempts(sanitizedUsername);
                        return AuthStatus.SUCCESS;
                    } else {
                        return AuthStatus.INVALID_CREDENTIALS;
                    }
                } else {
                    return AuthStatus.INVALID_CREDENTIALS;
                }
            }
        } catch (Exception ex) {
            System.err.println("Authentication error: " + ex.getMessage());
            return AuthStatus.SYSTEM_ERROR;
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }
    
    public boolean verifySecurityAnswers(String username, String answer1, String answer2) {
        // Sanitize inputs to prevent SQL injection
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        String sanitizedAnswer1 = DataValidator.sanitizeInput(answer1);
        String sanitizedAnswer2 = DataValidator.sanitizeInput(answer2);
        
        String sql = "SELECT security_answer1, security_answer2 FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedUsername);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedAnswer1 = rs.getString("security_answer1");
                    String storedAnswer2 = rs.getString("security_answer2");
                    
                    return sanitizedAnswer1.equalsIgnoreCase(storedAnswer1) && 
                           sanitizedAnswer2.equalsIgnoreCase(storedAnswer2);
                }
            }
        } catch (Exception ex) {
            System.err.println("Error verifying security answers: " + ex.getMessage());
        }
        
        return false;
    }
    
    public void updatePassword(String username, String newPassword) {
        // Sanitize username
        String sanitizedUsername = DataValidator.sanitizeInput(username);
        
        // Hash new password with salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashPassword(newPassword, salt);
        
        String sql = "UPDATE users SET password = ?, salt = ? WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, saltStr);
            pstmt.setString(3, sanitizedUsername);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error updating password: " + ex.getMessage());
        }
    }
    
    public void recordFailedAttempt(String username){
        String sql = "UPDATE users SET failed_attempts = failed_attempts + 1 WHERE username =?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetFailedAttempts(String username) {
        String sql = "UPDATE users SET failed_attempts = 0 WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lockAccount(String username){
        long lockoutTime = System.currentTimeMillis();;
        String sql = "UPDATE users SET locked = 1, lockout_time = ?, lockout_count = lockout_count + 1, role = ? WHERE username =?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, lockoutTime);
            pstmt.setString(2, Role.DISABLED.name());
            pstmt.setString(3, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void unlockAccount(String username) {
        String sql = "UPDATE users SET locked = 0, failed_attempts = 0, lockout_time = 0 WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isAccountLocked(String username) {
        String sql = "SELECT locked, lockout_time, lockout_count FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int locked = rs.getInt("locked");
                long lockoutTime = rs.getLong("lockout_time");
                int lockoutCount = rs.getInt("lockout_count");

                if (locked == 1) {
                    long currentTime = System.currentTimeMillis();
                    long lockoutDuration = 60000; // 1 minute lockout duration
                    if (currentTime - lockoutTime > lockoutDuration) {
                        unlockAccount(username);
                        return false;
                    } else if (lockoutCount >= 3) {
                        return true; // Permanently locked
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public int getFailedAttempts(String username) {
        String sql = "SELECT failed_attempts FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("failed_attempts");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }   
    
 
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        
        return false;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT id, username, password, role, locked, session_id, session_expiry FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    Role.valueOf(rs.getString("role")),
                    rs.getBoolean("locked"),
                    rs.getString("session_id"),
                    rs.getLong("session_expiry")
                );
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        String sql = "SELECT id, username, password, role, locked, session_id, session_expiry FROM users WHERE session_id = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    Role.valueOf(rs.getString("role")),
                    rs.getBoolean("locked"),
                    rs.getString("session_id"),
                    rs.getLong("session_expiry")
                );
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        
        return null;
    }

    public void updateUserSession(User user) {
        String sql = "UPDATE users SET session_id = ?, session_expiry = ? WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getSessionId());
            pstmt.setLong(2, user.getSessionExpiry());
            pstmt.setString(3, user.getUsername());
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public void clearUserSession(String sessionId) {
        String sql = "UPDATE users SET session_id = NULL, session_expiry = 0 WHERE session_id = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sessionId);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public void clearUserSessionsByUsername(String username) {
        String sql = "UPDATE users SET session_id = NULL, session_expiry = 0 WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public void updateProduct(int id, String name, int stock, double price) {
        // Validate product data
        String nameError = DataValidator.validateProductName(name);
        String stockError = DataValidator.validateProductStock(Integer.toString(stock));
        String priceError = DataValidator.validateProductPrice(Double.toString(price));
        
        if (nameError != null || stockError != null || priceError != null) {
            System.err.println("Product validation error: " + 
                              (nameError != null ? nameError : "") + 
                              (stockError != null ? " " + stockError : "") + 
                              (priceError != null ? " " + priceError : ""));
            return;
        }
        
        // Sanitize product name
        String sanitizedName = DataValidator.sanitizeInput(name);
        
        String sql = "UPDATE product SET name = ?, stock = ?, price = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sanitizedName);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, id);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error updating product: " + ex.getMessage());
        }
    }
}

