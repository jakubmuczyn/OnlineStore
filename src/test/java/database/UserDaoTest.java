package database;

import org.junit.jupiter.api.*;
import users.User;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao = new UserDao();
    User testUser;

    @BeforeEach
    void createTestUser() {
        testUser = new User(userDao.getVacantId(), "JanKowalski12", "Panko12!", "janek@gmail.com", "600432312");
    }

    @AfterEach
    void tearDown() {
        if (testUser != null) testUser = null;
    }

    @Test
    void get() {
        Optional<User> userOptional = userDao.get(0);
        assertFalse(userOptional.isEmpty());
    }

    @Test
    void save() {
        userDao.save(testUser);
        Optional<User> userFromDatabaseOptional = userDao.get(testUser);
        if (userFromDatabaseOptional.isPresent()) assertTrue(testUser.isIdenticalTo(userFromDatabaseOptional.get()));
        else fail("User couldn't be found after saving to database.");
    }

    @Test
    void getVacantId() {
        assertTrue(userDao.getVacantId() >= 0, "Max vacant id is smaller than 0.");
    }

    @Test
    void delete() {
        userDao.save(testUser);
        assertTrue(userDao.get(testUser).isPresent(), "User hasn't been properly added.");
        userDao.delete(testUser);
        userDao.get(testUser);
        assertFalse(userDao.get(testUser).isPresent(), "User is still present after deletion attempt.");
    }

    @Test
    void update() {
        String originalEmail = testUser.getEmail();
        userDao.save(testUser);
        userDao.update(testUser, UserDao.ColumnName.EMAIL.toString(), "email_po_zmianie@gmail.com");
        User testUserFromDatabase = userDao.get(testUser).get();
        assertNotEquals(originalEmail, testUserFromDatabase.getEmail(), "Email didn't change from original.");
        userDao.update(testUser, UserDao.ColumnName.EMAIL.toString(), originalEmail);
        testUserFromDatabase = userDao.get(testUser).get();
        assertEquals(originalEmail, testUserFromDatabase.getEmail(), "Email didn't properly restore after testing.");
        userDao.delete(testUser);
    }

    @Test
    void testColumnNames() {
        UserDao.ColumnName[] columnNames = UserDao.ColumnName.values();
        String[] columnNamesString = new String[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNamesString[i] = columnNames[i].toString();
        }
        String joinedColumnNames = String.join(",", List.of(columnNamesString));

        String sql = "SELECT %s FROM Users LIMIT 1".formatted(joinedColumnNames);

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            assertTrue(rs.next());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}