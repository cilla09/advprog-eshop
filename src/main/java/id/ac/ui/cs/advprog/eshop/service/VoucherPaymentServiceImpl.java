package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoucherPaymentServiceImpl implements VoucherPaymentService {

    @Autowired
    private PaymentService paymentService;

    public Payment processPayment(Order order, Map<String, String> paymentData) {
        Payment payment = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);

        String voucherCode = paymentData.get("voucherCode");

        if (isVoucherValid(voucherCode)) {
            payment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        } else {
            payment = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        }

        return payment;
    }

    public boolean isVoucherValid(String voucherCode) {
        // Rule 1: Voucher code must be 16 characters long
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }

        // Rule 2: Voucher code must start with "ESHOP"
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        // Rule 3: Voucher code must contain 8 numerical characters
        int numCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }

        return numCount == 8;
    }
}