package Controller;


import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import Model.Role;
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

        sqlite.createNewDatabase(); // Connect to db
       
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

        sqlite.addUser("admin", "qwerty1234", Role.ADMIN, "Blue", "SPCP");
        sqlite.addUser("manager", "qwerty1234", Role.MANAGER, "Smith", "CSA");
        sqlite.addUser("staff", "qwerty1234", Role.STAFF, "Buddy", "LSM");
        sqlite.addUser("client1", "qwerty1234", Role.CLIENT, "Tilly", "Don Bosco");
        sqlite.addUser("client2", "qwerty1234", Role.CLIENT, "Angel", "DLSU");

        sqlite.addProduct("Beans", 10, 10.0);

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
    
    // public boolean hasRole(Role requiredRole) {
    //     return isUserAuthenticated() && currentUser.getRole() == requiredRole;
    // }
    
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
