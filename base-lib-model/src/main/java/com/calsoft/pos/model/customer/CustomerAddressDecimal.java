package com.calsoft.pos.model.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_address_entity_decimal")
public class CustomerAddressDecimal implements Serializable,CustomerExt  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private int valueId;

    @Column(name = "entity_type_id")
    private int entityTypeId;

    @Column(name = "attribute_id")
    private int attributeId;

    @Column(name = "entity_id")
    private int entityId;

    @Column(name = "value")
    private String value;

	public int getValueId() {
		return valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
	}

	public int getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(int entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    

}
