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
import com.calsoft.pos.model.Settings;
import com.calsoft.pos.repository.SettingsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SettingsService {

	Logger logger = LoggerFactory.getLogger(SettingsService.class);
	@Autowired
	SettingsRepository settingsRepository;

	public Page<Settings> fetchAllSettings(Pageable pageable, String tenantId) {

		return settingsRepository.findByTenantId(pageable, tenantId);

	}

	public Settings findBySettings(String settingsId, String tenantId) {
		return settingsRepository.findBySettingIdAndTenantId(Long.valueOf(settingsId), tenantId);
	}

	public ResponseWrapper saveSettings(Settings settings, String tenantId) {

		try {

			settings.setTenantId(tenantId);
			settingsRepository.save(settings);

			return new ResponseWrapper(settings, ResponseCodes02.ADDSETTINGS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDSETTINGS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(settings, ResponseCodes02.ADDSETTINGS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDSETTINGS_REC_FAILURE.getDescription());
		}
	}

	public Page<Settings> searchSettings(Specification<Settings> specs, Pageable pageable) {

		return settingsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Settings> fetchAllSettingsList(String tenantId) {
		return settingsRepository.findByTenantId(tenantId);
	}
}
