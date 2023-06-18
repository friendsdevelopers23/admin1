package com.calsoft.pos.controller.units;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.Settings;
import com.calsoft.pos.service.SettingsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class SettingsController {

	@Autowired
	SettingsService settingsService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all settings page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/settings")
	public Page<Settings> fetchAllSettings(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return settingsService.fetchAllSettings(pageable, tenantId);
	}

	/***
	 * this method using fetch by single settings
	 * 
	 * @param brandsId
	 * @return
	 */

	@GetMapping("/settings/{id}")
	public Settings findBySettingsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return settingsService.findBySettings(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save settings
	 * 
	 * 
	 * @param settings
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/settings")
	public ResponseWrapper saveSettings(@RequestBody final Settings Settings,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return settingsService.saveSettings(Settings, tenantId);
	}

	/***
	 * this method using settings
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/settings/filter")
	public Page<Settings> searchSettings(@SearchSpec Specification<Settings> settings, Pageable pageable) {
		return settingsService.searchSettings(settings, pageable);
	}

	/***
	 * this method using fetch all settings
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/settings/dropdown")
	public List<Settings> fetchAllSettingsList(@RequestHeader("x-tenant") String tenantId) {
		return settingsService.fetchAllSettingsList(tenantId);
	}

}
