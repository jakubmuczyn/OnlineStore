import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // TODO Product : quantity

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
        else
        // TODO Komunikat "Koszyk jest pusty"
    }

    public void clearShoppingCart(List<Product>) {
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
