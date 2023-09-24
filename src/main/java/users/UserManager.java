package users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserManager {

    private static List<User> users = new ArrayList<>();
    private static int lastUserID = 0;


    public static User createUser(String username, String password, String email, String phoneNumber) {
        User user = new User(lastUserID, username, password, email, phoneNumber);
        users.add(user);
        lastUserID++;
        return user;
    }

    public static void addShippingAddress(String firstName, String lastName, String streetName, String unitNumber, String apartmentNumber, String zipCode, String city, String country, int userId){
        User user = getUserById(userId);
        ShippingAddress shipment = new ShippingAddress(firstName, lastName, streetName, unitNumber, apartmentNumber, zipCode, city, country);
        user.addShippingAddress(shipment);
    }

    public static void removeShippingAddress(int userId, int shippingAddressId) {
        User user = getUserById(userId);
        user.removeShippingAddress(shippingAddressId);
    }

    public static User getUserById(int userId) {
        ArrayList<User> user = (ArrayList<User>) users.stream().filter((u) -> u.getUserID() == userId).collect(Collectors.toList());
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
