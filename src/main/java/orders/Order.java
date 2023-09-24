package orders;

import products.Product;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Date orderDate;
    private List<Product> orderProducts;
    private double totalPrice;
    private OrderStatus orderStatus;

    public Order(List<Product> orderProducts) {
        this.orderDate = new Date();
        this.orderProducts = orderProducts;
        this.totalPrice = calculateTotalPrice();
        this.orderStatus = OrderStatus.ROZPOCZETE;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        // TODO - Dla każdego elementu listy orderProducts pobierz cenę i dodaj do totalPrice
        return totalPrice;
    }
}
