package users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserManagerTest {
    @BeforeEach
    void generateFakeUsers() {
        User user = UserManager.createUser("bolek69", "testowehaslo", "testowyemail@vp.pl", "888-777-666");
        UserManager.addShippingAddress("Adam", "Nawałka", "Wolska", "12", "54", "87-100", "Toruń", "Polska", user.getUserID());
    }

    @Test
    void addUserTest() {
        User user = UserManager.createUser("Bolek69", "BolekToNieAgent007", "walesa@solidarnosc.pl", "660-994-873");

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
    }

    static int getMaxUserId() {
        return UserManager.getAllUsers().size() - 1;
    }

    static User getAnyUser() {
        return UserManager.getAllUsers().get(0);
    }

    @Test
    void removeUserTest() {
        int maxUserId = getMaxUserId();

        User user = UserManager.getUser(0);
        assert user != null;
        UserManager.removeUser(user.getUserID());
        Assertions.assertEquals(getMaxUserId(),maxUserId - 1);
    }

    @Test
    void getUserTest() {
        User user = getAnyUser();
        Assertions.assertNotNull(user);
    }
}