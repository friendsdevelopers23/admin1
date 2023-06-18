
package com.calsoft.pos.controller.units;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

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

import com.calsoft.pos.model.CashRegister;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.CashRegisterRegisterService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class CashRegistersController {

	@Autowired
	public CashRegisterRegisterService cashRegisterService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using country page filter columns name
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping(value = "/cashRegister/filter")
	public Page<CashRegister> searchcashRegister(@SearchSpec Specification<CashRegister> specs, Pageable pageable) {
		return cashRegisterService.searchCashRegisterData(specs, pageable);
	}

	@PostMapping(value = "/cashRegister")
	public ResponseWrapper savecashRegister(@RequestBody final CashRegister cashRegister,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) throws ParseException {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		cashRegister.setUserId(Long.valueOf(customerId));
		return cashRegisterService.saveCashRegister(cashRegister, tenantId, customerId);

	}

	@GetMapping(value = "/cashRegister")
	public Page<CashRegister> fetchAllcashRegister(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return cashRegisterService.getAllCashRegisterData(pageable, tenantId);
	}

	/***
	 * this method using fetch by cashRegister based on single keyId
	 * 
	 * @param keyId
	 * @return
	 */

	@GetMapping("/cashRegister/{id}")
	public CashRegister findByKeyId(@PathVariable(value = "id") String id, @RequestHeader("x-tenant") String tenantId) {

		return cashRegisterService.fetchById(Integer.parseInt(id), tenantId);
	}

	@GetMapping("/cashRegister/active")
	public boolean findCashRegisterActiveOrNot(@RequestHeader("x-tenant") String tenantId, HttpServletRequest request) {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cashRegisterService.fetchByCashRegisterActive(customerId, tenantId);
	}

	@GetMapping("/cashRegister/active/data")
	public CashRegister fetchCashRegisterActiveData(@RequestHeader("x-tenant") String tenantId,
			HttpServletRequest request) {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);
		return cashRegisterService.fetchCashRegisterActiveData(customerId, tenantId);
	}

}
