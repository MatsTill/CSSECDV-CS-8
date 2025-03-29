package Controller;

import Model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class SessionManager {
    private static final long SESSION_DURATION = 24 * 60 * 60 * 1000; // 24 hours in milliseconds
    private static final String SESSION_FOLDER = "sessions";
    private SQLite sqlite;
    
    public SessionManager(SQLite sqlite) {
        this.sqlite = sqlite;
        // Ensure sessions directory exists
        File dir = new File(SESSION_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    /**
     * Creates a new session for the user
     */
    public String createSession(User user) {
        // Generate a secure random session ID
        SecureRandom random = new SecureRandom();
        byte[] sessionBytes = new byte[32];
        random.nextBytes(sessionBytes);
        String sessionId = Base64.getEncoder().encodeToString(sessionBytes)
                .replaceAll("\\+", "-")
                .replaceAll("/", "_")
                .replaceAll("=", "");
        
        // Set session expiry
        long expiry = System.currentTimeMillis() + SESSION_DURATION;
        
        // Update user object
        user.setSessionId(sessionId);
        user.setSessionExpiry(expiry);
        
        // Save session to database
        sqlite.updateUserSession(user);
        
        // Save session to file for persistence
        saveSessionToFile(user.getUsername(), sessionId);
        
        return sessionId;
    }
    
    /**
     * Validates if a session is still active
     */
    public boolean validateSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return false;
        }
        
        User user = sqlite.getUserBySessionId(sessionId);
        if (user == null) {
            return false;
        }
        
        // Check if session is expired
        if (user.getSessionExpiry() < System.currentTimeMillis()) {
            // Clear expired session
            invalidateSession(sessionId);
            return false;
        }
        
        return true;
    }
    
    /**
     * Invalidates (clears) a session
     */
    public void invalidateSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return;
        }
        
        // Get user by session first
        User user = sqlite.getUserBySessionId(sessionId);
        if (user != null) {
            // Delete session file
            deleteSessionFile(user.getUsername());
        }
        
        // Clear session in database
        sqlite.clearUserSession(sessionId);
    }
    
    /**
     * Invalidates all sessions for a user
     */
    public void invalidateUserSessions(String username) {
        if (username == null || username.isEmpty()) {
            return;
        }
        
        // Delete session file
        deleteSessionFile(username);
        
        // Clear sessions in database
        sqlite.clearUserSessionsByUsername(username);
    }
    
    /**
     * Save session to file for persistence across restarts
     */
    private void saveSessionToFile(String username, String sessionId) {
        if (username == null || sessionId == null || username.isEmpty() || sessionId.isEmpty()) {
            return;
        }
        
        Properties props = new Properties();
        props.setProperty("sessionId", sessionId);
        
        try {
            File file = new File(SESSION_FOLDER + File.separator + username + ".session");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Session data for " + username);
            }
        } catch (IOException e) {
            System.err.println("Error saving session: " + e.getMessage());
        }
    }
    
    /**
     * Load session from file
     */
    public String loadSessionFromFile(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        
        String sessionFile = SESSION_FOLDER + File.separator + username + ".session";
        if (!Files.exists(Paths.get(sessionFile))) {
            return null;
        }
        
        Properties props = new Properties();
        try (FileReader reader = new FileReader(sessionFile)) {
            props.load(reader);
            return props.getProperty("sessionId");
        } catch (IOException e) {
            System.err.println("Error loading session: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Delete session file
     */
    private void deleteSessionFile(String username) {
        if (username == null || username.isEmpty()) {
            return;
        }
        
        try {
            Files.deleteIfExists(Paths.get(SESSION_FOLDER + File.separator + username + ".session"));
        } catch (IOException e) {
            System.err.println("Error deleting session file: " + e.getMessage());
        }
    }
} 