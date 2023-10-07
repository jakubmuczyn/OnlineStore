package products;


import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String description;
    private String category;
    private Double price = null;
    private Integer quantityInStock = null;

    public Product(int id, String name, String description, String category, double price, int quantityInStock) {
        setId(id);
        setName(name);
        setDescription(description);
        setCategory(category);
        setPrice(price);
        setQuantityInStock(quantityInStock);
    }
    public Product(int id, String name, String description, String category) {
        setId(id);
        setName(name);
        setDescription(description);
        setCategory(category);
        setPrice(price);
        setQuantityInStock(quantityInStock);
    }

    private void setId(int id) {this.id = id;}
    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
