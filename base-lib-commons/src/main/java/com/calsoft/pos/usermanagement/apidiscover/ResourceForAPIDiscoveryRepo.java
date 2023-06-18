package com.calsoft.pos.usermanagement.apidiscover;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.usermanagement.apidiscover.ResourceForAPIDiscovery;

@Repository
public interface ResourceForAPIDiscoveryRepo extends JpaRepository<ResourceForAPIDiscovery, Long> {

	ResourceForAPIDiscovery findByModuleAndPathAndMethodAndResourceType(String module, String apiPath, String methodType, ResourceType api);
    
    List<ResourceForAPIDiscovery> findByModule(String module);
    
}
