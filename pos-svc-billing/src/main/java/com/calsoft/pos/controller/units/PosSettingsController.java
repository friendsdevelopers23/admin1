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
import com.calsoft.pos.model.PosSettings;
import com.calsoft.pos.service.PosSettingsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class PosSettingsController {

	@Autowired
	PosSettingsService posSettingsService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all posSettings page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/possettings")
	public Page<PosSettings> fetchAllPosSettings(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return posSettingsService.fetchAllPosSettings(pageable, tenantId);
	}

	/***
	 * this method using fetch by single posSettings
	 * 
	 * @param brandsId
	 * @return
	 */

	@GetMapping("/possettings/{id}")
	public PosSettings findByPosSettingsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return posSettingsService.findByPosSettings(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save posSettings
	 * 
	 * 
	 * @param possettings
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/possettings")
	public ResponseWrapper savePosSettings(@RequestBody final PosSettings PosSettings,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return posSettingsService.savePosSettings(PosSettings, tenantId);
	}

	/***
	 * this method using posSettings
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/possettings/filter")
	public Page<PosSettings> searchPosSettings(@SearchSpec Specification<PosSettings> possettings, Pageable pageable) {
		return posSettingsService.searchPosSettings(possettings, pageable);
	}

	/***
	 * this method using fetch all posSettings
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/possettings/dropdown")
	public List<PosSettings> fetchAllPosSettingsList(@RequestHeader("x-tenant") String tenantId) {
		return posSettingsService.fetchAllPosSettingsList(tenantId);
	}

}
