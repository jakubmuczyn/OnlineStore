package database;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class Database {
    private final static String STRING_URL = "jdbc:sqlite:src/main/resources/database/storedb.db";
    /**
     * Connect to a sample database
     */
    public static Connection connect() {
        // SQLite connection string
        String url = STRING_URL;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static void selectAllFromProducts() {
        String sql = "select * from Products";
        Connection conn = connect();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }

    public static void insertProduct(String name, String description, String category, BigDecimal price, int quantity) {
        String sql =    "INSERT INTO Products (name, description, category, price, stock)";
        sql += "VALUES(?, ?, ?, ?, ?);";

        Connection conn = connect();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            pstmt.setBigDecimal(4, price);
            pstmt.setInt(5, quantity);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Runtime exception: " + e.getMessage());
        }


    }
    public static void main(String[] args) {
        selectAllFromProducts();
        System.out.println("Now inserting...\n");
        String name = "Test z Javy";
        String description = "Description from java";
        String category = "Category from java";
        BigDecimal price = new BigDecimal("1.5");
        int quantity = 10;

        insertProduct(name, description, category, price, quantity);
        selectAllFromProducts();
    }
}