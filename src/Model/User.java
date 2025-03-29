package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int locked = 0;
    private Role role;
    private String sessionId;
    private long sessionExpiry;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public User(int id, String username, String password, Role role, boolean locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked ? 1 : 0;
    }
    
    public User(int id, String username, String password, Role role, int locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked;
    }
    
    public User(int id, String username, String password, Role role, int locked, String sessionId, long sessionExpiry){
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

    public int getLocked() {
        return locked;
    }
    
    public boolean isLocked() {
        return locked == 1;
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
