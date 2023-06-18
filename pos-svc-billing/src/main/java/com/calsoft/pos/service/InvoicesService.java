package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.Invoices;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.InvoicesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoicesService {

	Logger logger = LoggerFactory.getLogger(InvoicesService.class);
	@Autowired
	InvoicesRepository invoicesRepository;

	public Page<Invoices> fetchAllInvoices(Pageable pageable, String tenantId) {

		return invoicesRepository.findByTenantId(pageable, tenantId);

	}

	public Invoices findByInvoices(String invoicesId, String tenantId) {
		return invoicesRepository.findByInvoiceIdAndTenantId(Long.valueOf(invoicesId), tenantId);
	}

	public ResponseWrapper saveInvoices(Invoices invoices, String tenantId) {

		try {

			invoices.setTenantId(tenantId);
			invoicesRepository.save(invoices);

			return new ResponseWrapper(invoices, ResponseCodes02.ADDINVOICES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDINVOICES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(invoices, ResponseCodes02.ADDINVOICES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDINVOICES_REC_FAILURE.getDescription());
		}
	}

	public Page<Invoices> searchInvoices(Specification<Invoices> specs, Pageable pageable) {

		return invoicesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Invoices> fetchAllInvoicesList(String tenantId) {
		return invoicesRepository.findByTenantId(tenantId);
	}
}
