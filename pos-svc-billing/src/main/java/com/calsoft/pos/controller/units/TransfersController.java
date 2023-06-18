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
import com.calsoft.pos.model.Transfers;
import com.calsoft.pos.service.TransfersService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class TransfersController {
	@Autowired
	TransfersService transfersService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all transfers page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/transfers")
	public Page<Transfers> fetchAllTransfers(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return transfersService.fetchAllTransfers(pageable, tenantId);
	}

	/***
	 * this method using fetch by single transfers
	 * 
	 * @param transfersId
	 * @return
	 */

	@GetMapping("/transfers/{id}")
	public Transfers findByTransfersId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return transfersService.findByTransfers(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save transfers
	 * 
	 * 
	 * @param transfers
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/transfers")
	public ResponseWrapper saveTransfers(@RequestBody final Transfers Transfers,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return transfersService.saveTransfers(Transfers, tenantId);
	}

	/***
	 * this method using transfers
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/transfers/filter")
	public Page<Transfers> searchTransfers(@SearchSpec Specification<Transfers> transfers, Pageable pageable) {
		return transfersService.searchTransfers(transfers, pageable);
	}

	/***
	 * this method using fetch all transfers
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/transfers/dropdown")
	public List<Transfers> fetchAllTransfersList(@RequestHeader("x-tenant") String tenantId) {
		return transfersService.fetchAllTransfersList(tenantId);
	}

}
