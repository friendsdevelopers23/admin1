package com.calsoft.pos.model.customer;

public interface CustomerExt {

    public int getEntityTypeId();

    public void setEntityTypeId(int entityTypeId);

    public int getAttributeId();

    public void setAttributeId(int attributeId);

    public int getEntityId();

    public void setEntityId(int entityId);

    public String getValue();

    public void setValue(String value);

    public int getValueId();

    public void setValueId(int valueId);
}