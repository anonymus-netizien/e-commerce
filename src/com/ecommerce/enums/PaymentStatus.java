package com.ecommerce.enums;

public enum PaymentStatus {
    INITIATED, SUCCESS, FAILED, REFUNDED;

    public boolean canRetry() {
        return this == FAILED;
    }
}
