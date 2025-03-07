package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CODPaymentServiceImpl implements CODPaymentService {
    private final PaymentServiceImpl paymentService;

    public CODPaymentServiceImpl(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Payment processPayment(Order order, Map<String, String> paymentData) {return null;}
}