package com.calsoft.pos.usermanagement.apidiscover;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calsoft.utils.UserManagementConstant;

import java.util.List;

@Component
public class APIDiscoverDataCasting {

    @Autowired
    DiscoverAPI discoverAPI;

    @Autowired
    ResourceForAPIDiscoveryRepo resourceRepo;

    public void writeAPIDiscoveredTODB() {
        List<APIDiscoverDTO> apiDiscoverDTOS = discoverAPI.getAllAPIs();
        if(apiDiscoverDTOS != null && apiDiscoverDTOS.size() > 0) {
            apiDiscoverDTOS.stream().forEach(apiDiscoverDTO -> {
                ResourceForAPIDiscovery resource = resourceRepo.findByModuleAndPathAndMethodAndResourceType(apiDiscoverDTO.getModule(), apiDiscoverDTO.getApiPath(),
                        apiDiscoverDTO.getMethod(), ResourceType.API);

                if(resource == null) {
                	ResourceForAPIDiscovery _resource = ResourceForAPIDiscovery.builder()
                            .resourceType(ResourceType.API)
                            .createdBy(UserManagementConstant.SYSTEM_NAME)
                            .description(apiDiscoverDTO.getDescription())
                            .method(apiDiscoverDTO.getMethod())
                            .module(apiDiscoverDTO.getModule())
                            .path(apiDiscoverDTO.getApiPath())
                            .build();
                    resourceRepo.save(_resource);
                }
            });
        }
    }
}
