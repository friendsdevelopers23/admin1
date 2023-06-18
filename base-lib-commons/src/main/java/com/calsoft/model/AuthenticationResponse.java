package com.calsoft.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {
    private String username;
    private String userToken;
    private Customer customer;
    private UserProfile userProfile;
	private String accessToken;
	private String type;

}
