package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoucherPaymentServiceImpl implements VoucherPaymentService {

    @Autowired
    private PaymentService paymentService;

    public Payment processPayment(Order order, Map<String, String> paymentData) {return null;}

    public boolean isVoucherValid(String voucherCode) {return false;}
}