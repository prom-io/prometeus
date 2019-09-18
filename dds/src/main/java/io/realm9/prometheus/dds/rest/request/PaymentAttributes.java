package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentAttributes {
    private String file_id;
    private String status;
    private double amount;
}
