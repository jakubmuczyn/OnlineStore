package database;

import org.junit.jupiter.api.Test;
import products.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    ProductDao productDriver = new ProductDao();

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
        ProductDao productDao = new ProductDao();
        Product testProduct = createTestProduct(productDao);
        productDao.save(testProduct);

        Optional<Product> retrievedProduct = productDao.get(testProduct.getId());
        assertFalse(retrievedProduct.isEmpty());
        assertEquals(retrievedProduct.get(), testProduct);
        // AskBoss
        productDao.delete(retrievedProduct.get());
    }

    private Product createTestProduct(ProductDao productDao) {
        int freeId = productDao.getVacantId();
        return new Product(freeId, "Test name", "Test description", "Test category", 2.0D, 20);
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
        Product testProduct = createTestProduct(productDao);
        productDao.save(testProduct);
        int ammountOfProductsAfterAdd = productDao.getAmmountOfProducts();

        Optional<Product> retrievedProduct = productDao.get(testProduct.getId());
        productDao.delete(retrievedProduct.get());
        int finalAmmountOfProducts = productDao.getAmmountOfProducts();

        assertTrue(initialAmmountOfProducts < ammountOfProductsAfterAdd);
        assertEquals(initialAmmountOfProducts, finalAmmountOfProducts);
    }

    @Test
    void updateString() {
        ProductDao productDao = new ProductDao();
        Product testProduct = createTestProduct(productDao);
        productDao.save(testProduct);

        String initialName = testProduct.getName();
        productDao.update(testProduct, "name", "nazwa po zmianie");
        Product productAfterChange = productDao.get(testProduct.getId()).get();
        assertNotEquals(initialName, productAfterChange.getName());
        assertEquals("nazwa po zmianie", productAfterChange.getName());

        productDao.delete(productAfterChange);
    }

    @Test
    void updateDouble() {
        ProductDao productDao = new ProductDao();
        Product testProduct = createTestProduct(productDao);
        productDao.save(testProduct);

        Double initialPrice = testProduct.getPrice();
        productDao.update(testProduct, "price", 0.5D);
        Product productAfterChange = productDao.get(testProduct.getId()).get();
        assertNotEquals(initialPrice, productAfterChange.getPrice());
        assertEquals(0.5D, productAfterChange.getPrice() );

        productDao.delete(productAfterChange);
    }

    @Test
    void updateInt() {
        ProductDao productDao = new ProductDao();
        Product testProduct = createTestProduct(productDao);
        productDao.save(testProduct);

        int initialQuantity = testProduct.getQuantityInStock();
        productDao.update(testProduct, "stock", 27);
        Product productAfterChange = productDao.get(testProduct.getId()).get();
        assertNotEquals(initialQuantity, productAfterChange.getQuantityInStock());
        assertEquals(27, productAfterChange.getQuantityInStock() );

        productDao.delete(productAfterChange);
    }
    }