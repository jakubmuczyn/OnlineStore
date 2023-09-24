package products;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String category;
    private double price;
    private int quantityInStock;

    public Product(String name, String description, String category, double price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
