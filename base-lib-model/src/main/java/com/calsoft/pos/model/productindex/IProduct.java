package com.calsoft.pos.model.productindex;

public interface IProduct {
	
	 public long getProductId();
	 
	  public String getProductDescription();
	  public String getType();
	  public double getSpecialPrice() ;
	  public double getDiscountPercentage();
	  public double getRegularPricePerUnit();
	  public String getProductName();
	  public String getSku();
  public double getWeight();
  public String getProductImageUrl();
  
}
