package products;

import database.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> products = new ArrayList<>();
    private ProductDao productDao;

    public void createProduct(int id, String name, String description, String category, Double price, int quantityInStock) {
        productDao = new ProductDao();
        Product product = new Product(id, name, description, category, price, quantityInStock);

        productDao.save(product);
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

//     TODO Metody, np. wyszukiwanie produktów
}
