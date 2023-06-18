package com.calsoft.pos.model.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "core_store_group")
@Data
public class CoreStoreGroup implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
    private long groupId;
    
    @Column(name = "website_id")
    private long websiteId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "root_category_id")
    private long rootCategoryId;
    
    @Column(name = "default_store_id")
    private long defaultStoreId;
    
      @OneToMany(cascade=CascadeType.ALL)
	  @JoinColumn(name="website_id")
	  private List<CoreWebsite> coreWebsite = new ArrayList<CoreWebsite>();



    

}
