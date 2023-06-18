package com.calsoft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calsoft.pos.model.category.CategoryVarchar;
import com.calsoft.pos.model.categoryindex.CategoryDb;
import com.calsoft.pos.model.categoryindex.CategoryIDX;
import com.calsoft.pos.model.eavattribute.EavAttribute;
import com.calsoft.pos.repository.CategoryJpaRepository;
import com.calsoft.pos.repository.CategoryVarcharRepository;
import com.calsoft.pos.repository.EavAttributeJpaRepository;

@Component
public class CategoryIndexUtils {

	@Autowired
	public EavAttributeJpaRepository eavAttributeJpaRepository;

	@Autowired
	public CategoryVarcharRepository categoryVarcharRepository;

	@Autowired
	public CategoryJpaRepository categoryJpaRepository;

	public void toCategoryIDX(List<CategoryDb> categoryDb, CategoryIDX categoryIDX, List<CategoryIDX> toBeSaved,
			String tenantId) {
		Map<String, String> customValues = new HashMap<>();
		if (categoryDb.get(0).getParentId() > 1) {
			categoryIDX.setProductListType(1);
			categoryIDX.setPosition(0);
			categoryIDX.setDisplayDescription(0);
			categoryIDX.setMega("false");
			categoryIDX.setDisplayMode(2);
			for (CategoryDb spec : categoryDb) {

				if (spec.getValue() != null) {
					if (spec.getAttributeCode().equalsIgnoreCase("description")) {
						categoryIDX.setDescription(spec.getValue());
					} else if (spec.getAttributeCode().equalsIgnoreCase("display_description")) {
						categoryIDX.setDisplayDescription(Long.valueOf(spec.getValue()));
					} else if (spec.getAttributeCode().equalsIgnoreCase("product_list_type")) {
						categoryIDX.setProductListType(Long.valueOf(spec.getValue()));
					} else if (spec.getAttributeCode().equalsIgnoreCase("meta_description")) {
						categoryIDX.setMetaDescription(spec.getValue());
					} else if (spec.getAttributeCode().equalsIgnoreCase("image")) {
						categoryIDX.setImage(spec.getValue());
					} else if (spec.getAttributeCode().equalsIgnoreCase("is_active")) {
						categoryIDX.setIsActive(spec.getValue());
					} else if (spec.getAttributeCode().equalsIgnoreCase("include_in_menu")) {
						categoryIDX.setIncludeInMenu(spec.getValue());
					} else if (spec.getParentId() == 2 && spec.getAttributeCode().equalsIgnoreCase("is_anchor")) {
						String enableMega = setMega(String.valueOf(spec.getValue()));
						categoryIDX.setMega(enableMega);
					} else if (spec.getAttributeCode().equalsIgnoreCase("name")) {
						categoryIDX.setCategoryName(spec.getValue());
					} else if (spec.getAttributeCode().equalsIgnoreCase("position")) {
						if (spec.getValue() == null) {
							categoryIDX.setPosition(0);
						} else {
							categoryIDX.setPosition(Long.valueOf(spec.getValue()));

						}

					} else if (spec.getParentId() == 2) {
						categoryIDX.setType("PARENT");
					} else if (spec.getParentId() != 2) {
						categoryIDX.setType("CHILD");
					} else if (spec.getAttributeCode().equalsIgnoreCase("name")) {
						categoryIDX.setCategoryName(spec.getValue());
					}
					if (spec.getAttributeCode().equalsIgnoreCase("url_path")) {
						customValues.put("urlPath", spec.getValue());
						categoryIDX.setUrlPath(spec.getValue());
					}

					if (spec.getAttributeCode().equalsIgnoreCase("display_mode")&&!spec.getValue().isEmpty()) {
						
						customValues.put("displayMode", spec.getValue());
						categoryIDX.setDisplayMode(Long.valueOf(spec.getValue()));
					}

					categoryIDX.setPathValue(spec.getPath());
					categoryIDX.setPath(spec.getPath());
					categoryIDX.setParentId(String.valueOf(spec.getParentId()));
					categoryIDX.setCategoryId(spec.getEntityId());
					categoryIDX.setTenantId(tenantId);

					if (categoryIDX.getParentId().equalsIgnoreCase("2")) {
						categoryIDX.setPath(String.valueOf(spec.getEntityId()));
					}
				}
				categoryIDX.setIsCustomValue(customValues);
				categoryIDX.setChildCount(setChildCount(categoryDb.get(0).getPath()));
				categoryIDX.setBreadCrumpPath(setBreadCrump(categoryIDX, tenantId));
				toBeSaved.add(categoryIDX);
			}
		}
	}

	private long setChildCount(String path) {
		int size = categoryJpaRepository.findNumberOfMenu(path);
		return Long.valueOf(size);
	}

	private String setMega(String path) {

		String mega = "false";
		if (path.equalsIgnoreCase("1")) {
			mega = "true";
		} else {
			mega = "false";
		}

		return mega;
	}

	public String setBreadCrump(CategoryIDX category, String tenantId) {
		if (!category.getParentId().equalsIgnoreCase("2")) {
			String crumPath = category.getPath().replace("1/2/", "");
			String url = category.getPath().replace("1/2/", "");
			String[] paths = crumPath.split("/");
			System.out.println(category.getCategoryId());
			List<String> path = Arrays.asList(paths);
			System.out.println(path);
			List<Integer> newList = new ArrayList<Integer>();
			try {
				newList = path.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
			} catch (Exception e) {

			}

			EavAttribute eavAttribute = eavAttributeJpaRepository
					.findByAttributeCode(CommonUtils.CATEGORYNAME_ATTRIBUTE, 3, tenantId, CommonUtils.BASE_ATTRIBUTE);
			List<CategoryVarchar> categoryName = categoryVarcharRepository.findCategoryName(newList,
					(int) eavAttribute.getAttributeId(), (int) 3, tenantId);
			for (CategoryVarchar breadcrumb : categoryName) {
				crumPath = crumPath.replaceAll("\\b" + String.valueOf(breadcrumb.getEntityId()) + "\\b",
						breadcrumb.getValue());
			}
			System.out.println(crumPath);
			category.setBreadCrumpPath(crumPath.replace("/", ","));
			category.setPath(url.replace("/", ","));
		} else {
			category.setBreadCrumpPath(category.getCategoryName());
		}
		return category.getBreadCrumpPath();
	}
}
