package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.Map;

public interface CODPaymentService {
    Payment processPayment(Order order, Map<String, String> paymentData);
}