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
import com.calsoft.pos.model.ShopSettings;
import com.calsoft.pos.service.ShopSettingsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class ShopSettingsController {
	@Autowired
	ShopSettingsService shopSettingsService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all ShopSettings page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/shopsettings")
	public Page<ShopSettings> fetchAllShopSettings(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return shopSettingsService.fetchAllShopSettings(pageable, tenantId);
	}

	/***
	 * this method using fetch by single ShopSettings
	 * 
	 * @param ShopSettingsId
	 * @return
	 */

	@GetMapping("/shopsettings/{id}")
	public ShopSettings findShopSettingsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return shopSettingsService.findByShopSettings(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save ShopSettings
	 * 
	 * 
	 * @param Shop_Settings
	 * @return
	 * @throws Parsekitchen_Master
	 */
	@PostMapping("/shopsettings")
	public ResponseWrapper saveShopSettings(@RequestBody final ShopSettings ShopSettings,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return shopSettingsService.saveShopSettings(ShopSettings, tenantId);
	}

	/***
	 * this method using ShopSettings
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */

	@GetMapping("/shopsettings/filter")
	public Page<ShopSettings> searchShopSettings(@SearchSpec Specification<ShopSettings> shopSettings,
			Pageable pageable) {
		return shopSettingsService.searchShopSettings(shopSettings, pageable);
	}

	/***
	 * this method using fetch all ShopSettings
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/shopsettings/dropdown")
	public List<ShopSettings> fetchAllShopSettingsList(@RequestHeader("x-tenant") String tenantId) {
		return shopSettingsService.fetchAllShopSettingsList(tenantId);
	}
}
