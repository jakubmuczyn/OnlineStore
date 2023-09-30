package users;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserManager {
    
    private UserManager() {
        throw new IllegalStateException("Utility class");
    }
    
    private static List<User> users = new ArrayList<>();
    private static int lastUserID = 0;
    
    public static User createUser(String username, String password, String email, String phoneNumber) {
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Niepoprawna nazwa użytkownika.");
        }
        
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Niepoprawne hasło.");
        }
        
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Niepoprawny adres e-mail.");
        }
        
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Niepoprawny numer telefonu.");
        }
        
        User user = new User(lastUserID, username, password, email.toLowerCase(), phoneNumber);
        users.add(user);
        lastUserID++;
        return user;
    }
    
    static boolean isValidUsername(String username) {
        String regex = "^\\w{4,}$";
        return Pattern.matches(regex, username);
    }
    
    static boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,}$";
        return Pattern.matches(regex, password);
    }
    
    static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[a-zA-Z]{2,}";
        return Pattern.matches(regex, email);
    }
    
    static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(?:\\+\\d{2,3})?(?:\\d{9}|\\d{3}-\\d{3}-\\d{3})$";
        return Pattern.matches(regex, phoneNumber);
    }
    
    public static void addShippingAddress(String firstName, String lastName, String streetName, String unitNumber, String apartmentNumber, String zipCode, String city, String country, int userId) {
        User user = getUserById(userId);
        ShippingAddress shipment = new ShippingAddress(firstName, lastName, streetName, unitNumber, apartmentNumber, zipCode, city, country);
        user.addShippingAddress(shipment);
    }
    
    public static void removeShippingAddress(int userId, int shippingAddressId) {
        User user = getUserById(userId);
        user.removeShippingAddress(shippingAddressId);
    }
    
    public static User getUserById(int userId) {
        ArrayList<User> user = (ArrayList<User>) users.stream().filter(u -> u.getUserID() == userId).collect(Collectors.toList());
        return user.get(0);
    }
    
    public static void removeUser(int userID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                users.remove(i);
                break;
            }
        }
    }
    
    public static User getUser(int userID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                return users.get(i);
            }
        }
        return null;
    }
    
    public static List<User> getAllUsers() {
        return users;
    }
}
