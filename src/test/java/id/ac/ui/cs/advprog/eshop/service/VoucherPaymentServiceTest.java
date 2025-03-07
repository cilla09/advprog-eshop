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
class VoucherPaymentServiceTest {

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private VoucherPaymentServiceImpl voucherPaymentService;

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
    void testProcessPaymentWithValidVoucher() {
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.SUCCESS.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = voucherPaymentService.processPayment(order, paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentService).addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        verify(paymentService).setStatus(payment, PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testProcessPaymentWithInvalidVoucherLength() {
        paymentData.put("voucherCode", "ESHOP123456");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = voucherPaymentService.processPayment(order, paymentData);

        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testProcessPaymentWithInvalidVoucherPrefix() {
        paymentData.put("voucherCode", "ESHPX12345678ABC");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = voucherPaymentService.processPayment(order, paymentData);

        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testProcessPaymentWithInvalidVoucherNumericCount() {
        paymentData.put("voucherCode", "ESHOP123ABCDEFGHI");

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.PENDING.getValue())
                .paymentData(paymentData)
                .build();

        when(paymentService.addPayment(any(), anyString(), anyMap())).thenReturn(payment);
        when(paymentService.setStatus(any(), anyString())).thenReturn(payment);

        Payment result = voucherPaymentService.processPayment(order, paymentData);

        verify(paymentService).setStatus(payment, PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testIsVoucherValid() {
        assertTrue(voucherPaymentService.isVoucherValid("ESHOP12345678ABC"));
        assertFalse(voucherPaymentService.isVoucherValid("SHOP123456789ABC")); // Incorrect prefix
        assertFalse(voucherPaymentService.isVoucherValid("ESHOP123456ABC")); // Too short
        assertFalse(voucherPaymentService.isVoucherValid("ESHOP123ABCDEFGHI")); // Not enough numbers
    }
}