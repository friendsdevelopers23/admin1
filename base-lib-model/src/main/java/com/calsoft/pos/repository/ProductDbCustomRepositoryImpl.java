package com.calsoft.pos.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.productindex.ProductDb;

@Repository
public class ProductDbCustomRepositoryImpl implements ProductDbJpaCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<ProductDb> findbyProductId(Long productId, String tenantId) throws Exception {

		Query query = entityManager
				.createNativeQuery("SELECT DISTINCT Concat(tab.entity_id, '_', tab.attribute_id) AS ENTITY_PK, \n"
						+ "                tab.entity_id                                AS entityId, \n"
						+ "                tab.attribute_id                             AS attributeId, \n"
						+ "                tab.frontend_label                           AS label, \n"
						+ "                cs_catalog_category_product.category_id, \n"
						+ "                tab.attribute_code, \n" + "                CASE \n"
						+ "                  WHEN tab.frontend_label = 'Status' \n"
						+ "                       AND tab.value = 'Male' THEN '1' \n"
						+ "                  WHEN tab.frontend_label = 'Status' \n"
						+ "                       AND tab.value = 'Female' THEN '0' \n"
						+ "                  ELSE tab.value \n"
						+ "                END                                          AS value, \n"
						+ "                ev.is_filterable                             AS 'isFilterable', \n"
						+ "                st.qty                                       AS 'QTY', \n"
						+ "                ev.is_visible_on_front                       AS 'isAdditional', \n"
						+ "                tab.type_id, \n" + "                st.stock_status, \n"
						+ "                st.stock_id, \n" + "                st.website_id, \n"
						+ "                si.manage_stock, \n" + "                si.min_sale_qty, \n"
						+ "                si.max_sale_qty, \n" + "                si.is_qty_decimal, \n"
						+ "                si.notify_stock_qty, \n" + "                si.enable_qty_increments, \n"
						+ "                si.is_in_stock, \n" + "                tab.value_id, \n"
						+ "                tab.backend_type, \n" + "                si.item_id, \n"
						+ "                tab.tenant_id, \n" + "                tab.sku \n"
						+ "FROM   (SELECT ce.sku, \n" + "               ce.entity_id, \n"
						+ "               ea.attribute_id, \n" + "               ea.frontend_label, \n"
						+ "               ea.attribute_code, \n" + "               ce.tenant_id, \n"
						+ "               CASE \n"
						+ "                 WHEN ea.backend_type = 'varchar' THEN ce_varchar.value_id \n"
						+ "                 WHEN ea.backend_type = 'int' THEN ce_int.value_id \n"
						+ "                 WHEN ea.backend_type = 'text' THEN ce_text.value_id \n"
						+ "                 WHEN ea.backend_type = 'decimal' THEN ce_decimal.value_id \n"
						+ "                 WHEN ea.backend_type = 'datetime' THEN ce_datetime.value_id \n"
						+ "                 WHEN ea.backend_type = 'static' THEN 0 \n"
						+ "                 ELSE ea.backend_type \n" + "               END            AS value_id, \n"
						+ "               CASE \n"
						+ "                 WHEN ea.backend_type = 'varchar' THEN ce_varchar.value \n"
						+ "                 WHEN ea.backend_type = 'int' THEN ce_int.value \n"
						+ "                 WHEN ea.backend_type = 'text' THEN ce_text.value \n"
						+ "                 WHEN ea.backend_type = 'decimal' THEN ce_decimal.value \n"
						+ "                 WHEN ea.backend_type = 'datetime' THEN ce_datetime.value \n"
						+ "                 WHEN ea.backend_type = 'static' THEN 0 \n"
						+ "                 ELSE ea.backend_type \n" + "               END            AS value, \n"
						+ "               ea.backend_type, \n" + "               ea.is_required AS required, \n"
						+ "               ce.type_id \n" + "        FROM   cs_catalog_product_entity AS ce \n"
						+ "               LEFT JOIN cs_eav_attribute AS ea \n"
						+ "                      ON ce.entity_type_id = ea.entity_type_id \n"
						+ "               LEFT JOIN cs_catalog_product_entity_varchar AS ce_varchar \n"
						+ "                      ON ce.entity_id = ce_varchar.entity_id \n"
						+ "                         AND ea.attribute_id = ce_varchar.attribute_id \n"
						+ "                         AND ea.backend_type = 'varchar' \n"
						+ "               LEFT JOIN cs_eav_attribute_option_value a \n"
						+ "                      ON a.option_id = ce_varchar.value \n"
						+ "               LEFT JOIN cs_catalog_product_entity_int AS ce_int \n"
						+ "                      ON ce.entity_id = ce_int.entity_id \n"
						+ "                         AND ea.attribute_id = ce_int.attribute_id \n"
						+ "                         AND ea.backend_type = 'int' \n"
						+ "               LEFT JOIN cs_eav_attribute_option_value \n"
						+ "                      ON cs_eav_attribute_option_value.option_id = ce_int.value \n"
						+ "               LEFT JOIN cs_catalog_product_entity_text AS ce_text \n"
						+ "                      ON ce.entity_id = ce_text.entity_id \n"
						+ "                         AND ea.attribute_id = ce_text.attribute_id \n"
						+ "                         AND ea.backend_type = 'text' \n"
						+ "               LEFT JOIN cs_catalog_product_entity_decimal AS ce_decimal \n"
						+ "                      ON ce.entity_id = ce_decimal.entity_id \n"
						+ "                         AND ea.attribute_id = ce_decimal.attribute_id \n"
						+ "                         AND ea.backend_type = 'decimal' \n"
						+ "               LEFT JOIN cs_catalog_product_entity_datetime AS ce_datetime \n"
						+ "                      ON ce.entity_id = ce_datetime.entity_id \n"
						+ "                         AND ea.attribute_id = ce_datetime.attribute_id \n"
						+ "                         AND ea.backend_type = 'datetime') AS tab \n"
						+ "       JOIN cs_catalog_eav_attribute ev \n"
						+ "         ON tab.attribute_id = ev.attribute_id \n"
						+ "       JOIN cs_catalog_category_product \n"
						+ "         ON tab.entity_id = cs_catalog_category_product.product_id \n"
						+ "       JOIN cs_cataloginventory_stock_status st \n"
						+ "         ON st.product_id = tab.entity_id \n"
						+ "       JOIN cs_cataloginventory_stock_item si \n"
						+ "         ON si.product_id = tab.entity_id \n" + "WHERE  tab.entity_id = ?1 \n"
						+ "       AND tab.tenant_id = ?2 \n" + "       AND tab.value_id IS NOT NULL \n"
						+ "       AND tab.frontend_label IS NOT NULL");
		List<Object[]> queryResults;
		query.setParameter(1, productId);
		query.setParameter(2, tenantId);
		try {
			queryResults = query.getResultList();
		} catch (DataAccessException e) {
			throw new Exception(e.getCause());
		} catch (HibernateException e) {
			throw new Exception(e.getCause());
		}
		return queryResults.stream()
				.map(result -> new ProductDb((String) result[0], Long.valueOf(((Integer) result[1])),
						((Short) result[2]), (String) result[3], Long.valueOf(((Integer) result[4])),
						(String) result[5], (String) result[6], (Short) result[7],
						Double.valueOf(((BigDecimal) result[8]).doubleValue()), ((Short) result[9]), (String) result[10],
						(Short) result[11], (Short) result[12], (Short) result[13], (Short) result[14],
						Double.valueOf(((BigDecimal) result[15]).doubleValue()),
						Double.valueOf(((BigDecimal) result[16]).doubleValue()), (Short) result[17],
						Double.valueOf(((BigDecimal) result[18]).doubleValue()), (Short) result[19], (Short) result[20],
						((String) result[21]), ((String) result[22]), ((Integer) result[23]), ((String) result[25])))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDb> findbyProductIdWithOutAdditionValue(Long productId, String tenantId) throws Exception {

		Query query = entityManager.createNativeQuery("SELECT DISTINCT \r\n"
				+ "                       Concat(tab.entity_id, '_', tab.attribute_id) AS ENTITY_PK, \r\n"
				+ "                       tab.entity_id AS entityId, \r\n"
				+ "                       tab.attribute_id AS attributeId, \r\n"
				+ "                       tab.frontend_label AS label, \r\n"
				+ "                       cs_catalog_category_product.category_id, \r\n"
				+ "                       tab.attribute_code,                 CASE                    WHEN \r\n"
				+ "                             tab.frontend_label = 'Status'  \r\n"
				+ "                             AND tab.value = 'Male'                     THEN \r\n"
				+ "                             '1'                     WHEN \r\n"
				+ "                             tab.frontend_label = 'Status'  \r\n"
				+ "                             AND tab.value = 'Female'                     THEN \r\n"
				+ "                             '0'                     ELSE \r\n"
				+ "                             tab.value                  END \r\n"
				+ "                       AS value, ev.is_filterable AS 'isFilterable', si.qty AS 'QTY', ev.is_visible_on_front AS 'isAdditional', tab.type_id,\r\n"
				+ "                       st.stock_status, st.stock_id, st.website_id, si.manage_stock, si.min_sale_qty, si.max_sale_qty, si.is_qty_decimal,\r\n"
				+ "                       si.notify_stock_qty, si.enable_qty_increments, si.is_in_stock, tab.attribute_set_id, tab.created_at, \r\n"
				+ "                       tab.updated_at, tab.sku, tab.tenant_id, tab.is_customized,tab.frontend_input,tab.is_used_in_site,ev.is_global,ev.is_configurable\r\n"
				+ "                    FROM                 (                    SELECT \r\n"
				+ "                             ce.sku,                       ce.entity_id, \r\n"
				+ "                             ea.attribute_id,                       ea.frontend_label, \r\n"
				+ "                             ea.attribute_code,                       ce.created_at, \r\n"
				+ "                             ce.updated_at,                       ce.tenant_id, \r\n"
				+ "                             ea.is_customized,                        ea.frontend_input,\r\n"
				+ "                              ea.is_used_in_site,                      CASE \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'select'  \r\n"
				+ "                                THEN                             a.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'int' && ea.frontend_label = 'Visibility'  \r\n"
				+ "                                THEN                             ce_int.value  \r\n"
				+ "								WHEN \r\n"
				+ "                                   ea.backend_type = 'int' && ea.frontend_label = 'supplier'  \r\n"
				+ "                                THEN                             ce_int.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'int' && ea.frontend_label = 'Tax Class'  \r\n"
				+ "                                THEN                             ce_int.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'multiselect' && ea.is_customized = 1  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'multiselect'  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'int' && ea.frontend_input = 'boolean'  \r\n"
				+ "                                THEN                             ce_int.value   \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'media_image'  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'text'  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'varchar' \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'gallery'  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN \r\n"
				+ "                                   ea.backend_type = 'varchar' && ea.frontend_input = 'textarea'  \r\n"
				+ "                                THEN                             ce_varchar.value  \r\n"
				+ "                                WHEN                             ea.backend_type = 'int'  \r\n"
				+ "                                THEN \r\n"
				+ "                                   cs_eav_attribute_option_value.value  \r\n"
				+ "                                WHEN                             ea.backend_type = 'text'  \r\n"
				+ "                                THEN                             ce_text.value  \r\n"
				+ "                                WHEN                             ea.backend_type = 'decimal'  \r\n"
				+ "                                THEN                             ce_decimal.value  \r\n"
				+ "                                WHEN                             ea.backend_type = 'datetime'  \r\n"
				+ "                                THEN                             ce_datetime.value  \r\n"
				+ "                                ELSE                             ea.backend_type  \r\n"
				+ "                             END \r\n"
				+ "                             AS value, ea.backend_type, ea.is_required AS required, ce.type_id, ce.attribute_set_id  \r\n"
				+ "                          FROM                       cs_catalog_product_entity AS ce  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_eav_entity_attribute AS eav_entity  \r\n"
				+ "                                ON ce.attribute_set_id = eav_entity.attribute_set_id  \r\n"
				+ "                             LEFT JOIN                          cs_eav_attribute AS ea  \r\n"
				+ "                                ON eav_entity.attribute_id = ea.attribute_id  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_catalog_product_entity_varchar AS ce_varchar  \r\n"
				+ "                                ON ce.entity_id = ce_varchar.entity_id  \r\n"
				+ "                                AND ea.attribute_id = ce_varchar.attribute_id  \r\n"
				+ "                                AND ea.backend_type = 'varchar'  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_eav_attribute_option_value a  \r\n"
				+ "                                ON a.option_id = ce_varchar.value  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_catalog_product_entity_int AS ce_int  \r\n"
				+ "                                ON ce.entity_id = ce_int.entity_id  \r\n"
				+ "                                AND ea.attribute_id = ce_int.attribute_id  \r\n"
				+ "                                AND ea.backend_type = 'int'                        LEFT JOIN \r\n"
				+ "                                cs_eav_attribute_option_value  \r\n"
				+ "                                ON cs_eav_attribute_option_value.option_id = ce_int.value  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_catalog_product_entity_text AS ce_text  \r\n"
				+ "                                ON ce.entity_id = ce_text.entity_id  \r\n"
				+ "                                AND ea.attribute_id = ce_text.attribute_id  \r\n"
				+ "                                AND ea.backend_type = 'text'                        LEFT JOIN \r\n"
				+ "                                cs_catalog_product_entity_decimal AS ce_decimal  \r\n"
				+ "                                ON ce.entity_id = ce_decimal.entity_id  \r\n"
				+ "                                AND ea.attribute_id = ce_decimal.attribute_id  \r\n"
				+ "                                AND ea.backend_type = 'decimal'  \r\n"
				+ "                             LEFT JOIN \r\n"
				+ "                                cs_catalog_product_entity_datetime AS ce_datetime  \r\n"
				+ "                                ON ce.entity_id = ce_datetime.entity_id  \r\n"
				+ "                                AND ea.attribute_id = ce_datetime.attribute_id  \r\n"
				+ "                                AND ea.backend_type = 'datetime'                 ) \r\n"
				+ "                       AS tab                  JOIN \r\n"
				+ "                          cs_catalog_eav_attribute ev  \r\n"
				+ "                          ON tab.attribute_id = ev.attribute_id                  JOIN \r\n"
				+ "                          cs_catalog_category_product  \r\n"
				+ "                          ON tab.entity_id = cs_catalog_category_product.product_id  \r\n"
				+ "                       JOIN                    cs_cataloginventory_stock_status st  \r\n"
				+ "                          ON st.product_id = tab.entity_id                  JOIN \r\n"
				+ "                          cs_cataloginventory_stock_item si  \r\n"
				+ "                          ON si.product_id = tab.entity_id               WHERE \r\n"
				+ "                       tab.entity_id =?1     and  tab.tenant_id =?2          \r\n"
				+ "                    AND tab.frontend_label IS NOT NULL  \r\n"
				+ "                       AND tab.value IS NOT NULL               GROUP BY \r\n"
				+ "                       ev.position,tab.attribute_id");
		List<Object[]> queryResults;

		query.setParameter(1, productId);
		query.setParameter(2, tenantId);
		try {
			queryResults = query.getResultList();
		} catch (DataAccessException e) {
			throw new Exception(e.getCause());
		} catch (HibernateException e) {
			throw new Exception(e.getCause());
		}

		return queryResults.stream().map(result -> new ProductDb((String) result[0],
				Long.valueOf(((Integer) result[1])), ((Short) result[2]), (String) result[3],
				Long.valueOf(((Integer) result[4])), (String) result[5], (String) result[6], (Short) result[7],
				Double.valueOf(((BigDecimal) result[8]).doubleValue()), ((Short) result[9]), (String) result[10],
				(Short) result[11], (Short) result[12], (Short) result[13], (Short) result[14],
				Double.valueOf(((BigDecimal) result[15]).doubleValue()),
				Double.valueOf(((BigDecimal) result[16]).doubleValue()), (Short) result[17],
				Double.valueOf(((BigDecimal) result[18]).doubleValue()), (Short) result[19], (Short) result[20],
				((Short) result[21]), ((Date) result[22]), ((Date) result[23]), (String) result[24], (Short) result[26],
				(String) result[27], (Short) result[28], (Short) result[29], (Short) result[30]

		)).collect(Collectors.toList());
	}

}
