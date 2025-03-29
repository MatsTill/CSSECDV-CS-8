package View;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

/**
 * A document filter for restricting the characters that can be entered in text fields.
 */
public class InputFilter extends DocumentFilter {
    
    // Filter types
    public static final int ALPHANUMERIC_FILTER = 0;
    public static final int USERNAME_FILTER = 1;
    public static final int NUMERIC_FILTER = 2;
    public static final int DECIMAL_FILTER = 3;
    public static final int TEXT_FILTER = 4;
    
    private final Pattern pattern;
    private final int maxLength;
    
    /**
     * Creates a new input filter with the specified filter type and maximum length.
     * 
     * @param filterType The type of filter to apply
     * @param maxLength The maximum length of text allowed (0 for no limit)
     */
    public InputFilter(int filterType, int maxLength) {
        this.maxLength = maxLength;
        
        switch (filterType) {
            case ALPHANUMERIC_FILTER:
                pattern = Pattern.compile("[a-zA-Z0-9]*");
                break;
            case USERNAME_FILTER:
                pattern = Pattern.compile("[a-zA-Z0-9_]*");
                break;
            case NUMERIC_FILTER:
                pattern = Pattern.compile("[0-9]*");
                break;
            case DECIMAL_FILTER:
                pattern = Pattern.compile("[0-9.]*");
                break;
            case TEXT_FILTER:
                pattern = Pattern.compile("[a-zA-Z0-9\\s.,?!-_@#$%^&*()]*");
                break;
            default:
                pattern = Pattern.compile(".*");
                break;
        }
    }
    
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) 
            throws BadLocationException {
        
        // If text is null or empty, just pass it through
        if (text == null || text.isEmpty()) {
            super.insertString(fb, offset, text, attrs);
            return;
        }
        
        // Check if the insertion would exceed the maximum length
        if (maxLength > 0 && fb.getDocument().getLength() + text.length() > maxLength) {
            return;
        }
        
        // Apply the pattern filter
        if (pattern.matcher(text).matches()) {
            super.insertString(fb, offset, text, attrs);
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
            throws BadLocationException {
        
        // If text is null or empty, just pass it through
        if (text == null || text.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }
        
        // Check if the replacement would exceed the maximum length
        if (maxLength > 0 && fb.getDocument().getLength() - length + text.length() > maxLength) {
            return;
        }
        
        // Apply the pattern filter
        if (pattern.matcher(text).matches()) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
} 