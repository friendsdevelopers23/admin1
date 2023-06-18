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
import com.calsoft.pos.model.Transfers;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.TransfersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransfersService {
	Logger logger = LoggerFactory.getLogger(TransfersService.class);
	@Autowired
	TransfersRepository transfersRepository;

	public Page<Transfers> fetchAllTransfers(Pageable pageable, String tenantId) {

		return transfersRepository.findByTenantId(pageable, tenantId);

	}

	public Transfers findByTransfers(String transfersId, String tenantId) {
		return transfersRepository.findByTransferIdAndTenantId(Long.valueOf(transfersId), tenantId);
	}

	public ResponseWrapper saveTransfers(Transfers transfers, String tenantId) {

		try {

			transfers.setTenantId(tenantId);
			transfersRepository.save(transfers);

			return new ResponseWrapper(transfers, ResponseCodes02.ADDTRANSFERS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDTRANSFERS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(transfers, ResponseCodes02.ADDTRANSFERS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDTRANSFERS_REC_FAILURE.getDescription());
		}
	}

	public Page<Transfers> searchTransfers(Specification<Transfers> specs, Pageable pageable) {

		return transfersRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Transfers> fetchAllTransfersList(String tenantId) {
		return transfersRepository.findByTenantId(tenantId);
	}
}
