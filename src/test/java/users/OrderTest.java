package users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import payments.Payment;
import products.Product;
import orders.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static orders.OrderStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;
    private List<Product> orderProducts;
    @BeforeEach
    void generateOrder() {
        orderProducts = new ArrayList<>();
        orderProducts.add(new Product("Produkt 1", "Opis", "Kategoria", 50.0, 10));
        orderProducts.add(new Product("Produkt 2", "Opis", "Kategoria", 100.0, 5));
        order = new Order(orderProducts);
    }
    @Test
    void getOrderIdTest () {
        assertEquals(0,order.getOrderId());
    }

    @Test
    void setOrderIdTest () {
        order.setOrderId(5);
        assertEquals(5, order.getOrderId());
    }
    @Test
    void getOrderDateTest () {
        Date expectedDate = new Date();
        Date actualDate = order.getOrderDate();
        long timeDifference = Math.abs(expectedDate.getTime() - actualDate.getTime());
        assertTrue(timeDifference < 1000);
    }
    @Test
    void setOrderDateTest () {
        Date expectedDate = new Date(120, 8, 30, 12, 55, 0);
        order.setOrderDate(new Date(120,8,30,12,55,0));
        assertEquals(expectedDate, order.getOrderDate());
    }
    @Test
    void getOrderProductsTest () {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("Produkt 1", "Opis", "Kategoria", 50.0, 10));
        expectedProducts.add(new Product("Produkt 2", "Opis", "Kategoria", 100.0, 5));
    //TODO
        assertEquals(expectedProducts, order.getOrderProducts());
    }
    @Test
    void setOrderProductsTest () {
        List<Product> newProductsList = new ArrayList<>();
        newProductsList.add(new Product("Nowy Produkt 1", "Nowy Opis", "Nowa Kategoria", 100.0, 20));
        newProductsList.add(new Product("Nowy Produkt 2", "Nowy Opis 2", "Nowa Kategoria 2", 200.0, 10));

        order.setOrderProducts(newProductsList);
        assertEquals(newProductsList, order.getOrderProducts());
    }
    @Test
    void getTotalPriceTest () {
        assertEquals(150.0, order.getTotalPrice());
    }
    @Test
    void setTotalPriceTest () {
        order.setTotalPrice(200.0);
        assertEquals(200.0, order.getTotalPrice());
    }
    @Test
    void getOrderStatusTest () {
        assertEquals(ROZPOCZETE, order.getOrderStatus());
    }
    @Test
    void setOrderStatusTest () {
        order.setOrderStatus(OPŁACONE);
        assertEquals(OPŁACONE, order.getOrderStatus());
    }
    @Test
    void calculateTotalPriceTest () {
        double totalPrice = order.calculateTotalPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

}
