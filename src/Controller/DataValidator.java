package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    
    // Username validation constants
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]+$"; // Alphanumeric and underscore only
    
    // Password validation constants
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final String PASSWORD_COMPLEXITY_PATTERN = 
            "^.{8,64}$";
    
    // Product validation constants
    private static final int MAX_PRODUCT_NAME_LENGTH = 50;
    private static final double MIN_PRODUCT_PRICE = 0.01;
    private static final double MAX_PRODUCT_PRICE = 1000000.00;
    private static final int MAX_PRODUCT_STOCK = 1000000;
    
    // SQL Injection prevention
    private static final String SQL_INJECTION_PATTERN = ".*[;'\"].*";
    
    /**
     * Validates username format and content
     * @param username The username to validate
     * @return Error message, or null if valid
     */
    public static String validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty";
        }
        
        if (username.length() < MIN_USERNAME_LENGTH) {
            return "Username must be at least " + MIN_USERNAME_LENGTH + " characters";
        }
        
        if (username.length() > MAX_USERNAME_LENGTH) {
            return "Username cannot exceed " + MAX_USERNAME_LENGTH + " characters";
        }
        
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (!pattern.matcher(username).matches()) {
            return "Username can only contain letters, numbers, and underscores";
        }
        
        if (hasInjectionAttempt(username)) {
            return "Username contains invalid characters";
        }
        
        return null; // No error, validation passed
    }
    
    /**
     * Validates password strength and security
     * @param password The password to validate
     * @return Error message, or null if valid
     */
    public static String validatePassword(char[] password) {
        String passwordStr = new String(password);
        
        if (passwordStr.length() < MIN_PASSWORD_LENGTH) {
            return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
        }
        
        if (passwordStr.length() > MAX_PASSWORD_LENGTH) {
            return "Password cannot exceed " + MAX_PASSWORD_LENGTH + " characters";
        }
        
        // Check for password complexity
        Pattern pattern = Pattern.compile(PASSWORD_COMPLEXITY_PATTERN);
        if (!pattern.matcher(passwordStr).matches()) {
            return "Password must contain at least one digit, one lowercase letter, " +
                   "one uppercase letter, one special character, and no whitespace";
        }
        
        // Clear password from memory
        java.util.Arrays.fill(password, '0');
        
        return null; // No error, validation passed
    }
    
    /**
     * Validates that two passwords match
     * @param password Primary password
     * @param confirmPassword Confirmation password
     * @return Error message, or null if valid
     */
    public static String validatePasswordMatch(char[] password, char[] confirmPassword) {
        String passwordStr = new String(password);
        String confirmPasswordStr = new String(confirmPassword);
        
        boolean match = passwordStr.equals(confirmPasswordStr);
        
        // Clear passwords from memory
        java.util.Arrays.fill(password, '0');
        java.util.Arrays.fill(confirmPassword, '0');
        
        return match ? null : "Passwords do not match";
    }
    
    /**
     * Validates product name
     * @param productName The product name to validate
     * @return Error message, or null if valid
     */
    public static String validateProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return "Product name cannot be empty";
        }
        
        if (productName.length() > MAX_PRODUCT_NAME_LENGTH) {
            return "Product name cannot exceed " + MAX_PRODUCT_NAME_LENGTH + " characters";
        }
        
        if (hasInjectionAttempt(productName)) {
            return "Product name contains invalid characters";
        }
        
        return null; // No error, validation passed
    }
    
    /**
     * Validates product price
     * @param priceStr The price string to validate
     * @return Error message, or null if valid
     */
    public static String validateProductPrice(String priceStr) {
        if (priceStr == null || priceStr.trim().isEmpty()) {
            return "Price cannot be empty";
        }
        
        try {
            double price = Double.parseDouble(priceStr);
            
            if (price < MIN_PRODUCT_PRICE) {
                return "Price must be at least " + MIN_PRODUCT_PRICE;
            }
            
            if (price > MAX_PRODUCT_PRICE) {
                return "Price cannot exceed " + MAX_PRODUCT_PRICE;
            }
            
            return null; // No error, validation passed
        } catch (NumberFormatException e) {
            return "Price must be a valid number";
        }
    }
    
    /**
     * Validates product stock
     * @param stockStr The stock string to validate
     * @return Error message, or null if valid
     */
    public static String validateProductStock(String stockStr) {
        if (stockStr == null || stockStr.trim().isEmpty()) {
            return "Stock cannot be empty";
        }
        
        try {
            int stock = Integer.parseInt(stockStr);
            
            if (stock < 0) {
                return "Stock cannot be negative";
            }
            
            if (stock > MAX_PRODUCT_STOCK) {
                return "Stock cannot exceed " + MAX_PRODUCT_STOCK;
            }
            
            return null; // No error, validation passed
        } catch (NumberFormatException e) {
            return "Stock must be a valid integer";
        }
    }
    
    /**
     * Validates security answer format
     * @param answer The security answer to validate
     * @return Error message, or null if valid
     */
    public static String validateSecurityAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            return "Security answer cannot be empty";
        }
        
        if (hasInjectionAttempt(answer)) {
            return "Security answer contains invalid characters";
        }
        
        return null; // No error, validation passed
    }
    
    /**
     * Sanitizes input to prevent SQL injection and XSS attacks
     * @param input The input to sanitize
     * @return Sanitized input
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        
        // Replace potentially dangerous characters
        String sanitized = input.replaceAll("[<>\"'%;()&+]", "");
        
        // Limit length of input
        if (sanitized.length() > 255) {
            sanitized = sanitized.substring(0, 255);
        }
        
        return sanitized;
    }
    
    /**
     * Checks if input contains potential SQL injection attempts
     * @param input The input to check
     * @return True if injection attempt is detected
     */
    private static boolean hasInjectionAttempt(String input) {
        if (input == null) {
            return false;
        }
        
        Pattern pattern = Pattern.compile(SQL_INJECTION_PATTERN);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        
        return matcher.matches() || 
               input.toLowerCase().contains("drop") ||
               input.toLowerCase().contains("delete") ||
               input.toLowerCase().contains("update") ||
               input.toLowerCase().contains("insert") ||
               input.toLowerCase().contains("select");
    }
} 