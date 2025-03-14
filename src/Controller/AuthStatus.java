package Controller;

public enum AuthStatus {
    SUCCESS, 
    INVALID_CREDENTIALS, // FOR BOTH USERNAME AND PASS
    ACCOUNT_LOCKED,
    SYSTEM_ERROR
}