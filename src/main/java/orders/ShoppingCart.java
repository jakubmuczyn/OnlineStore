package orders;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // TODO products.Product : quantity

    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        if (items.size() > 0)
            return items;
        else {
            // TODO Komunikat "Koszyk jest pusty"
            System.out.println("Koszyk jest pusty");
            return null;
        }

    }

    public void clearShoppingCart() {
        items.clear();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public void applyDiscount() {
        // TODO Zaaplikowanie kodu rabatowego

    }
}
