package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("testKey", "testValue");
    }

    @Test
    void testCreatePayment() {
        payment = new Payment("payment-123", "order-123", "VOUCHER", "SUCCESS", paymentData);

        assertEquals("payment-123", payment.getId());
        assertEquals("order-123", payment.getOrderId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatus() {
        payment = new Payment("payment-123", "order-123", "VOUCHER", "SUCCESS", paymentData);
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }
}