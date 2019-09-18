package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private RequestType type;
    private PaymentAttributes attributes;
}
