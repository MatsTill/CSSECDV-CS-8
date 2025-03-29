package View;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.JPasswordField;

/**
 * Utility class for applying validation to UI components.
 */
public class ValidationUtils {
    
    /**
     * Apply a username filter to a text field, restricting input to valid username characters.
     * 
     * @param textField The text field to apply the filter to
     * @param maxLength The maximum length of username allowed
     */
    public static void applyUsernameFilter(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new InputFilter(InputFilter.USERNAME_FILTER, maxLength));
    }
    
    /**
     * Apply a numeric filter to a text field, allowing only numbers.
     * 
     * @param textField The text field to apply the filter to
     * @param maxLength The maximum length of input allowed
     */
    public static void applyNumericFilter(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new InputFilter(InputFilter.NUMERIC_FILTER, maxLength));
    }
    
    /**
     * Apply a decimal filter to a text field, allowing numbers and decimal point.
     * 
     * @param textField The text field to apply the filter to
     * @param maxLength The maximum length of input allowed
     */
    public static void applyDecimalFilter(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new InputFilter(InputFilter.DECIMAL_FILTER, maxLength));
    }
    
    /**
     * Apply a text filter to a text field, allowing alphanumeric characters and common punctuation.
     * 
     * @param textField The text field to apply the filter to
     * @param maxLength The maximum length of input allowed
     */
    public static void applyTextFilter(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new InputFilter(InputFilter.TEXT_FILTER, maxLength));
    }
    
    /**
     * Apply filters to registration form fields.
     * 
     * @param usernameFld The username field
     * @param passwordFld The password field
     * @param confPasswordFld The confirm password field
     * @param securityAnswer1 Security answer 1 field
     * @param securityAnswer2 Security answer 2 field
     */
    public static void applyRegistrationFilters(
            JTextField usernameFld,
            JPasswordField passwordFld,
            JPasswordField confPasswordFld,
            JTextField securityAnswer1,
            JTextField securityAnswer2) {
        
        applyUsernameFilter(usernameFld, 20);
        applyTextFilter(securityAnswer1, 50);
        applyTextFilter(securityAnswer2, 50);
    }
    
    /**
     * Apply filters to product management form fields.
     * 
     * @param productNameFld The product name field
     * @param productStockFld The product stock field
     * @param productPriceFld The product price field
     */
    public static void applyProductFilters(
            JTextField productNameFld,
            JTextField productStockFld,
            JTextField productPriceFld) {
        
        applyTextFilter(productNameFld, 50);
        applyNumericFilter(productStockFld, 7);
        applyDecimalFilter(productPriceFld, 10);
    }
} 