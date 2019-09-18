package io.realm9.prometheus.dds.rest.response;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Type(value = "local_payment", path = "/payments/local")
public class LocalPaymentResponse extends BaseResponse {
    private String from;
    private String to;
    private BigDecimal amount;
    private int size;
}
