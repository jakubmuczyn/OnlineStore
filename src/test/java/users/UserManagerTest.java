package users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    
    static int getMaxUserId() {
        return UserManager.getAllUsers().size() - 1;
    }

    static User getAnyUser() {
        UserManager.createUser("bolek69", "Testowehaslo1!", "testowyemail@vp.pl", "888-777-666");
        return UserManager.getAllUsers().get(0);
    }
    
    @Test
    void testValidUsername() {
        User user = getAnyUser();
        assertAll(
                () -> assertDoesNotThrow(() -> user.setUsername("JakubMuczyn1234")),
                () -> assertDoesNotThrow(() -> user.setUsername("JakubMuczyn1234")),
                () -> assertThrows(IllegalArgumentException.class,() -> user.setUsername("Lol")),
                () -> assertThrows(IllegalArgumentException.class,() -> user.setUsername("User@Name"))
        );

    }
    
    @Test
    void testValidPassword() {
//        assertAll(
//                () -> assertTrue(UserManager.isValidPassword("P@ssw0rd")),
//                () -> assertTrue(UserManager.isValidPassword("Secret123!")),
//                () -> assertFalse(UserManager.isValidPassword("simple")),
//                () -> assertFalse(UserManager.isValidPassword("NoDigit!")),
//                () -> assertFalse(UserManager.isValidPassword("NoUpperCase123"))
//        );
    }
    
    @Test
    void testValidEmail() {
//        assertAll(
//                () -> assertTrue(UserManager.isValidEmail("user@example.com")),
//                () -> assertTrue(UserManager.isValidEmail("jan.kowalski123@poczta.onet.pl")),
//                () -> assertFalse(UserManager.isValidEmail("invalid_email")),
//                () -> assertFalse(UserManager.isValidEmail("user@.com")),
//                () -> assertFalse(UserManager.isValidEmail("user@example."))
//        );
    }
    
    @Test
    void testValidPhoneNumber() {
//        assertAll(
//                () -> assertTrue(UserManager.isValidPhoneNumber("123456789")),
//                () -> assertTrue(UserManager.isValidPhoneNumber("123-456-789")),
//                () -> assertTrue(UserManager.isValidPhoneNumber("+48123456789")),
//                () -> assertTrue(UserManager.isValidPhoneNumber("+123123-456-789")),
//                () -> assertFalse(UserManager.isValidPhoneNumber("1234567890")),
//                () -> assertFalse(UserManager.isValidPhoneNumber("1234")),
//                () -> assertFalse(UserManager.isValidPhoneNumber("12-34-56-789")),
//                () -> assertFalse(UserManager.isValidPhoneNumber("+12-34-56-789"))
//        );
    }
    
    @Test
    void addUserTest() {
        User user = UserManager.createUser("Bolek69", "BolekToNieAgent007#", "walesa@solidarnosc.pl", "660-994-873");
        
        // TODO test authenticate user
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getUserName(), "Bolek69"),
                () -> Assertions.assertEquals(user.getEmail(), "walesa@solidarnosc.pl"),
                () -> Assertions.assertEquals(user.getPhoneNumber(), "660-994-873")
        );
    }
    
    @Test
    void addShippingAddressTest() {
        User user = getAnyUser();
        UserManager.addShippingAddress("Lech", "Wałęsa", "Romualda", "14", "4", "88-199", "Gdynia", "Polska", user.getUserID());
        
        ShippingAddress shippingAddress = user.getShippingAddresses().get(getMaxShippingId(user));
        
        Assertions.assertAll(
                () -> Assertions.assertEquals(shippingAddress.getFirstName(), "Lech"),
                () -> Assertions.assertEquals(shippingAddress.getLastName(), "Wałęsa"),
                () -> Assertions.assertEquals(shippingAddress.getStreetName(), "Romualda"),
                () -> Assertions.assertEquals(shippingAddress.getUnitNumber(), "14"),
                () -> Assertions.assertEquals(shippingAddress.getApartmentNumber(), "4"),
                () -> Assertions.assertEquals(shippingAddress.getZipCode(), "88-199"),
                () -> Assertions.assertEquals(shippingAddress.getCity(), "Gdynia"),
                () -> Assertions.assertEquals(shippingAddress.getCountry(), "Polska")
        );
    }
    
    int getMaxShippingId(User user) {
        return user.getShippingAddresses().size() - 1;
    }
    
    @Test
    void removeShippingAddressTest() {
        User user = getAnyUser();
        int maxShippingId = getMaxShippingId(user);
        
        UserManager.removeShippingAddress(user.getUserID(), maxShippingId);
        Assertions.assertEquals(getMaxShippingId(user), maxShippingId - 1);
    }
    
    @Test
    void getUserByIdTest() {
        // TODO
    }
    
    @Test
    void removeUserTest() {
        int maxUserId = getMaxUserId();
        
        User user = getAnyUser();
        if (user == null) throw new AssertionError();
        UserManager.removeUser(user.getUserID());
        Assertions.assertEquals(getMaxUserId(), maxUserId);
    }
    
    @Test
    void getUserTest() {
        User user = getAnyUser();
        Assertions.assertNotNull(user);
    }
}