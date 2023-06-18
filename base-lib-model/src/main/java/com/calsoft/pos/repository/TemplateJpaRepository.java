package com.calsoft.pos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateJpaRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> getImageByEntity(int entityId) {

		List<String> imageList = jdbcTemplate.query(
				"SELECT mg.VALUE,mgv.POSITION,mg.entity_id FROM cs_catalog_product_entity_media_gallery mg inner join\n"
						+ "cs_catalog_product_entity_media_gallery_value mgv\n" + "ON\n" + "mg.value_id=mgv.VALUE_id\n"
						+ "WHERE mg.entity_id=?\n" + "ORDER BY mgv.POSITION",
				new Object[] { entityId }, new ImageRowMapper());
		return imageList;
	}
}

class ImageRowMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {

		return rs.getString("value");
	}
}
