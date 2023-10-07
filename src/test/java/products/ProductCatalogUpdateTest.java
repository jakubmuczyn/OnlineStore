package products;

import database.ProductDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ProductCatalogUpdateTest {
    ProductDao productDao = new ProductDao();
    ProductCatalog productCatalog = new ProductCatalog();
    Product testProduct;

    @BeforeEach
    void initialize() {
        testProduct = createTestProduct();
        productDao.save(createTestProduct());
    }

    @AfterEach
    void tearDown() {
        if (testProduct != null) productDao.delete(testProduct);
    }

    Product createTestProduct() {
        int vacantId = productDao.getVacantId();
        return productCatalog.createProduct("Test", "Create product from Catalogue", "Tests", 0D, 0);
    }

    @Test
    void updateProductName() {
        String initialName = testProduct.getName();

        String newName = "New test product name";
        productCatalog.updateProductName(testProduct, newName);

        Optional<Product> productOptional = productDao.get(testProduct);
        if (productOptional.isEmpty()) {
            Assertions.fail("Couldn't get element from database after update.");
            return;
        }
        String newNameFromDatabase = productOptional.get().getName();

        StandardUpdateTest.testUpdateAndSetter(initialName, newName, testProduct.getName(), newNameFromDatabase);
    }

    @Test
    void updateProductDescription() {
        String initialDescription = testProduct.getDescription();

        String newDescription = "New test product description";
        productCatalog.updateProductDescription(testProduct, newDescription);

        Optional<Product> productOptional = productDao.get(testProduct);
        if (productOptional.isEmpty()) {
            Assertions.fail("Couldn't get element from database after update.");
            return;
        }
        String newDescrFromDatabase = productOptional.get().getDescription();

        StandardUpdateTest.testUpdateAndSetter(initialDescription, newDescription, testProduct.getDescription(), newDescrFromDatabase);
    }

    @Test
    void updateProductCategory() {
        String initialCategory = testProduct.getCategory();

        String newCategory = "New test product description";
        productCatalog.updateProductCategory(testProduct, newCategory);

        Optional<Product> productOptional = productDao.get(testProduct);
        if (productOptional.isEmpty()) {
            Assertions.fail("Couldn't get element from database after update.");
            return;
        }
        String newCategoryFromDatabase = productOptional.get().getCategory();

        StandardUpdateTest.testUpdateAndSetter(initialCategory, newCategory, testProduct.getCategory(), newCategoryFromDatabase);
    }

    @Test
    void updateProductPrice() {
        Double initialPrice = testProduct.getPrice();
        Double newPrice = 9.7D;
        productCatalog.updateProductPrice(testProduct, newPrice);

        Optional<Product> productOptional = productDao.get(testProduct);
        if (productOptional.isEmpty()) {
            Assertions.fail("Couldn't get element from database after update.");
            return;
        }
        Double newPriceFromDatabase = productOptional.get().getPrice();

        StandardUpdateTest.testUpdateAndSetter(initialPrice, newPrice, testProduct.getPrice(), newPriceFromDatabase);
    }

    @Test
    void updateProductQuantity() {
        int intialQuantity = testProduct.getQuantityInStock();

        int newQuantity = 475;
        productCatalog.updateProductQuantity(testProduct, newQuantity);

        Optional<Product> productOptional = productDao.get(testProduct);
        if (productOptional.isEmpty()) {
            Assertions.fail("Couldn't get element from database after update.");
            return;
        }
        int newQuantityFromDatabase = productOptional.get().getQuantityInStock();
        StandardUpdateTest.testUpdateAndSetter(intialQuantity, newQuantity, testProduct.getQuantityInStock(), newQuantityFromDatabase);
    }
}
