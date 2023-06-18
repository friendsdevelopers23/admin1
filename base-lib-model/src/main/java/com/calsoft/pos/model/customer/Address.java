package com.calsoft.pos.model.customer;

import lombok.Data;

@Data
public class Address {
	public final static int CITY_ATTRIBUTE_ID = 26;
	public final static int COMPANY_ATTRIBUTE_ID = 24;
	public final static int COUNTRY_ATTRIBUTE_ID = 27;
	public final static int FAX_ATTRIBUTE_ID = 32;
	public final static int FIRSTNAME_ATTRIBUTE_ID = 20;
	public final static int LASTNAME_ATTRIBUTE_ID = 22;
	public final static int POSTALCODE_ATTRIBUTE_ID = 30;
	public final static int REGION_ATTRIBUTE_ID = 28;
	public final static int STREETADDRESS_ATTRIBUTE_ID = 25;
	public final static int TELEPHONE_ATTRIBUTE_ID = 31;
	public final static int REGIONID_ATTRIBUTE_ID = 29;

	private int entityId;
	private String city;
	private String company;
	private String region;
	private String fax;
	private String firstName;
	private String lastName;
	private String postCode;
	private String state;
	private String countryId;
	private String street;
	private String telephone;
	private String regionId;

}
