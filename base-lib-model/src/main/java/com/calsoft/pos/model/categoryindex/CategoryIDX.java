package com.calsoft.pos.model.categoryindex;

import java.util.ArrayList;
import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SolrDocument(collection = "category_idx")
@Data
public class CategoryIDX implements ICategory {

	@Id
	@Indexed(name = "categoryId", type = "long")
	private long categoryId;

	@Indexed(name = "path", type = "string")
	private String path;

	@Indexed(name = "parentId", type = "string")
	private String parentId;

	@Indexed(name = "name", type = "string")
	private String categoryName;

	@Field
	private String description;

	@Indexed(name = "isActive", type = "string")
	private String isActive;

	@Indexed(name = "includeInMenu", type = "string")
	private String includeInMenu;

	@Indexed(name = "type", type = "string")
	private String type;

	@Indexed(name = "megaEnabled", type = "string")
	private String mega;

	@Field
	private String metaDescription;

	@JsonProperty("subcategories")
	private List<CategoryIDX> subCategories = new ArrayList<CategoryIDX>();

	@Indexed(name = "breadCrumpPath", type = "string")
	private String breadCrumpPath;

	@Indexed(name = "childCount", type = "long")
	private long childCount;

	@Indexed(name = "pathValue", type = "string")
	private String pathValue;

	@Indexed(name = "position", type = "long")
	private long position;

	@Indexed(name = "image", type = "string")
	private String image;

	@JsonIgnore
	@JsonIgnoreProperties
	@Indexed(name = "tenantId", type = "string")
	private String tenantId;

	@Indexed(name = "productListType", type = "long")
	private long productListType;

	@Indexed(name = "displayDescription", type = "long")
	private long displayDescription;
	
	
	@Indexed(name = "displayMode", type = "long")
	private long displayMode;
	
	@Indexed(name = "urlPath", type = "string")
	private String urlPath;
	
	@Dynamic
	@Field("*_s")
	private Map<String, String> isCustomValue;

}
