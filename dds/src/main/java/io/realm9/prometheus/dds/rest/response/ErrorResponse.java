package io.realm9.prometheus.dds.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String status;
    private String title;
    private String detail;
}
