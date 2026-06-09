package fr.carla.orders.domain;

import java.math.BigDecimal;

public class OrderReceipt {

    private final String productReference;
    private final int quantity;
    private final BigDecimal totalAmount;
    private final String confirmationMessage;

    public OrderReceipt(
            String productReference,
            int quantity,
            BigDecimal totalAmount,
            String confirmationMessage
    ) {
        this.productReference = productReference;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.confirmationMessage = confirmationMessage;
    }

    public String getProductReference() {
        return productReference;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }
}