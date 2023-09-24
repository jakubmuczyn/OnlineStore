package orders;

import orders.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders = new ArrayList<>();

    public void addOrderToHistory(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

//     TODO - Metody: przeglądanie zamówień użytkownika
}
