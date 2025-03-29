package Controller;

import Model.History;
import Model.Logs;
import Model.Product;
import Model.Role;
import Model.User;
import View.Frame;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    
    public SQLite sqlite;
    public SessionManager sessionManager;
    private User currentUser = null;
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public void logout() {
        if (currentUser != null && currentUser.getSessionId() != null) {
            sessionManager.invalidateSession(currentUser.getSessionId());
        }
        currentUser = null;
        sqlite.logout();
    }
    
    public boolean isUserAuthenticated() {
        return currentUser != null;
    }
    
    public boolean hasRole(Role requiredRole) {
        if (currentUser == null) {
            return false;
        }
        
        // Compare enum ordinal values for proper role hierarchical check
        return currentUser.getRole().ordinal() >= requiredRole.ordinal();
    }
    
    public boolean tryRestoreSession(String sessionId) {
        if (sessionId != null && !sessionId.isEmpty()) {
            if (sessionManager.validateSession(sessionId)) {
                User user = sqlite.getUserBySessionId(sessionId);
                if (user != null) {
                    setCurrentUser(user);
                    sqlite.setCurrentUser(user);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        new Main().init();
    }
    
    public void init(){
        // Initialize SQLite database
        sqlite = new SQLite();
        sqlite.createNewDatabase();
       
        // Initialize SessionManager
        sessionManager = new SessionManager(sqlite);
       
        // Drop tables if needed (comment these out in production)
        sqlite.dropHistoryTable();
        sqlite.dropLogsTable();
        sqlite.dropProductTable();
        sqlite.dropUserTable();
       
        // Create tables if not exist
        sqlite.createHistoryTable();
        sqlite.createLogsTable();
        sqlite.createProductTable();
        sqlite.createUserTable();

        // Add sample users with different roles using Role enum
        sqlite.addUser("admin", "qwerty1234", Role.ADMIN, "Blue", "SPCP");
        sqlite.addUser("manager", "qwerty1234", Role.MANAGER, "Smith", "CSA");
        sqlite.addUser("staff", "qwerty1234", Role.STAFF, "Buddy", "LSM");
        sqlite.addUser("client1", "qwerty1234", Role.CLIENT, "Tilly", "Don Bosco");
        sqlite.addUser("client2", "qwerty1234", Role.CLIENT, "Angel", "DLSU");
        sqlite.addUser("disabled", "qwerty1234", Role.DISABLED, "Locked", "Account");
        
        // Initialize User Interface
        Frame frame = new Frame();
        frame.init(this);
    }
    
    public void purchaseProduct(User user, Product product) {
        if (AuthorizeRole.canPurchase(user)) {
            // Logic to purchase product
        } else {
            System.out.println("Access Denied: You do not have permission to purchase products.");
        }
    }

    public void manageProducts(User user) {
        if (AuthorizeRole.canManageProducts(user)) {
            // Logic to manage products
        } else {
            System.out.println("Access Denied: You do not have permission to manage products.");
        }
    }

    public void viewAllPurchaseHistory(User user) {
        if (AuthorizeRole.canViewAllPurchaseHistory(user)) {
            // Logic to view all purchase history
        } else {
            System.out.println("Access Denied: You do not have permission to view all purchase history.");
        }
    }

    public void manageUsers(User user) {
        if (AuthorizeRole.canManageUsers(user)) {
            // Logic to manage users
        } else {
            System.out.println("Access Denied: You do not have permission to manage users.");
        }
    }

    public void manageLogs(User user) {
        if (AuthorizeRole.canManageLogs(user)) {
            // Logic to manage logs
        } else {
            System.out.println("Access Denied: You do not have permission to manage logs.");
        }
    }
}
