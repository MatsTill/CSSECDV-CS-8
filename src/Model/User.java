package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int role = 2;
    private int locked = 0;
    private String sessionId;
    private long sessionExpiry;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public User(int id, String username, String password, int role, int locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked;
    }
    
    public User(int id, String username, String password, int role, int locked, String sessionId, long sessionExpiry){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked;
        this.sessionId = sessionId;
        this.sessionExpiry = sessionExpiry;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public long getSessionExpiry() {
        return sessionExpiry;
    }
    
    public void setSessionExpiry(long sessionExpiry) {
        this.sessionExpiry = sessionExpiry;
    }
    
    public boolean isSessionValid() {
        return sessionId != null && sessionExpiry > System.currentTimeMillis();
    }
}
