package com.calsoft.security.config;


import org.springframework.core.env.Environment;

import com.calsoft.pos.usermanagement.authentication.utils.ApplicationContextProvider;


public  class EnvProperties {

	private static	Environment prop;

	public static String getConfigValue(String key) {
		String value = null;
		if (prop == null && ApplicationContextProvider.getApplicationContext() != null) {
			prop = ApplicationContextProvider.getApplicationContext().getBean(Environment.class);
		}
		if (prop != null && prop.getProperty(key) != null && ApplicationContextProvider.getApplicationContext() != null) {
			value = prop.getProperty(key).trim();
		}
		if (value == null && System.getenv(key) !=null) {
			value = System.getenv(key).trim();
		}
		return value;
	}

}