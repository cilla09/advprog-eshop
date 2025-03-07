package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentData = new HashMap<>();
        paymentData.put("testKey", "testValue");
        payment = Payment.builder()
                .id("payment-123")
                .orderId("order-123")
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.SUCCESS.getValue())
                .paymentData(paymentData)
                .build();
    }

    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getOrderId(), savedPayment.getOrderId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());
    }

    @Test
    void testFindById() {
        paymentRepository.save(payment);
        Payment foundPayment = paymentRepository.findById("payment-123");

        assertNotNull(foundPayment);
        assertEquals(payment.getId(), foundPayment.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Payment foundPayment = paymentRepository.findById("non-existent-id");

        assertNull(foundPayment);
    }

    @Test
    void testUpdateExistingPayment() {
        paymentRepository.save(payment);

        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("newKey", "newValue");

        Payment updatedPayment = Payment.builder()
                .id("payment-123")
                .orderId("order-123")
                .method(PaymentMethod.VOUCHER.getValue())
                .status(PaymentStatus.REJECTED.getValue())
                .paymentData(updatedData)
                .build();

        Payment result = paymentRepository.save(updatedPayment);
        Payment foundPayment = paymentRepository.findById("payment-123");

        assertEquals(PaymentStatus.REJECTED.getValue(), foundPayment.getStatus());
        assertEquals(updatedData, foundPayment.getPaymentData());
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment);

        Payment anotherPayment = Payment.builder()
                .id("payment-456")
                .orderId("order-456")
                .method(PaymentMethod.COD.getValue())
                .status(PaymentStatus.SUCCESS.getValue())
                .paymentData(new HashMap<>())
                .build();

        paymentRepository.save(anotherPayment);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
    }
}