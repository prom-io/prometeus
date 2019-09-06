package io.realm9.prometheus.dds.services.dds;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class TemporaryFile implements File {
    private UUID id;
    private String name;
    private Map<String, String> metadata;
}
