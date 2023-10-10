package users;

import orders.Order;
import orders.ShoppingCart;
import payments.PaymentMethodInterface;
import validators.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber; // TODO format XXX-XXX-XXX
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();
    private List<Order> orders = new ArrayList<>(); // Historia zamówień użytkownika
    List<PaymentMethodInterface> paymentMethods;
    private ShoppingCart shoppingCart;
    private UserValidator validator = new UserValidator();

    public User(int userID, String username, String password, String email, String phoneNumber ) {
        setUserID(userID);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.paymentMethods = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }

    public List<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return username;
    }

    public void setPassword(String password) {
        validator.validatePassword(password);
        this.password = password;
    }

    public void setUsername(String username){
        validator.validateUsername(username);
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validator.validateEmail(email);
        this.email = email;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        validator.validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public List<PaymentMethodInterface> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodInterface> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getShippingAddressPosition(int shippingAddressId) {
        int pos;
        for (int i = 0; i < shippingAddresses.size(); i++) {
           if (shippingAddresses.get(i).getShippingAddressId() == shippingAddressId)
               return i;
        }
        // TODO take care of this exception
        return -1;
    }

    public void addShippingAddress(ShippingAddress shippingAddress) {
        shippingAddresses.add(shippingAddress);
    }

    public void removeShippingAddress(int shippingAddressId) {
        int shippingAddresPosition = getShippingAddressPosition(shippingAddressId);
        shippingAddresses.remove(shippingAddresPosition);
    }

    public boolean isIdenticalTo(Object o) {
       if (!this.equals(o))
           return false;
       User comparedUser = (User) o;
       return this.getEmail().equals(comparedUser.getEmail()) && this.getPassword().equals(comparedUser.getPassword()) && this.getPhoneNumber().equals(comparedUser.getPhoneNumber());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username);
    }
}
