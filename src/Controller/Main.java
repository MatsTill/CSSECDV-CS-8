package Controller;


import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import View.Frame;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;



public class Main {
    
    public SQLite sqlite;
    public SessionManager sessionManager;
    private User currentUser;
    
    public static void main(String[] args) {
        new Main().init();
    }
    
    public void init(){
        // Initialize a driver object
        sqlite = new SQLite();
        sessionManager = new SessionManager(sqlite);

        sqlite.createNewDatabase();
       
        // Drop users table if needed
        sqlite.dropHistoryTable();
        sqlite.dropLogsTable();
        sqlite.dropProductTable();
        sqlite.dropUserTable();
       
        // Create users table if not exist
        sqlite.createHistoryTable();
        sqlite.createLogsTable();
        sqlite.createProductTable();
        sqlite.createUserTable();

        sqlite.addUser("admin", "qwerty1234", 5, "Blue", "SPCP");
        sqlite.addUser("manager", "qwerty1234", 4, "Smith", "CSA");
        sqlite.addUser("staff", "qwerty1234", 3, "Buddy", "LSM");
        sqlite.addUser("client1", "qwerty1234", 2, "Tilly", "Don Bosco");
        sqlite.addUser("client2", "qwerty1234", 2, "Angel", "DLSU");
        
        // Initialize User Interface
        Frame frame = new Frame();
        frame.init(this);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user != null) {
            sessionManager.createSession(user);
        }
    }
    
    public void logout() {
        if (currentUser != null) {
            sessionManager.invalidateUserSessions(currentUser.getUsername());
            currentUser = null;
        }
    }
    
    public boolean isUserAuthenticated() {
        return currentUser != null && sessionManager.isSessionValid(currentUser);
    }
    
    public boolean hasRole(int requiredRole) {
        return isUserAuthenticated() && currentUser.getRole() >= requiredRole;
    }
    
    public boolean tryRestoreSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return false;
        }
        
        User user = sessionManager.getUserBySessionId(sessionId);
        if (user != null) {
            currentUser = user;
            return true;
        }
        
        return false;
    }
}
