package com.calsoft.pos.usermanagement.apidiscover;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.calsoft.pos.config.CacheServer;
import com.calsoft.utils.UserManagementConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiscoverAPI {


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private CacheServer cacheServer;

    public List<APIDiscoverDTO> getAllAPIs() {
        List<APIDiscoverDTO> apiDiscoverDTOS = new ArrayList<>();
        try {
            RequestMappingInfo mapInfo;
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry :  requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
                APIDiscoverDTO apiDiscoverDTO;
                mapInfo = entry.getKey();
                String apiDesc = "";
                if (mapInfo.getName() != null) {
                    apiDesc = mapInfo.getName();
                }
                Iterator methodTypeIter = mapInfo.getMethodsCondition().getMethods().iterator();
                if (methodTypeIter.hasNext()) {
                    HandlerMethod handlerMethod = entry.getValue();
                    String moduleName = handlerMethod.getBean().toString().replace("Controller", "").replace("Api", "").replace("Servlet", "");
                    Map<String, String> apiResources = new HashMap<>();
                    String apiPath = mapInfo.getPatternsCondition().getPatterns().iterator().next();
                    String methodType = methodTypeIter.next().toString();
                    apiResources.put("createdBy", UserManagementConstant.SYSTEM_NAME);
                    cacheServer.set(UserManagementConstant.APIDISCOVER+"::"+methodType+"::"+apiPath, moduleName);
                    apiDiscoverDTO = APIDiscoverDTO.builder().description(apiDesc).apiPath(apiPath).method(methodType).module(moduleName).build();
                    apiDiscoverDTOS.add(apiDiscoverDTO);
                }
            }
        } catch (Exception e) {
            log.error("Exception at:" , e);
        }
        return apiDiscoverDTOS;
    }
}
