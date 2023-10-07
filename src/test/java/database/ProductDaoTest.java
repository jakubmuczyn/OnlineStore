package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import products.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    ProductDao productDriver = new ProductDao();
    Product testProduct;

    @AfterEach
    void deleteTestProduct() { if (testProduct != null) productDriver.delete(testProduct); }
    @Test
    void getAny() {
        assertNotNull(productDriver.get(1));
    }

    @Test
    void getVacantId() {
        assertTrue(productDriver.getVacantId() > -1);
    }

    @Test
    void getAll() {
        List<Product> products = productDriver.getAll();
        assertTrue(products.size() >= 2);
    }

    @Test
    void save() {
        Product testProduct = createAndSaveTestProduct();

        Optional<Product> retrievedProduct = productDriver.get(testProduct.getId());
        assertFalse(retrievedProduct.isEmpty());
        assertEquals(retrievedProduct.get(), testProduct);
        productDriver.delete(retrievedProduct.get());
    }

    private Product createTestProduct() {
        int freeId = productDriver.getVacantId();
        return new Product(freeId, "Test name", "Test description", "Test category", 2.0D, 20);
    }

    private Product createAndSaveTestProduct() {
        Product product = createTestProduct();
        productDriver.save(product);
        return product;
    }

    @Test
    void getAmmountOfProducts() {
        int ammountOfProducts = productDriver.getAmmountOfProducts();
        assertTrue(ammountOfProducts >= 0);
    }

    @Test
    void delete() {
        int initialAmmountOfProducts = productDriver.getAmmountOfProducts();
        Product testProduct = createAndSaveTestProduct();
        int ammountOfProductsAfterAdd = productDriver.getAmmountOfProducts();

        Optional<Product> retrievedProduct = productDriver.get(testProduct.getId());
        if (retrievedProduct.isEmpty()) fail();
        productDriver.delete(retrievedProduct.get());
        int finalAmmountOfProducts = productDriver.getAmmountOfProducts();

        assertTrue(initialAmmountOfProducts < ammountOfProductsAfterAdd);
        assertEquals(initialAmmountOfProducts, finalAmmountOfProducts);

        Optional<Product> retrievedProductAfterDelete = productDriver.get(testProduct.getId());
        assertTrue(retrievedProductAfterDelete.isEmpty());
    }

    @Test
    void updateString() {
        Product testProduct = createAndSaveTestProduct();

        String initialName = testProduct.getName();
        productDriver.update(testProduct, "name", "nazwa po zmianie");
        Optional<Product> productOptional = productDriver.get(testProduct.getId());
        if (productOptional.isEmpty()) fail("Product was not added to database.");
        Product productAfterChange = productOptional.get();
        assertNotEquals(initialName, productAfterChange.getName());
        assertEquals("nazwa po zmianie", productAfterChange.getName());
    }

    @Test
    void updateDouble() {
        Product testProduct = createAndSaveTestProduct();

        Double initialPrice = testProduct.getPrice();
        productDriver.update(testProduct, "price", 0.5D);
        Optional<Product> productOptional = productDriver.get(testProduct.getId());
        if (productOptional.isEmpty()) fail("Product was not added to database.");
        Product productAfterChange = productOptional.get();
        assertNotEquals(initialPrice, productAfterChange.getPrice());
        assertEquals(0.5D, productAfterChange.getPrice());
    }

    @Test
    void updateInt() {
        Product testProduct = createAndSaveTestProduct();

        int initialQuantity = testProduct.getQuantityInStock();
        productDriver.update(testProduct, "stock", 27);
        Optional<Product> productOptional = productDriver.get(testProduct.getId());
        if (productOptional.isEmpty()) fail("Product was not added to database.");
        Product productAfterChange = productOptional.get();
        assertNotEquals(initialQuantity, productAfterChange.getQuantityInStock());
        assertEquals(27, productAfterChange.getQuantityInStock());
    }
}