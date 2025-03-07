package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;
import java.util.Map;

public interface VoucherPaymentService {
    public Payment processPayment(Order order, Map<String, String> paymentData);
    public boolean isVoucherValid(String voucherCode);
}