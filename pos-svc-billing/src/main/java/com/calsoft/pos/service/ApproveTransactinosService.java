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
import com.calsoft.pos.model.ApproveTransactinos;
import com.calsoft.pos.repository.ApproveTransactinosRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApproveTransactinosService {
	Logger logger = LoggerFactory.getLogger(ApproveTransactinosService.class);
	@Autowired
	ApproveTransactinosRepository approveTransactinosRepository;

	public Page<ApproveTransactinos> fetchAllApproveTransactinos(Pageable pageable, String tenantId) {

		return approveTransactinosRepository.findByTenantId(pageable, tenantId);

	}

	public ApproveTransactinos findByApproveTransactinos(String approveTransactinosId, String tenantId) {
		return approveTransactinosRepository.findByIdAndTenantId(Long.valueOf(approveTransactinosId), tenantId);
	}

	public ResponseWrapper saveApproveTransactinos(ApproveTransactinos approveTransactinos, String tenantId) {

		try {

			approveTransactinos.setTenantId(tenantId);
			approveTransactinosRepository.save(approveTransactinos);

			return new ResponseWrapper(approveTransactinos,
					ResponseCodes02.ADDAPPROVE_TRANSACTIONS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDAPPROVE_TRANSACTIONS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(approveTransactinos,
					ResponseCodes02.ADDAPPROVE_TRANSACTIONS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDAPPROVE_TRANSACTIONS_REC_FAILURE.getDescription());
		}
	}

	public Page<ApproveTransactinos> searchApproveTransactinos(Specification<ApproveTransactinos> specs,
			Pageable pageable) {

		return approveTransactinosRepository.findAll(Specification.where(specs), pageable);
	}

	public List<ApproveTransactinos> fetchAllApproveTransactinosList(String tenantId) {
		return approveTransactinosRepository.findByTenantId(tenantId);
	}

}
