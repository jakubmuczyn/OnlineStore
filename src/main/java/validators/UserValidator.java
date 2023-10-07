package validators;

public class UserValidator {
    public void validatePassword(String password) throws IllegalArgumentException {
        String regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,}$";
        if (!Validator.validateRegex(regex, password))
            throw new IllegalArgumentException(("Niepoprawny format hasła"));
    }
    public void validateUsername(String username) throws IllegalArgumentException {
        String regex = "^\\w{4,}$";
        if (!Validator.validateRegex(regex, username))
            throw new IllegalArgumentException("Niepoprawny format nazwy użytkownika.");
    }
    public void validateEmail(String email) throws IllegalArgumentException {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[a-zA-Z]{2,}";
        if (!Validator.validateRegex(regex, email))
            throw new IllegalArgumentException("Niepoprawny format adresu email.");
    }

    public void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        String regex = "^(?:\\+\\d{2,3})?(?:\\d{9}|\\d{3}-\\d{3}-\\d{3})$";
        if (!Validator.validateRegex(regex, phoneNumber))
            throw new IllegalArgumentException("Niepoprawny format numeru telefonu.");
    }
}
