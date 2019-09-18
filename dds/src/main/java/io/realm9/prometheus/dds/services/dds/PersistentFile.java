package io.realm9.prometheus.dds.services.dds;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Builder
@Setter
public class PersistentFile implements File {
    private UUID id;
    private String name;
    private Map<String, String> metadata;
    private java.io.File file;
    private int duration;
}
