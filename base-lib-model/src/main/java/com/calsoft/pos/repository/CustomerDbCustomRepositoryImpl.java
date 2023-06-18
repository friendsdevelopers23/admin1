package com.calsoft.pos.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.repository.CustomerDbJpaCustomRepository;
import com.calsoft.pos.model.customerindex.CustomerDb;

@Repository

public class CustomerDbCustomRepositoryImpl implements CustomerDbJpaCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDb> findbyCustomerId(Integer customerId, String tenantId) throws Exception {

		Query query = entityManager
				.createNativeQuery("SELECT DISTINCT Concat(tab.entity_id, '_', tab.attribute_id) AS ENTITY_PK, \r\n"
						+ "                tab.* \r\n" + "FROM   (SELECT ce.*, \r\n"
						+ "               ea.attribute_code, \r\n" + "               ea.frontend_label, \r\n"
						+ "               ea.backend_type, \r\n" + "               ea.attribute_id, \r\n"
						+ "               CASE ea.backend_type \r\n"
						+ "                 WHEN 'varchar' THEN ce_varchar.value \r\n"
						+ "                 WHEN 'int'  and ce_int.attribute_id=18  THEN ce_int.value\r\n"
						+ "                 WHEN 'int' THEN cs_eav_attribute_option_value.value \r\n"
						+ "                 WHEN 'text' THEN ce_text.value \r\n"
						+ "                 WHEN 'decimal' THEN ce_decimal.value \r\n"
						+ "                 WHEN 'datetime' THEN ce_datetime.value \r\n"
						+ "                 ELSE NULL \r\n" + "               END AS value \r\n"
						+ "        FROM   cs_customer_entity AS ce \r\n"
						+ "               LEFT JOIN cs_eav_attribute AS ea \r\n"
						+ "                      ON ce.entity_type_id = ea.entity_type_id \r\n"
						+ "               LEFT JOIN cs_customer_entity_varchar AS ce_varchar \r\n"
						+ "                      ON ce.entity_id = ce_varchar.entity_id \r\n"
						+ "                         AND ea.attribute_id = ce_varchar.attribute_id \r\n"
						+ "                         AND ea.backend_type = 'varchar' \r\n"
						+ "               LEFT JOIN cs_customer_entity_int AS ce_int \r\n"
						+ "                      ON ce.entity_id = ce_int.entity_id \r\n"
						+ "                         AND ea.attribute_id = ce_int.attribute_id \r\n"
						+ "                         AND ea.backend_type = 'int' \r\n"
						+ "               LEFT JOIN cs_eav_attribute_option_value \r\n"
						+ "                      ON cs_eav_attribute_option_value.option_id = ce_int.value \r\n"
						+ "               LEFT JOIN cs_customer_entity_text AS ce_text \r\n"
						+ "                      ON ce.entity_id = ce_text.entity_id \r\n"
						+ "                         AND ea.attribute_id = ce_text.attribute_id \r\n"
						+ "                         AND ea.backend_type = 'text' \r\n"
						+ "               LEFT JOIN cs_customer_entity_decimal AS ce_decimal \r\n"
						+ "                      ON ce.entity_id = ce_decimal.entity_id \r\n"
						+ "                         AND ea.attribute_id = ce_decimal.attribute_id \r\n"
						+ "                         AND ea.backend_type = 'decimal' \r\n"
						+ "               LEFT JOIN cs_customer_entity_datetime AS ce_datetime \r\n"
						+ "                      ON ce.entity_id = ce_datetime.entity_id \r\n"
						+ "                         AND ea.attribute_id = ce_datetime.attribute_id \r\n"
						+ "                         AND ea.backend_type = 'datetime') AS tab \r\n"
						+ "WHERE  tab.entity_id = ?1 \r\n" + "       AND tab.tenant_id =  ?2");
		List<Object[]> queryResults;

		query.setParameter(1, customerId);
		query.setParameter(2, tenantId);
		try {
			queryResults = query.getResultList();
		} catch (DataAccessException e) {
			throw new Exception(e.getCause());
		} catch (HibernateException e) {
			throw new Exception(e.getCause());
		}
		return queryResults.stream()
				.map(result -> new CustomerDb((String) result[0], ((Integer) result[1]), ((Short) result[2]),
						((Short) result[3]), ((Short) result[4]), (String) result[5], ((Short) result[6]),
						((Integer) result[7]), ((Short) result[8]), ((Timestamp) result[9]), ((Timestamp) result[10]),
						((Short) result[11]), (Short) result[12], (String) result[14], (String) result[15],
						(String) result[16], ((Short) result[17]), (String) result[18]))
				.collect(Collectors.toList());
	}

}
