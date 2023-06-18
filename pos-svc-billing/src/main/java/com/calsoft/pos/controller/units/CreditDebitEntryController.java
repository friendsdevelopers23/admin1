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
import com.calsoft.pos.model.CreditDebitEntry;
import com.calsoft.pos.service.CreditDebitEntryService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class CreditDebitEntryController {
	@Autowired
	CreditDebitEntryService creditDebitEntryService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all creditDebitEntry page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/creditdebitentry")
	public Page<CreditDebitEntry> fetchAllCreditDebitEntry(Pageable pageable,
			@RequestHeader("x-tenant") String tenantId) {
		return creditDebitEntryService.fetchAllCreditDebitEntry(pageable, tenantId);
	}

	/***
	 * this method using fetch by single creditDebitEntry
	 * 
	 * @param CreditDebitEntryId
	 * @return
	 */

	@GetMapping("/creditdebitentry/{id}")
	public CreditDebitEntry findCreditDebitEntryId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return creditDebitEntryService.findByCreditDebitEntry(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save creditDebitEntry
	 * 
	 * 
	 * @param creditDebitEntry
	 * @return
	 * @throws ParsecreditDebitEntry
	 */
	@PostMapping("/creditdebitentry")
	public ResponseWrapper saveCreditDebitEntry(@RequestBody final CreditDebitEntry CreditDebitEntry,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return creditDebitEntryService.saveCreditDebitEntry(CreditDebitEntry, tenantId);
	}

	/***
	 * this method using creditDebitEntry
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/creditdebitentry/filter")
	public Page<CreditDebitEntry> searchCreditDebitEntry(@SearchSpec Specification<CreditDebitEntry> creditDebitEntry,
			Pageable pageable) {
		return creditDebitEntryService.searchCreditDebitEntry(creditDebitEntry, pageable);
	}

	/***
	 * this method using fetch all creditDebitEntry
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/creditdebitentry/dropdown")
	public List<CreditDebitEntry> fetchAllCreditDebitEntryList(@RequestHeader("x-tenant") String tenantId) {
		return creditDebitEntryService.fetchAllCreditDebitEntryList(tenantId);
	}

}
