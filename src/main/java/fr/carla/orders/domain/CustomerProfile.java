package fr.carla.orders.domain;

import java.math.BigDecimal;

public enum CustomerProfile {

    STANDARD(new BigDecimal("0.00")),
    PREMIUM(new BigDecimal("0.10")),
    VIP(new BigDecimal("0.20"));

    private final BigDecimal discountRate;

    CustomerProfile(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }
}