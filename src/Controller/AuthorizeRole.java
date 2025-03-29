package Controller;

import Model.Role;
import Model.User;

public class AuthorizeRole {

    public static boolean canPurchase(User user) {
        return user.getRole() == Role.CLIENT;
    }

    public static boolean canManageProducts(User user) {
        return user.getRole() == Role.STAFF || user.getRole() == Role.MANAGER;
    }

    public static boolean canViewAllPurchaseHistory(User user) {
        return user.getRole() == Role.MANAGER;
    }

    public static boolean canManageUsers(User user) {
        return user.getRole() == Role.ADMIN;
    }

    public static boolean canManageLogs(User user) {
        return user.getRole() == Role.ADMIN;
    }
}