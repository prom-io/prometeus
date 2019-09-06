package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FileUploadAttributes {
    public static final int MONTH_30_DAYS = 60 * 60 * 24 * 30;

    private String name;
    private String data;
    private int duration = MONTH_30_DAYS;
    private Map<String, String> additional;
}
