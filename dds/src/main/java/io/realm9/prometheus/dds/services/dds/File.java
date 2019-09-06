package io.realm9.prometheus.dds.services.dds;

import java.util.Map;
import java.util.UUID;

public interface File {
    UUID getId();

    String getName();

    Map<String, String> getMetadata();
}
