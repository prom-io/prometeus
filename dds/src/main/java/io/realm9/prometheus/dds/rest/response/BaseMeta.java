package io.realm9.prometheus.dds.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMeta {
    private String myAttribute;

    public String getMyAttribute() {
        return myAttribute;
    }

    public void setMyAttribute(String value) {
        this.myAttribute = value;
    }
}
