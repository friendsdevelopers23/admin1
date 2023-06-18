package com.calsoft.pos.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import lombok.Data;

@Data
public class Customer {
	public final static int FIRSTNAME_ATTRIBUTE_ID = 5;
	public final static int LASTNAME_ATTRIBUTE_ID = 7;
	public final static int EMAIL_ATTRIBUTE_ID = 9;
	public final static int PASSWORD_ATTRIBUTE_ID = 12;
	public final static int GENDER_ATTRIBUTE_ID = 18;
	public final static int DOB_ATTRIBUTE_ID = 11;
	public final static int FID_ATTRIBUTE_ID = 194;
	public final static int FTOKEN_ATTRIBUTE_ID = 195;
	public final static int GID_ATTRIBUTE_ID = 192;
	public final static int GTOKEN_ATTRIBUTE_ID = 193;
	public final static int TELEPHONE_ATTRIBUTE_ID = 385;
	private int entityId = 0;
	private String email;
	private String firstName;	
	private String lastName;
	private String password;
	private String gender;
	private String socialGID;
	private String socicalGToken;
	private String socialFID;
	private String socialFToken;
	private String dob;
	private String createdDate;
	private String updatedDate;
	public String telephone;

	private String type;

	@Transient
	private String opreationType;

	List<Address> address = new ArrayList<Address>();

}
