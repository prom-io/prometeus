package io.realm9.prometheus.dds.rest.response;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@Type("file")
public class FileResponse {
    @Id
    private UUID id;
    private String name;
    private Map<String, String> additional;
    private int duration;
    private BigDecimal cost;

    @Links
    private com.github.jasminb.jsonapi.Links links;
}
