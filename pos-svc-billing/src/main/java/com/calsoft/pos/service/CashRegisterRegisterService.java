package com.calsoft.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.config.OrderConfig;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.model.CashRegister;
import com.calsoft.pos.model.CashRegisterTransactions;
import com.calsoft.pos.repository.CashRegistersRepository;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class CashRegisterRegisterService {

	@Autowired
	private CashRegistersRepository cashRegisterRepository;

	public Page<CashRegister> searchCashRegisterData(Specification<CashRegister> specs, Pageable pageable) {

		return cashRegisterRepository.findAll(Specification.where(specs), pageable);
	}

	public ResponseWrapper saveCashRegister(CashRegister cashRegister, String tenantId, String customerId) {
		try {

			CashRegister saveDataCashRegister = null;
			if (cashRegister.getOperationType() != null && cashRegister.getOperationType().equalsIgnoreCase("New")) {

				cashRegister.setStatus(1);

				cashRegister.setUserId(Long.valueOf(customerId));

				// hard code will change it
				cashRegister.setBusinessId(Long.valueOf(1));

				// hard code will change it
				cashRegister.setLocationId(Long.valueOf(1));

				cashRegister.setTenantId(tenantId);

				saveDataCashRegister = cashRegisterRepository.saveAndFlush(cashRegister);

				CashRegisterTransactions cashRegisterTransactions = new CashRegisterTransactions();

				cashRegisterTransactions.setAmount(cashRegister.getTotalCashInHand());

				cashRegisterTransactions.setType(OrderConfig.CASH_REGISTER_TRANSACTION_TYPE_CREDIT);

				cashRegisterTransactions.setTransactionType(OrderConfig.CASH_REGISTER_TRANSACTION_TYPE_INITIAL);

				cashRegisterTransactions.setTransactionId(Long.valueOf(0));

				cashRegisterTransactions.setCashRegister(saveDataCashRegister);

				cashRegisterTransactions.setPayMethod("Cash");

				saveDataCashRegister.getCashRegisterTransactions().add(cashRegisterTransactions);
				cashRegisterRepository.save(saveDataCashRegister);

			} else {

				cashRegister.setStatus(0);

				Timestamp instant = Timestamp.from(Instant.now());

				cashRegister.setClosedAt(instant);

				cashRegisterRepository.save(cashRegister);
			}

			if (cashRegister.getOperationType() != null && cashRegister.getOperationType().equalsIgnoreCase("New")) {
				return new ResponseWrapper(cashRegister, ResponseCodes02.ADD_CASH_REGISTER_SUCCESS.getCode(),
						ResponseCodes02.ADD_CASH_REGISTER_SUCCESS.getDescription());
			}

			return new ResponseWrapper(cashRegister, ResponseCodes02.UPDATE_CASH_REGISTER_SUCCESS.getCode(),
					ResponseCodes02.UPDATE_CASH_REGISTER_SUCCESS.getDescription());
		} catch (Exception e) {

			e.printStackTrace();
			if (cashRegister.getOperationType() != null && cashRegister.getOperationType().equalsIgnoreCase("New")) {
				return new ResponseWrapper(cashRegister, ResponseCodes02.ADD_CASH_REGISTER_FAILED.getCode(),
						ResponseCodes02.ADD_CASH_REGISTER_FAILED.getDescription());
			}
			return new ResponseWrapper(cashRegister, ResponseCodes02.UPDATE_CASH_REGISTER_FAILED.getCode(),
					ResponseCodes02.UPDATE_CASH_REGISTER_FAILED.getDescription());

		}
	}

	public Page<CashRegister> getAllCashRegisterData(Pageable pageable, String tenantId) {

		return cashRegisterRepository.findByTenantId(pageable, tenantId);
	}

	public CashRegister fetchById(int id, String tenantId) {
		CashRegister cashRegister = new CashRegister();

		try {
			cashRegister = cashRegisterRepository.findByIdAndTenantId(id, tenantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cashRegister;
	}

	public boolean fetchByCashRegisterActive(String customerId, String tenantId) {

		boolean status = false;

		try {

			CashRegister cashRegister = cashRegisterRepository
					.findByUserIdAndStatusAndTenantId(Long.valueOf(customerId), 1, tenantId);

			if (cashRegister != null) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public CashRegister fetchCashRegisterActiveData(String customerId, String tenantId) {

		CashRegister cashRegister = null;
		try {

			cashRegister = cashRegisterRepository.findByUserIdAndStatusAndTenantId(Long.valueOf(customerId), 1,
					tenantId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cashRegister;
	}

}
