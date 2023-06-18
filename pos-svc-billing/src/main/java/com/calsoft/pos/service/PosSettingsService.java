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
import com.calsoft.pos.model.PosSettings;
import com.calsoft.pos.repository.PosSettingsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PosSettingsService {
	Logger logger = LoggerFactory.getLogger(PosSettingsService.class);
	@Autowired
	PosSettingsRepository posSettingsRepository;

	public Page<PosSettings> fetchAllPosSettings(Pageable pageable, String tenantId) {

		return posSettingsRepository.findByTenantId(pageable, tenantId);

	}

	public PosSettings findByPosSettings(String posSettingsId, String tenantId) {
		return posSettingsRepository.findByPosIdAndTenantId(Long.valueOf(posSettingsId), tenantId);
	}

	public ResponseWrapper savePosSettings(PosSettings posSettings, String tenantId) {

		try {

			posSettings.setTenantId(tenantId);
			posSettingsRepository.save(posSettings);

			return new ResponseWrapper(posSettings, ResponseCodes02.ADDPOS_SETTINGS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDPOS_SETTINGS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(posSettings, ResponseCodes02.ADDPOS_SETTINGS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDPOS_SETTINGS_REC_FAILURE.getDescription());
		}
	}

	public Page<PosSettings> searchPosSettings(Specification<PosSettings> specs, Pageable pageable) {

		return posSettingsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<PosSettings> fetchAllPosSettingsList(String tenantId) {
		return posSettingsRepository.findByTenantId(tenantId);
	}
}
