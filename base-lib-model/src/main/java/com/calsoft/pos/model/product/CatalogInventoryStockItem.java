package com.calsoft.pos.model.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cataloginventory_stock_item")
@Getter
@Setter
public class CatalogInventoryStockItem implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long itemId;

	@Column(name = "stock_id")
	private int stockId;

	@Column(name = "qty")
	private double qty;

	@Column(name = "min_qty")
	private double minQty;

	@Column(name = "max_sale_qty")
	private double maxQty;

	@Column(name = "use_config_min_qty")
	private int useConfigMinQty;

	@Column(name = "is_qty_decimal")
	private int isQtyDecimal;

	@Column(name = "backorders")
	private int backorders=0;

	@Column(name = "use_config_backorders")
	private int useConfigBackorders;

	@Column(name = "min_sale_qty")
	private double minSaleQty;

	@Column(name = "use_config_max_sale_qty")
	private int useConfigMaxSaleQty;

	@Column(name = "is_in_stock")
	private double isInStock;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "low_stock_date")
	private Date lowStockDate;

	@Column(name = "notify_stock_qty")
	private Double notifyStockQty;

	@Column(name = "use_config_notify_stock_qty")
	private int useConfigNotifyStockQty;

	@Column(name = "manage_stock")
	private int manageStock;

	@Column(name = "use_config_manage_stock")
	private int useConfigManageStock;

	@Column(name = "stock_status_changed_auto")
	private int stockStatusChangedAuto;

	@Column(name = "use_config_qty_increments")
	private int useConfigQtyIncrements;

	@Column(name = "qty_increments")
	private double qtyIncrements;

	@Column(name = "use_config_enable_qty_inc")
	private int useConfigEnableQtyInc;

	@Column(name = "enable_qty_increments")
	private int enableQtyIncrements;

	@Column(name = "is_decimal_divided")
	private int isDecimalDivided;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	@Column(name = "product_id",insertable=false,updatable=false)
	private long productId;

}
