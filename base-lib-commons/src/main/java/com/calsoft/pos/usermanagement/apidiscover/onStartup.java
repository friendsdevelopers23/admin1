package com.calsoft.pos.usermanagement.apidiscover;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class onStartup {

	@Autowired
	APIDiscoverDataCasting apiDiscoverDataCasting;

	@PostConstruct
	public void onStartUp() {
		apiDiscoverDataCasting.writeAPIDiscoveredTODB();
	}

}
