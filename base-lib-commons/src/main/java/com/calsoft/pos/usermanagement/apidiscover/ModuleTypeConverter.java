package com.calsoft.pos.usermanagement.apidiscover;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.calsoft.pos.usermanagement.apidiscover.ResourceType;

import java.util.Optional;

@Converter
public class ModuleTypeConverter implements AttributeConverter<ResourceType, String> {

    public String convertToDatabaseColumn(ResourceType value) {

        return Optional.ofNullable(value)
                .map(serviceTypeCode ->value.getCode())
                .orElse(null);
    }

    public ResourceType convertToEntityAttribute(String value) {

        return Optional.ofNullable(value)
                .map(serviceTypeCode ->ResourceType.fromCode(value))
                .orElse(null);
    }
}
