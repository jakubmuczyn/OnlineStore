package database;

import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserDao implements DaoInterface<User> {
    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt(1);
        String userName = rs.getString(2);
        String password = rs.getString(3);
        String email = rs.getString(4);
        String phoneNumber = rs.getString(5);

        return new User(userId, userName, password, email, phoneNumber);
    }
    @Override
    public Optional<User> get(int id) {
        String sql = "SELECT userId, userName, password, email, phoneNumber FROM USERS WHERE userId = ?" ;
        Optional<User> userOptional = Optional.empty();

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                   userOptional = Optional.of(createUserFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userOptional;
    }

    @Override
    public Optional<User> get(User user) {
        int userId = user.getUserID();
        return get(userId);
    }

    @Override
    public ArrayList<User> getAll() {
        String sql = "SELECT userId, userName, password, email, phoneNumber FROM USERS" ;
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                users.add(createUserFromResultSet(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO Users(userId, userName, password, email, phoneNumber) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, getVacantId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhoneNumber());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K> void update(User user, String parameterName, K parameterValue) {
        String sql = "UPDATE Users SET %s = ? WHERE userId = ?".formatted(parameterName);
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setInt(2, user.getUserID());

            if (parameterValue instanceof String string)
                pstmt.setString(1, string);
            else {
                String message = "Did not implement behaviour for this type of parameter (";
                message += parameterValue.getClass() + ")";
                throw new IllegalArgumentException(message);
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM Users where userId = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getUserID());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getVacantId() {
        // TODO This formatting thing is actually less intuitive than just using names from database
        String queryString = "Select max(%s) from users".formatted(ColumnName.USERID);
        int maxId = 0;

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryString)) {
            maxId = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return maxId+1;
    }

    public enum ColumnName {
        USERID, USERNAME, PASSWORD, EMAIL, PHONENUMBER
    }


}
