package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FileExtendAttributes implements FileAttributes {
    private static final int MONTH_30_DAYS = 60 * 60 * 24 * 30;

    private int duration = MONTH_30_DAYS;
    private Map<String, String> additional;
}
