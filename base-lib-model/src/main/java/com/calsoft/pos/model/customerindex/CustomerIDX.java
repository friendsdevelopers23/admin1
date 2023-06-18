package com.calsoft.pos.model.customerindex;

import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SolrDocument(collection = "user_idx")
@Data
public class CustomerIDX implements ICustomer {

	@Id
	@Indexed(name = "entityId", type = "long")
	private long entityId;

	@Indexed(name = "firstName", type = "string")
	private String firstName;

	@Indexed(name = "email", type = "string")
	private String email;

	@Indexed(name = "lastName", type = "string")
	private String lastName;

	@Indexed(name = "dob", type = "string")
	private String dob;

	@Indexed(name = "isActive", type = "string")
	private String isActive;

	@Indexed(name = "gender", type = "string")
	private String gender;

	@Dynamic
	@Field("*_s")
	private Map<String, String> additional;

	@Field
	@JsonIgnore
	@JsonIgnoreProperties
	private String password;

	@NotFound(action = NotFoundAction.IGNORE)
	private int quoteItemCount;

	@JsonIgnore
	@JsonIgnoreProperties
	@Indexed(name = "tenantId", type = "string")
	private String tenantId;

}
