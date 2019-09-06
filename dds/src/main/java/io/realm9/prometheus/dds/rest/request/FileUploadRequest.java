package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadRequest {
    private FileUploadType type;
    private FileUploadAttributes attributes;
}
