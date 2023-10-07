package products;

import database.ProductDao;

import java.util.List;

public class ProductCatalog {
    private List<Product> products;
    private final ProductDao productDao = new ProductDao();

    public ProductCatalog() {
       fetchAllProductsFromDatabase();
    }
    public void fetchAllProductsFromDatabase() {
        products = productDao.getAll();
    }

    public Product createProduct(String name, String description, String category, Double price, int quantityInStock) {
        int vacantId = productDao.getVacantId();
        Product product = new Product(vacantId, name, description, category, price, quantityInStock);

        productDao.save(product);
        products.add(product);
        return product;
    }

    public void removeProduct(Product product) {
        products.remove(product);
        productDao.delete(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void updateProductName(Product product, String newName) {
        product.setName(newName);
        productDao.update(product, ProductDao.ColumnName.NAME.getColumnName(), newName);
    }

    public void updateProductDescription(Product product, String newDescription) {
        product.setDescription(newDescription);
        productDao.update(product, ProductDao.ColumnName.DESCRIPTION.getColumnName(), newDescription);
    }

    public void updateProductCategory(Product product, String newCategory) {
        product.setCategory(newCategory);
        productDao.update(product, ProductDao.ColumnName.CATEGORY.getColumnName(), newCategory);
    }

    public void updateProductPrice(Product product, Double newPrice) {
        product.setPrice(newPrice);
        productDao.update(product, ProductDao.ColumnName.PRICE.getColumnName(), newPrice);
    }

    public void updateProductQuantity(Product product, int newQuantity) {
        product.setQuantityInStock(newQuantity);
        productDao.update(product, ProductDao.ColumnName.STOCK.getColumnName(), newQuantity);
    }

//     TODO Metody, np. wyszukiwanie produktów
}
