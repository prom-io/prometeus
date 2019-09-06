package io.realm9.prometheus.dds.services;

import io.realm9.prometheus.dds.services.dds.File;
import io.realm9.prometheus.dds.services.dds.TemporaryFile;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface DDSService {
    TemporaryFile storeTemporaryFile(String name, String base64Data, Map<String, String> additionalMetadata);

    Optional<File> findFileById(UUID id);
}
