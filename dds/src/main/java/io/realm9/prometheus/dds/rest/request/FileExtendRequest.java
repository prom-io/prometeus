package io.realm9.prometheus.dds.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileExtendRequest implements FileRequest {
    private RequestType type;
    private FileExtendAttributes attributes;
}
