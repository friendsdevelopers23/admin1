package com.calsoft.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class LoggedInUserUtlis {
	
	public static String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = (authentication==null || "anonymousUser".equalsIgnoreCase(authentication.getName()) ) ? "Guest" : authentication.getName();
		return name;

	}


}
