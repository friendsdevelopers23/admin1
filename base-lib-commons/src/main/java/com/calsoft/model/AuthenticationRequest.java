package com.calsoft.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRequest implements Serializable {
	private static final long serialVersionUID = -342121324332423L;
	private String username;
	private String password;
	private String accessToken;
	private String source;
	private String userToken;
	private String platForm;
	private String type;
	private String telephone;

}
