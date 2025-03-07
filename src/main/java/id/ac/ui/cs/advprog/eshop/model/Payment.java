package id.ac.ui.cs.advprog.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
public class Payment {
    private String id;
    private String orderId;
    private String method;
    @Setter
    private String status;
    private Map<String, String> paymentData;
}