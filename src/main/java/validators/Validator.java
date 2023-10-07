package validators;

import java.util.regex.Pattern;

public class Validator {
    static boolean validateRegex(String regexPattern, String validatedItem) {
        return Pattern.matches(regexPattern, validatedItem);
    }
}
