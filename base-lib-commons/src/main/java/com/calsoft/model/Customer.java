package com.calsoft.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long entityId;

	private String firstName;

	private String email;

	private String lastName;

	private String dob;

	private String isActive;

	private String gender;

	private String password;

	private int quoteItemCount;

	private Map<String, String> additional;

}
