package io.realm9.prometheus.dds.rest.request;

import java.util.Map;

public interface FileAttributes {
    int getDuration();

    Map<String, String> getAdditional();
}
