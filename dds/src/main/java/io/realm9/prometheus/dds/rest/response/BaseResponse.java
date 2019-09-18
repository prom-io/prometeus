package io.realm9.prometheus.dds.rest.response;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Meta;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
abstract class BaseResponse {
    @Id
    private UUID id;

    @Meta
    private BaseMeta meta;

}
