package io.realm9.prometheus.dds.services.stubs;

import io.realm9.prometheus.dds.services.DDSService;
import io.realm9.prometheus.dds.services.dds.File;
import io.realm9.prometheus.dds.services.dds.PersistentFile;
import io.realm9.prometheus.dds.services.dds.TemporaryFile;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DDSServiceStub implements DDSService {
    @Override
    public TemporaryFile storeTemporaryFile(String name, String base64Data, Map<String, String> additionalMetadata) {
        return TemporaryFile.builder()
                .name(name)
                .metadata(additionalMetadata)
                .id(UUID.randomUUID())
                .file(new java.io.File("/tmp/file"))
                .build();
    }

    @Override
    public Optional<File> findFileById(UUID id) {
        return Optional.of(PersistentFile.builder()
                .id(id)
                .file(new java.io.File("/tmp/file"))
                .name("Test File")
                .metadata(Map.of("data", "test data"))
                .build());
    }
}
