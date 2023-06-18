package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.model.ShopSettings;
import com.calsoft.pos.repository.ShopSettingsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShopSettingsService {
	Logger logger = LoggerFactory.getLogger(ShopSettingsService.class);
	@Autowired
	ShopSettingsRepository shopSettingsRepository;

	public Page<ShopSettings> fetchAllShopSettings(Pageable pageable, String tenantId) {

		return shopSettingsRepository.findByTenantId(pageable, tenantId);

	}

	public ShopSettings findByShopSettings(String shopSettingsId, String tenantId) {
		return shopSettingsRepository.findByShopIdAndTenantId(Long.valueOf(shopSettingsId), tenantId);
	}

	public ResponseWrapper saveShopSettings(ShopSettings shopSettings, String tenantId) {

		try {

			shopSettings.setTenantId(tenantId);
			shopSettingsRepository.save(shopSettings);

			return new ResponseWrapper(shopSettings, ResponseCodes02.ADDSHOPSETTINGS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDSHOPSETTINGS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(shopSettings, ResponseCodes02.ADDSHOPSETTINGS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDSHOPSETTINGS_REC_FAILURE.getDescription());
		}
	}

	public Page<ShopSettings> searchShopSettings(Specification<ShopSettings> specs, Pageable pageable) {

		return shopSettingsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<ShopSettings> fetchAllShopSettingsList(String tenantId) {
		return shopSettingsRepository.findByTenantId(tenantId);
	}

}
