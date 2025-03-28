package Controller;

import Model.User;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SessionManager {
    private static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes in milliseconds
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    
    private Map<String, User> activeSessions = new HashMap<>();
    private SQLite sqlite;
    
    private static final String SESSION_FILE = "session.dat";
    
    public SessionManager(SQLite sqlite) {
        this.sqlite = sqlite;
    }
    
    public String createSession(User user) {
        // Generate a random session ID
        String sessionId = generateSessionId();
        
        // Set session expiry time
        long expiryTime = System.currentTimeMillis() + SESSION_TIMEOUT;
        
        // Update user with session info
        user.setSessionId(sessionId);
        user.setSessionExpiry(expiryTime);
        
        // Store in database
        updateUserSession(user);
        
        // Add to in-memory cache
        activeSessions.put(sessionId, user);
        
        // Save session to file
        saveSessionToFile(user.getUsername(), sessionId);
        
        return sessionId;
    }
    
    public User getUserBySessionId(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return null;
        }
        
        // Check in-memory cache first
        User user = activeSessions.get(sessionId);
        
        if (user == null) {
            // If not in cache, try database
            user = sqlite.getUserBySessionId(sessionId);
            if (user != null) {
                activeSessions.put(sessionId, user);
            }
        }
        
        if (user != null && isSessionValid(user)) {
            // Extend session if valid
            extendSession(user);
            return user;
        } else if (user != null) {
            // Invalidate expired session
            invalidateSession(sessionId);
            return null;
        }
        
        return null;
    }
    
    public boolean isSessionValid(User user) {
        return user != null && user.isSessionValid();
    }
    
    public void extendSession(User user) {
        if (user != null && user.getSessionId() != null) {
            // Extend session timeout
            long newExpiry = System.currentTimeMillis() + SESSION_TIMEOUT;
            user.setSessionExpiry(newExpiry);
            updateUserSession(user);
        }
    }
    
    public void invalidateSession(String sessionId) {
        if (sessionId != null) {
            User user = activeSessions.get(sessionId);
            if (user != null) {
                deleteSessionFile(user.getUsername());
            }
            
            activeSessions.remove(sessionId);
            sqlite.clearUserSession(sessionId);
        }
    }
    
    public void invalidateUserSessions(String username) {
        if (username != null) {
            // Remove from in-memory cache
            activeSessions.entrySet().removeIf(entry -> 
                entry.getValue().getUsername().equals(username));
            
            // Clear from database
            sqlite.clearUserSessionsByUsername(username);
            
            // Delete session file
            deleteSessionFile(username);
        }
    }
    
    private String generateSessionId() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
    
    private void updateUserSession(User user) {
        sqlite.updateUserSession(user);
    }
    
    private void saveSessionToFile(String username, String sessionId) {
        try {
            File file = new File(username + "_" + SESSION_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(sessionId);
            }
        } catch (IOException e) {
            System.err.println("Error saving session: " + e.getMessage());
        }
    }
    
    public String loadSessionFromFile(String username) {
        try {
            File file = new File(username + "_" + SESSION_FILE);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    return reader.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading session: " + e.getMessage());
        }
        return null;
    }
    
    private void deleteSessionFile(String username) {
        try {
            File file = new File(username + "_" + SESSION_FILE);
            if (file.exists()) {
                if (!file.delete()) {
                    System.err.println("Failed to delete session file for user: " + username);
                }
            }
        } catch (Exception e) {
            System.err.println("Error deleting session file: " + e.getMessage());
        }
    }
} 