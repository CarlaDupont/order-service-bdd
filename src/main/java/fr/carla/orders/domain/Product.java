package fr.carla.orders.domain;

import java.math.BigDecimal;

public class Product {

    private final String reference;
    private final String name;
    private final BigDecimal unitPrice;
    private final int availableStock;

    public Product(
            String reference,
            String name,
            BigDecimal unitPrice,
            int availableStock
    ) {
        this.reference = reference;
        this.name = name;
        this.unitPrice = unitPrice;
        this.availableStock = availableStock;
    }

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}