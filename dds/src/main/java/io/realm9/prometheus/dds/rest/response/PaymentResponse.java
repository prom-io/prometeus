package io.realm9.prometheus.dds.rest.response;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Type(value = "payment", path = "/payments/{id}")
public class PaymentResponse extends BaseResponse {
    private UUID file_id;
    private String status;
    private double amount;
}
