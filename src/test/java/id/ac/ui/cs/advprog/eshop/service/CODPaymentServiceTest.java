package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CODPaymentServiceTest {

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private CODPaymentServiceImpl codPaymentService;

    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(1);
        products.add(product);

        order = new Order("order-123", products, System.currentTimeMillis(), "user1");

        paymentData = new HashMap<>();
    }

    @Test
    void testProcessPaymentWithValidData() {
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "10000");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.COD.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = codPaymentService.processPayment(order, paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentService).setStatus(payment, PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testProcessPaymentWithMissingAddress() {
        paymentData.put("deliveryFee", "10000");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.COD.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = codPaymentService.processPayment(order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testProcessPaymentWithMissingDeliveryFee() {
        paymentData.put("address", "123 Main St");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.COD.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = codPaymentService.processPayment(order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testProcessPaymentWithEmptyPaymentData() {
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.COD.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = codPaymentService.processPayment(order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }
}
