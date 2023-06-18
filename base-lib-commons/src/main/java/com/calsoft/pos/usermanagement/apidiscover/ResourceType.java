package com.calsoft.pos.usermanagement.apidiscover;

import java.util.Arrays;

public enum ResourceType {
    PAGE("page", "PAGE"),
    API("api", "API"),
    OBJECT("object", "OBJECT"),
    ATTRIBUTE("attribute", "ATTRIBUTE");

    private String code;
    private String label;

    ResourceType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return this.label;
    }

    public static ResourceType fromCode(String code) {
        return Arrays.stream(ResourceType.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid service type code"));
    }
}
