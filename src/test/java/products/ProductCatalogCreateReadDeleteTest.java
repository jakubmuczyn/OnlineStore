package products;

import database.ProductDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ProductCatalogCreateReadDeleteTest {
    ProductDao productDao = new ProductDao();
    ProductCatalog productCatalog;
    Product testProduct = null;

    @BeforeEach
    void reintializeProductCalatalog() { productCatalog = new ProductCatalog();}
    Product createTestProduct(){
        int vacantId = productDao.getVacantId();
        return productCatalog.createProduct("Test", "Create product from Catalogue", "Tests", 0D, 0);
    }
    @Test
    void createProduct() {
        Product product = createTestProduct();

        assertTrue(productCatalog.getProducts().contains(product));
        assertFalse(productDao.get(product.getId()).isEmpty());
        productCatalog.removeProduct(product);
    }

    @Test
    void removeProduct() {
        int productAmmountBefore = productDao.getAmmountOfProducts();
        Product product = createTestProduct();
        int productAmmountAfterCreate = productDao.getAmmountOfProducts();
        productCatalog.removeProduct(product);
        int productAmmountAfterDelete = productDao.getAmmountOfProducts();

        assertEquals(productAmmountBefore, productAmmountAfterDelete);
        assertEquals(productAmmountAfterCreate - 1, productAmmountAfterDelete);
    }

    @Test
    void getProducts() { assertFalse(productCatalog.getProducts().isEmpty()); }

    @Test
    void updateProductName() {
        Product testProduct = createTestProduct();
        productDao.save(testProduct);
        String initialName = testProduct.getName();

        String newName = "New test product name";
        productCatalog.updateProductName(testProduct, newName);

        assertNotEquals(initialName, testProduct.getName());
        assertEquals(newName, testProduct.getName());
        assertEquals(newName, productDao.get(testProduct.getId()).get().getName());

        productDao.delete(testProduct);
    }

    @Test
    void updateProductDescription() {
        Product testProduct = createTestProduct();
        productDao.save(testProduct);
        String initialDescription = testProduct.getDescription();

        String newName = "New test product description";
        productCatalog.updateProductDescription(testProduct, newName);

        assertNotEquals(initialDescription, testProduct.getDescription());
        assertEquals(newName, testProduct.getDescription());
        assertEquals(newName, productDao.get(testProduct.getId()).get().getDescription());

        productDao.delete(testProduct);
    }

    @Test
    void updateProductCategory() {
        Product testProduct = createTestProduct();
        productDao.save(testProduct);
        String initialCategory = testProduct.getCategory();

        String newName = "New test product description";
        productCatalog.updateProductCategory(testProduct, newName);

        assertNotEquals(initialCategory, testProduct.getCategory());
        assertEquals(newName, testProduct.getCategory());
        assertEquals(newName, productDao.get(testProduct.getId()).get().getCategory());

        productDao.delete(testProduct);
    }

    @Test
    void updateProductPrice() {
        Product testProduct = createTestProduct();
        productDao.save(testProduct);
        Double initialPrice = testProduct.getPrice();

        Double newPrice = 9.7D;
        productCatalog.updateProductPrice(testProduct, newPrice);

        assertNotEquals(initialPrice, testProduct.getPrice());
        assertEquals(newPrice, testProduct.getPrice());
        assertEquals(newPrice, productDao.get(testProduct.getId()).get().getPrice());

        productDao.delete(testProduct);
    }

    @Test
    void updateProductQuantity() {
        Product testProduct = createTestProduct();
        productDao.save(testProduct);
        int intialQuantity = testProduct.getQuantityInStock();

        int newQuantity = 475;
        productCatalog.updateProductQuantity(testProduct, newQuantity);

        assertNotEquals(intialQuantity, testProduct.getQuantityInStock());
        assertEquals(newQuantity, testProduct.getQuantityInStock());
        assertEquals(newQuantity, productDao.get(testProduct.getId()).get().getQuantityInStock());

        productDao.delete(testProduct);
    }
}