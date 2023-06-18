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

import com.calsoft.pos.model.categoryindex.CategoryDb;

@Repository
public class CategoryDbCustomRepositoryImpl implements CategoryDbJpaCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CategoryDb> findbyCategoryId(Integer categoryId,String tenantId) throws Exception  {

		Query query = entityManager.createNativeQuery("SELECT DISTINCT\r\n" + 
				"concat(tab.entity_id, '_',tab.attribute_id) AS ENTITY_PK,\r\n" + 
				"tab.*\r\n" + 
				" FROM (    SELECT\r\n" + 
				"        ce.*,\r\n" + 
				"        ea.attribute_id,\r\n" + 
				"        ea.frontend_label,\r\n" + 
				"        ea.backend_type,\r\n" + 
				"        ea.attribute_code,\r\n" + 
				"            CASE\r\n" + 
				"              WHEN ea.backend_type='int' and  ea.frontend_label='Is Active' THEN ce_int.value\r\n" + 
				"           WHEN ea.backend_type='int' and  ea.frontend_label='Include in Navigation Menu' THEN ce_int.value\r\n" +
				"           WHEN ea.backend_type='int' and  ea.frontend_label='position' THEN ce_int.value\r\n" +
				"           WHEN ea.backend_type='int' and  ea.frontend_label='Is Anchor' THEN ce_int.value\r\n" +
				"           WHEN ea.backend_type='int' and  ea.frontend_label='Product List Type' THEN ce_int.value\r\n" +
				"           WHEN ea.backend_type='int' and  ea.frontend_label='Display Description' THEN ce_int.value\r\n" +
				"     WHEN ea.backend_type='varchar'  THEN ce_varchar.value\r\n" + 
				"           WHEN ea.backend_type='int' THEN cs_eav_attribute_option_value.value\r\n" + 
				"           WHEN ea.backend_type='text' THEN ce_text.value\r\n" + 
				"           WHEN ea.backend_type='decimal' THEN ce_decimal.value\r\n" + 
				"           WHEN ea.backend_type='datetime' THEN ce_datetime.value\r\n" + 
				"           ELSE ea.backend_type\r\n" + 
				"        END AS value,\r\n" + 
				"        ea.is_required AS required\r\n" + 
				"    FROM cs_catalog_category_entity AS ce\r\n" + 
				"    LEFT JOIN cs_eav_attribute AS ea\r\n" + 
				"        ON ce.entity_type_id = ea.entity_type_id\r\n" + 
				"    LEFT JOIN cs_catalog_category_entity_varchar AS ce_varchar\r\n" + 
				"        ON ce.entity_id = ce_varchar.entity_id\r\n" + 
				"        AND ea.attribute_id = ce_varchar.attribute_id\r\n" + 
				"        AND ea.backend_type = 'varchar'\r\n" + 
				"        LEFT JOIN cs_eav_attribute_option_value a\r\n" + 
				"        on a.option_id =ce_varchar.value\r\n" + 
				"    LEFT JOIN cs_catalog_category_entity_int AS ce_int\r\n" + 
				"        ON ce.entity_id = ce_int.entity_id\r\n" + 
				"        AND ea.attribute_id = ce_int.attribute_id\r\n" + 
				"        AND ea.backend_type = 'int'\r\n" + 
				"        LEFT JOIN cs_eav_attribute_option_value\r\n" + 
				"        on cs_eav_attribute_option_value.option_id =ce_int.value\r\n" + 
				"    LEFT JOIN cs_catalog_category_entity_text AS ce_text\r\n" + 
				"        ON ce.entity_id = ce_text.entity_id\r\n" + 
				"        AND ea.attribute_id = ce_text.attribute_id\r\n" + 
				"        AND ea.backend_type = 'text'\r\n" + 
				"    LEFT JOIN cs_catalog_category_entity_decimal AS ce_decimal\r\n" + 
				"        ON ce.entity_id = ce_decimal.entity_id\r\n" + 
				"        AND ea.attribute_id = ce_decimal.attribute_id\r\n" + 
				"        AND ea.backend_type = 'decimal'\r\n" + 
				"    LEFT JOIN cs_catalog_category_entity_datetime AS ce_datetime\r\n" + 
				"        ON ce.entity_id = ce_datetime.entity_id\r\n" + 
				"        AND ea.attribute_id = ce_datetime.attribute_id\r\n" + 
				"        AND ea.backend_type = 'datetime'\r\n" + 
				" ) AS tab\r\n" + 
				" where\r\n" + 
				" tab.entity_id=?1 AND  tab.tenant_id=?2");
		List<Object[]>	queryResults;
		
		query.setParameter(1,categoryId);
		query.setParameter(2,tenantId);
		try{
			queryResults=query.getResultList();
		}
		catch (DataAccessException e) {
			throw new Exception(e.getCause());
		} 
		catch (HibernateException e) {
			throw new Exception(e.getCause());
		}
		return   queryResults.stream().map(result -> new CategoryDb(
				(String)  result[0],
				((Integer) result[1]),
				((Short) result[2]),
				((Short) result[3]),
				((Integer) result[4]),
				((Timestamp) result[5]),
                ((Timestamp)result[6]),
				(String)  result[7],
				((Integer)  result[8]),
				((Integer)  result[9]),
				((Integer) result[10]),
				((Short) result[12]),
				(String)  result[13],
				(String)  result[14],
				(String)  result[16],
				((Short) result[17]),
				(String)  result[15]
				)).collect(Collectors.toList());
	}
	
	
	
}
