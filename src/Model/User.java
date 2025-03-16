package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int locked = 0;
    private Role role;

    public User(int id, String username, String password, Role role, boolean locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked ? 1 : 0;
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

    public void setLocked(int locked) {
        this.locked = locked;
    }
}
