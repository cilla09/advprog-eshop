package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CODPaymentServiceImpl implements CODPaymentService {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Override
    public Payment processPayment(Order order, Map<String, String> paymentData) {
        // Create initial payment with PENDING status
        Payment payment = paymentService.addPayment(
                order,
                PaymentMethod.COD.getValue(),
                paymentData
        );

        // Validate payment data
        if (isValidPaymentData(paymentData)) {
            // Update payment status to SUCCESS
            return paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        } else {
            // Update payment status to REJECTED
            return paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        }
    }

    private boolean isValidPaymentData(Map<String, String> paymentData) {
        return paymentData != null &&
                paymentData.containsKey("address") &&
                paymentData.get("address") != null &&
                !paymentData.get("address").isEmpty() &&
                paymentData.containsKey("deliveryFee") &&
                paymentData.get("deliveryFee") != null &&
                !paymentData.get("deliveryFee").isEmpty();
    }
}