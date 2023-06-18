package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "catalogsearch_query")
@Data
public class CatalogsearchQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "query_id")
	private int queryId;

	@Column(name = "query_text")
	private String queryText;

	@Column(name = "num_results")
	private int numResults;

	@Column(name = "popularity")
	private int popularity;

	@Column(name = "redirect")
	private String redirect;

	@Column(name = "synonym_for")
	private String synonymFor;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "display_in_terms")
	private int displayInTerms;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "is_processed")
	private int isProcessed;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Transient
	private String operationType;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
