package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    private String id;
    private String orderId;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String orderId, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.orderId = orderId;
        this.status = PaymentStatus.PENDING.getValue();

        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }

        this.setStatus(status);
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }
}