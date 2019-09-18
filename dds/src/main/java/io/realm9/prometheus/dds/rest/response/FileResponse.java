package io.realm9.prometheus.dds.rest.response;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@Type(value = "file", path = "/files/{id}")
public class FileResponse extends BaseResponse {
    private String name;
    private Map<String, String> additional;
    private int duration;
    private BigDecimal price;
}
