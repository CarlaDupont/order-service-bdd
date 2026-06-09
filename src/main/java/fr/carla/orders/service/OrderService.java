package fr.carla.orders.service;

import fr.carla.orders.domain.CustomerProfile;
import fr.carla.orders.domain.Order;
import fr.carla.orders.domain.OrderReceipt;
import fr.carla.orders.domain.Product;
import fr.carla.orders.exception.InsufficientStockException;
import fr.carla.orders.exception.ProductNotFoundException;
import fr.carla.orders.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderService {

    private final ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OrderReceipt placeOrder(
            Order order,
            CustomerProfile customerProfile
    ) {
        Product product = productRepository
                .findByReference(order.getProductReference())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                order.getProductReference()
                        )
                );

        if (order.getQuantity() > product.getAvailableStock()) {
            throw new InsufficientStockException(
                    order.getQuantity(),
                    product.getAvailableStock()
            );
        }

        BigDecimal subtotal = product
                .getUnitPrice()
                .multiply(BigDecimal.valueOf(order.getQuantity()));

        BigDecimal discountAmount = subtotal
                .multiply(customerProfile.getDiscountRate());

        BigDecimal totalAmount = subtotal
                .subtract(discountAmount)
                .setScale(2, RoundingMode.HALF_UP);

        return new OrderReceipt(
                product.getReference(),
                order.getQuantity(),
                totalAmount,
                "Commande acceptée pour " + order.getCustomerEmail()
        );
    }
}