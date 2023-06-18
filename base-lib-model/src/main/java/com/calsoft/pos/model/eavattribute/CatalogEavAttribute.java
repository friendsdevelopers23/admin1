package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "catalog_eav_attribute")
@Data
//@Where(clause = "is_visible=1")
public class CatalogEavAttribute implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @Column(name = "attribute_id") private long attributeId;
	 */
	
	@Column(name = "attribute_id",insertable = false,updatable = false) 
	private long attributeId;

	@Column(name = "frontend_input_renderer")
	private String frontendInputRenderer;

	@Column(name = "is_global")
	private long isGlobal;

	@Column(name = "is_visible")
	private long isVisible;

	@Column(name = "is_searchable")
	private long isSearchable;

	@Column(name = "is_filterable")
	private long isFilterable;

	@Column(name = "is_comparable")
	private long isComparable;

	@Column(name = "is_visible_on_front")
	private long isVisibleOnFront;

	@Column(name = "is_html_allowed_on_front")
	private long isHtmlAllowedOnFront;

	@Column(name = "is_used_for_price_rules")
	private long isUsedForPriceRules;

	@Column(name = "is_filterable_in_search")
	private long isFilterableInSearch;

	@Column(name = "used_in_product_listing")
	private long usedInProductListing;

	@Column(name = "used_for_sort_by")
	private long usedForSortBy;

	@Column(name = "is_configurable")
	private long isConfigurable;

	@Column(name = "apply_to")
	private String applyTo;

	@Column(name = "is_visible_in_advanced_search")
	private long isVisibleInAdvancedSearch;

	@Column(name = "position")
	private long position;

	@Column(name = "is_wysiwyg_enabled")
	private long isWysiwygEnabled;

	@Column(name = "is_used_for_promo_rules")
	private long isUsedForPromoRules;

	@Column(name = "solr_search_field_weight")
	private long solrSearchFieldWeight;

	@Column(name = "solr_search_field_boost")
	private String solrSearchFieldBoost;

	@Column(name = "solr_search_field_range")
	private long solrSearchFieldRange;

	@Column(name = "layered_navigation_canonical")
	private long layeredNavigationCanonical;

	@JsonBackReference
	@OneToOne
	@Id
	@JoinColumn(name = "attribute_id")
	private EavAttribute eavAttribute;

	
}
