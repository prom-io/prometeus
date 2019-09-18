package io.realm9.prometheus.dds.rest.request;

public interface FileRequest {
    RequestType getType();
    FileAttributes getAttributes();
}
