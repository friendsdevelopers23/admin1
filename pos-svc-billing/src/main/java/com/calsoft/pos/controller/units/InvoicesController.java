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

import com.calsoft.pos.model.Invoices;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.InvoicesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class InvoicesController {
	@Autowired
	InvoicesService invoicesService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all invoices page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/invoices")
	public Page<Invoices> fetchAllInvoices(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return invoicesService.fetchAllInvoices(pageable, tenantId);
	}

	/***
	 * this method using fetch by single invoices
	 * 
	 * @param invoicesId
	 * @return
	 */

	@GetMapping("/invoices/{id}")
	public Invoices findByInvoicesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return invoicesService.findByInvoices(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save invoices
	 * 
	 * 
	 * @param invoices
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/invoices")
	public ResponseWrapper saveInvoices(@RequestBody final Invoices Invoices,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return invoicesService.saveInvoices(Invoices, tenantId);
	}

	/***
	 * this method using invoices
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/invoices/filter")
	public Page<Invoices> searchInvoices(@SearchSpec Specification<Invoices> invoices, Pageable pageable) {
		return invoicesService.searchInvoices(invoices, pageable);
	}

	/***
	 * this method using fetch all invoices
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/invoices/dropdown")
	public List<Invoices> fetchAllInvoicesList(@RequestHeader("x-tenant") String tenantId) {
		return invoicesService.fetchAllInvoicesList(tenantId);
	}

}
