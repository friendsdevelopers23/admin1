package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.CustomerGroup;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.CustomerGroupRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerGroupService {
	Logger logger = LoggerFactory.getLogger(CustomerGroupService.class);
	@Autowired
	CustomerGroupRepository customerGroupRepository;

	public Page<CustomerGroup> fetchAllCustomerGroup(Pageable pageable, String tenantId) {

		return customerGroupRepository.findByTenantId(pageable, tenantId);

	}

	public CustomerGroup findByCustomerGroup(String customerGroupId, String tenantId) {
		return customerGroupRepository.findByCustomerGroupIdAndTenantId(Long.valueOf(customerGroupId), tenantId);
	}

	public ResponseWrapper saveCustomerGroup(CustomerGroup customerGroup, String tenantId) {

		try {

			customerGroup.setTenantId(tenantId);
			customerGroupRepository.save(customerGroup);

			return new ResponseWrapper(customerGroup, ResponseCodes02.ADDCUSTOMER_GROUP_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDCUSTOMER_GROUP_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(customerGroup, ResponseCodes02.ADDCUSTOMER_GROUP_REC_FAILURE.getCode(),
					ResponseCodes02.ADDCUSTOMER_GROUP_REC_FAILURE.getDescription());
		}
	}

	public Page<CustomerGroup> searchCustomerGroup(Specification<CustomerGroup> specs, Pageable pageable) {

		return customerGroupRepository.findAll(Specification.where(specs), pageable);
	}

	public List<CustomerGroup> fetchAllCustomerGroupList(String tenantId) {
		return customerGroupRepository.findByTenantId(tenantId);
	}

}
