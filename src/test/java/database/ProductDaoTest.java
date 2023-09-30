package database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import products.Product;
import org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    ProductDao productDriver = new ProductDao();

    @Test
    void get() { assertNotNull(productDriver.get(1)); }

    @Test
    void getVacantId() { assertTrue(productDriver.getVacantId() > -1); }

    @Test
    void getAll() {
        List<Product> products = productDriver.getAll();
        assertTrue(products.size() >= 2);
    }

    @Test
    void save() {
        ProductDao productDao = new ProductDao();
        int freeId = productDao.getVacantId();
        Product testProduct = new Product(freeId, "Test of save", "This is a test of save", "No cat", 0, 0);
        productDao.save(testProduct);

        Optional<Product> retrievedProduct = productDao.get(testProduct.getId());
        assertFalse(retrievedProduct.isEmpty());
        assertEquals(retrievedProduct.get(), testProduct);
        productDao.delete(retrievedProduct.get());
    }

    @Test
    void getAmmountOfProducts() {
        ProductDao productDao = new ProductDao();
        int ammountOfProducts = productDao.getAmmountOfProducts();
        assertTrue(ammountOfProducts >= 0);
    }

    @Test
    void delete() {
        ProductDao productDao = new ProductDao();
        int initialAmmountOfProducts = productDao.getAmmountOfProducts();
        int freeId = productDao.getVacantId();
        Product testProduct = new Product(freeId, "Test of save", "This is a test of save", "No cat", 0, 0);
        productDao.save(testProduct);
        int ammountOfProductsAfterAdd = productDao.getAmmountOfProducts();

        Optional<Product> retrievedProduct = productDao.get(testProduct.getId());
        productDao.delete(retrievedProduct.get());
        int finalAmmountOfProducts = productDao.getAmmountOfProducts();

        assertTrue(initialAmmountOfProducts < ammountOfProductsAfterAdd);
        assertTrue(initialAmmountOfProducts == finalAmmountOfProducts);
    }
}