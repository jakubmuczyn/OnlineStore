package database;

import products.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements DaoInterface<Product> {
    public enum ColumnName {
        PRODUCT_ID("productId"),
        NAME("name"),
        DESCRIPTION("description"),
        CATEGORY("category"),
        PRICE("price"),
        STOCK("stock");

        private final String columnName;

        ColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName() {
            return columnName;
        }
    }

    @Override
    public Optional<Product> get(int id) {
        String queryString = "SELECT * FROM Products WHERE productId = ?";
        Product product = null;

        //TODO How to close rs in finally
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(queryString)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String category = rs.getString("category");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("stock");
                    // TODO If we already have product in memory, we should not fetch it again
                    product = new Product(id, name, description, category, price, quantityInStock);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product != null ? Optional.of(product) : Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        String queryString = "SELECT * FROM Products";
        Product product = null;
        List<Product> products = new ArrayList<>();

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryString)) {
            while (rs.next()) {
                int id = rs.getInt("productId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantityInStock = rs.getInt("stock");
                // TODO If we already have product in memory, we should not fetch it again
                // TODO What if we have a bajillion products? Would probably be bad to fetch them all.
                product = new Product(id, name, description, category, price, quantityInStock);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO Products (name, description, category, price, stock, productId)";
        sql += "VALUES(?, ?, ?, ?, ?, ?);";


        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantityInStock());
            pstmt.setInt(6, getVacantId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Runtime exception: " + e.getMessage());
        }
    }

    public int getAmmountOfProducts() {
        String queryString = "SELECT count(productId) from Products";
        int ammountOfProducts = -1;

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryString)) {
            ammountOfProducts = rs.getInt(1);
        } catch (SQLException e) {
        }
        return ammountOfProducts;
    }

    public int getVacantId() {
        String queryString = "SELECT max(productId) FROM Products";
        int maxId = -1;
        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryString)) {

            if (rs.next()) maxId = rs.getInt(1) + 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return maxId;
    }

    @Override
    public <K> void update(Product product, String parameterName, K parameterValue) {
        String sqlString = "UPDATE Products SET ";
        sqlString += parameterName;
        sqlString += " = ? WHERE productId = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            pstmt.setInt(2, product.getId());

            if (parameterValue instanceof String stringParameter) pstmt.setString(1, stringParameter);
            else if (parameterValue instanceof Double doubleParameter) pstmt.setDouble(1, doubleParameter);
            else if (parameterValue instanceof Integer intParameter) pstmt.setInt(1, intParameter);
            else {
                String message = "Did not implement a method for this type of parameter (";
                message += parameterValue.getClass() + ")";
                throw new IllegalArgumentException(message);
            }
            System.out.println(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Product product) {
        int productId = product.getId();
        String sql = "DELETE FROM Products where productId = " + productId;

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
