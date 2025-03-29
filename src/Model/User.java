package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;
    private boolean locked = false;
    private String sessionId;
    private long sessionExpiry;
    
    public User(int id, String username, String password, Role role, boolean locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked;
    }
    
    public User(int id, String username, String password, Role role, boolean locked, String sessionId, long sessionExpiry){
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
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
