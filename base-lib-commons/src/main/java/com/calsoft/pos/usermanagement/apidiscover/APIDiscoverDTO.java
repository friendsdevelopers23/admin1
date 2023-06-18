package com.calsoft.pos.usermanagement.apidiscover;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIDiscoverDTO {

    private String apiPath;
    private String method;
    private String module;
    private String description;
}
