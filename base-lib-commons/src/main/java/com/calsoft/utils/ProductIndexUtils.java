package com.calsoft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.calsoft.model.ConfigProdJson;
import com.calsoft.pos.model.categoryindex.CategoryIDX;
import com.calsoft.pos.model.eavattribute.EavAttribute;
import com.calsoft.pos.model.eavattribute.EavAttributeOptionValue;
import com.calsoft.pos.model.eavattribute.EavAttributeSet;
import com.calsoft.pos.model.product.CatalogProductSuperAttribute;
import com.calsoft.pos.model.product.CatalogProductSuperLink;
import com.calsoft.pos.model.productindex.ProductDb;
import com.calsoft.pos.model.productindex.ProductIDX2;
import com.calsoft.pos.model.reviewrating.RatingOptionVote;
import com.calsoft.pos.model.taxclass.TaxCalculationRate;
import com.calsoft.pos.repository.CatalogCategoryProductRepository;
import com.calsoft.pos.repository.CatalogProductSuperAttributeRepository;
import com.calsoft.pos.repository.CatalogProductSuperLinkRepository;
import com.calsoft.pos.repository.CategoryIdxV2Repository;
import com.calsoft.pos.repository.EavAttributeJpaRepository;
import com.calsoft.pos.repository.EavAttributeOptionValueRepository;
import com.calsoft.pos.repository.EavAttributeSetJpaRepository;
import com.calsoft.pos.repository.ProductIDx2Repository;
import com.calsoft.pos.repository.RatingOptionVoteJpaRepository;
import com.calsoft.pos.repository.TaxRateJpaRepository;
import com.calsoft.pos.repository.TemplateJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductIndexUtils {

	@Autowired
	public RatingOptionVoteJpaRepository ratingOptionVoteJpaRepository;

	@Autowired
	private CatalogCategoryProductRepository catalogCategoryProductRepository;

	@Autowired
	private CategoryIdxV2Repository categoryIdxV2Repository;

	@Autowired
	private TemplateJpaRepository templateJpaRepository;

	@Autowired
	private TaxRateJpaRepository taxRateJpaRepository;

	@Autowired
	EavAttributeSetJpaRepository eavAttributeSetJpaRepository;

	@Autowired
	private CatalogProductSuperLinkRepository catalogProductSuperLinkRepository;

	@Autowired
	private CatalogProductSuperAttributeRepository catalogProductSuperAttributeRepository;

	@Autowired
	private EavAttributeOptionValueRepository eavAttributeOptionValueRepository;

	@Autowired
	private ProductIDx2Repository productIDx2Repository;

	@Autowired
	private EavAttributeJpaRepository eavAttributeJpaRepository;

	@Autowired
	ObjectMapper objectMapper;

	public void toProductIDX2(List<ProductDb> productDb, ProductIDX2 productIDX2, List<ProductIDX2> toBeSaved,
			String tenantId) throws ParseException {
		Map<String, String> values = new HashMap<>();
		Map<String, String> additionalValues = new HashMap<>();
		Map<String, String> customValues = new HashMap<>();
		Map<String, String> faq = new HashMap<>();
		Map<String, String> FactBox = new HashMap<>();
		Map<String, String> SIDEEFFECTS = new HashMap<>();
		Map<String, String> CommonSideEffects = new HashMap<>();
		Map<String, String> SafetyAdvice = new HashMap<>();
		LinkedHashMap<String, String> configMap = new LinkedHashMap<>();
		productIDX2.setAttributeSetId(Long.valueOf(productDb.get(0).getAttributeSetId()));
		productIDX2.setType(productDb.get(0).getTypeId());
		productIDX2.setStockStatus(Long.valueOf(productDb.get(0).getStockStatus()));
		productIDX2.setStockId(Long.valueOf(productDb.get(0).getStockId()));
		productIDX2.setWebsiteId(productDb.get(0).getWebsiteId());
		productIDX2.setManageStock(Long.valueOf(productDb.get(0).getManageStock()));
		productIDX2.setMinSaleQty(productDb.get(0).getMinSaleQty());
		productIDX2.setMaxSaleQty(productDb.get(0).getMaxQty());
		productIDX2.setIsQtyDecimal(Long.valueOf(productDb.get(0).getIsQtyDecimal()));
		productIDX2.setNotifyStockQty(0.0);
		productIDX2.setEnableQtyIncrements(Long.valueOf(productDb.get(0).getEnableQtyIncrements()));
		productIDX2.setIsInStock(productDb.get(0).getIsInStock());
		productIDX2.setQty(productDb.get(0).getQty());
		productIDX2.setAttributeCode(productDb.get(0).getAttributeCode());
		productIDX2.setType(productDb.get(0).getTypeId());
		productIDX2.setCreatedDate(productDb.get(0).getCreatedDate());
		productIDX2.setUpdatedDate(productDb.get(0).getUpdatedDate());
		productIDX2.setSku(productDb.get(0).getSku());
		productIDX2.setTenantId(tenantId);
		productIDX2.setVisibility(4);
		productIDX2.setFeatured(Long.valueOf(0));
		productIDX2.setPosHide(Long.valueOf(0));
		productIDX2.setHideinShop(Long.valueOf(1));
		productIDX2.setPosPrice(0.0);
		productIDX2.setSpecialPrice(0.0);
		productIDX2.setUnits("Piece");
		productIDX2.setSupplierId(0);

		for (ProductDb spec : productDb) {
			if (spec.getAttributeCode().equalsIgnoreCase("FAQ")) {
				String finalResult = null;
				try {
					finalResult = setValueFaq(spec.getValue(), faq);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				customValues.put("faq", finalResult);

			} else if (spec.getAttributeCode().equalsIgnoreCase("FactBox")) {
				String finalResult = null;
				try {
					finalResult = setValueFactBox(spec.getValue(), FactBox);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				customValues.put("FactBox", finalResult);

			} else if (spec.getAttributeCode().equalsIgnoreCase("CommonSideEffects")) {

				customValues.put("Commonsideeffects", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("SIDEEFFECTS")) {

				customValues.put("SIDEEFFECTS", spec.getValue());
			}

			else if (spec.getAttributeCode().equalsIgnoreCase("SafetyAdvice")) {
				String finalResult = null;
				try {
					finalResult = setValueSafetyAdvice(spec.getValue(), SafetyAdvice, customValues);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				customValues.put("SafetyAdvice", finalResult);

			} else if (spec.getAttributeCode().equalsIgnoreCase("ProductInformation")) {

				customValues.put("ProductInformation", spec.getValue());
			}

			else if (spec.getAttributeCode().equalsIgnoreCase("UsesOf")) {

				customValues.put("UsesOf", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("Benefits")) {

				customValues.put("Benefits", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("HOWTOUSE")) {

				customValues.put("HOWTOUSE", spec.getValue());
			}

			else if (spec.getAttributeCode().equalsIgnoreCase("HowWorks")) {

				customValues.put("HowWorks", spec.getValue());

			} else if (spec.getAttributeCode().equalsIgnoreCase("Ifmiss")) {

				customValues.put("Ifmiss", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("MANUFACTURER")) {

				customValues.put("MANUFACTURER", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("SALTCOMPOSITION")) {

				customValues.put("SALTCOMPOSITION", spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("STORAGES")) {

				customValues.put("STORAGES", spec.getValue());
			}

			else if (spec.getIsUsedInSite() == 1) {
				customValues.put(spec.getLabel(), spec.getValue());
			} else if (spec.getFrontendInput().equalsIgnoreCase("multiselect") && spec.getIsCustomized() == 1) {
				customValues.put(spec.getLabel(), spec.getValue());
			}

			if (spec.getAttributeCode().equalsIgnoreCase("meta_keyword")) {
				productIDX2.setKeyword(spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("meta_keyword")) {
				productIDX2.setKeyword(spec.getValue());
			} else if (spec.getAttributeCode().equalsIgnoreCase("description")) {
				productIDX2.setDescription(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("description")) {
				productIDX2.setDescription(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("short_description")) {
				productIDX2.setShortDescription(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("name")) {
				productIDX2.setName(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("price")) {
				values.put(spec.getLabel(), spec.getValue().trim());
				productIDX2.setValues(values);
				productIDX2.setPrice(Double.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("weight")) {
				productIDX2.setWeight(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("tax_class_id")) {
				productIDX2.setTaxClassId(Long.valueOf(spec.getValue().trim()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("image")) {
				productIDX2.setProductImageUrl(spec.getValue().trim());
			} else if (spec.getAttributeCode().equalsIgnoreCase("special_price")) {
				if (spec.getValue() != null && !spec.getValue().isEmpty()) {
					productIDX2.setSpecialPrice(Double.valueOf(spec.getValue().trim()));
				} else {
					productIDX2.setSpecialPrice(0.0);
				}
			} else if (spec.getAttributeCode().equalsIgnoreCase("visibility")) {
				productIDX2.setVisibility(Long.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("hideInPOS")) {
				productIDX2.setPosHide(Long.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("hideInShop")) {
				productIDX2.setHideinShop(Long.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("posPrice")) {
				productIDX2.setPosPrice(Double.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("posSpecialPrice")) {
				productIDX2.setPosSpecialPrice(Double.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("featured")) {
				productIDX2.setFeatured(Long.valueOf(spec.getValue()));
			} else if (spec.getAttributeCode().equalsIgnoreCase("units")) {
				productIDX2.setUnits(String.valueOf(spec.getValue()));
			}

			if (spec.getIsFilterable() == 1 && spec.getValue() != null) {

				if (spec.getFrontendInput().equalsIgnoreCase("boolean")) {

					if (spec.getValue().equalsIgnoreCase("1")) {

						values.put(spec.getLabel(), "YES");
					} else {
						values.put(spec.getLabel(), "NO");
					}
				} else if (spec.getFrontendInput().equalsIgnoreCase("multiselect")) {
					String value = getValueForOptionId(spec.getValue());
					if (value != null) {
						values.put(spec.getLabel(), value.trim());
					}

				} else {
					values.put(spec.getLabel(), spec.getValue().trim());
				}
				productIDX2.setValues(values);
			}

			if (spec.getGlobal() == 1 && spec.getIsConfigurable() == 1 && spec.getValue() != null
					&& productDb.get(0).getTypeId().equalsIgnoreCase("simple")) {
				configMap.put(spec.getLabel(), spec.getValue().trim());

				productIDX2.setConfigurableProductMap(configMap);

			}

			if (spec.getAttributeCode().equalsIgnoreCase("isCustomized")) {

				productIDX2.setIsCustomized(Long.valueOf(spec.getValue()));
			}

			if (spec.getAttributeCode().equalsIgnoreCase("supplier")) {

				productIDX2.setSupplierId(Long.valueOf(spec.getValue()));
			}

			if (spec.getIsAdditional() == 1) {

				if (spec.getFrontendInput().equalsIgnoreCase("date")) {

					String[] dateArray = spec.getValue().split(" ");

					String[] formatDate = dateArray[0].split("-");

					String date = formatDate[2] + "-" + formatDate[1] + "-" + formatDate[0];

					additionalValues.put(spec.getLabel(), date);
				} else if (spec.getFrontendInput().equalsIgnoreCase("boolean")) {

					if (spec.getValue().equalsIgnoreCase("1")) {

						additionalValues.put(spec.getLabel(), "YES");
					} else {
						additionalValues.put(spec.getLabel(), "NO");
					}
				}

				else if (spec.getFrontendInput().equalsIgnoreCase("multiselect")) {
					String value = getValueForOptionId(spec.getValue().trim());
					if (value != null) {
						additionalValues.put(spec.getLabel(), value.trim());
					}

				} else {
					additionalValues.put(spec.getLabel(), spec.getValue().trim());
				}

				productIDX2.setAdditionalSpec(additionalValues);
			}

		}
		productIDX2.setConfigurableProductActive(0);
		if (productIDX2.getType().equalsIgnoreCase("Configurable")) {
			productIDX2.setConfigurableProductActive(1);
		}

		productIDX2
				.setAttributeSetName(setAttributeSetName(productDb.get(0).getAttributeSetId(), productIDX2, tenantId));

		List<String> images = getImageByEntity(productIDX2.getEntityId().intValue());
		productIDX2.setImages(images);
		computeFields(productIDX2);
		customValues = calculateTaxClass(productIDX2, customValues);

		productIDX2.setIsCustomizedValue(customValues);
		// setConfigurableProductList(productIDX2);
		// productIDX2.setConfigProduct(configProduct);
		toBeSaved.add(productIDX2);
	}

	private String setValueFaq(String value, Map<String, String> faq) throws JsonProcessingException {

		String[] initValue = value.split("\\|");

		for (String secondValue : initValue) {

			String[] secondValueArray = secondValue.split(":::");

			if (secondValueArray.length > 0) {

				faq.put(secondValueArray[0], secondValueArray[1]);
			}
		}

		return new ObjectMapper().writeValueAsString(faq);
	}

	private String setValueFactBox(String value, Map<String, String> FactBox) throws JsonProcessingException {

		String[] initValue = value.split("\\|");

		for (String secondValue : initValue) {

			String[] secondValueArray = secondValue.split("::");

			if (secondValueArray.length > 0) {

				FactBox.put(secondValueArray[0], secondValueArray[1]);
			}
		}

		return new ObjectMapper().writeValueAsString(FactBox);
	}

	private String setValueSafetyAdvice(String value, Map<String, String> safetyAdvice,
			Map<String, String> customValues) throws JsonProcessingException {

		String[] initValue = value.split("-");

		int i = 0;
		String key = null;

		List<String> wordList = Arrays.asList(initValue);

		for (String secondValue : wordList) {

			if (secondValue.length() == 0) {

			} else {

				if (i % 2 != 0) {
					safetyAdvice.put(key, secondValue);
					key = null;

				} else {

					String[] finalString = secondValue.split(":");
					key = finalString[0];
					System.out.println(finalString.length);
					if (finalString.length == 2) {
						value = finalString[1];
						customValues.put(key + "interaction", value);
					}

				}
				i++;

			}

		}

		return new ObjectMapper().writeValueAsString(safetyAdvice);
	}

	private String getValueForOptionId(String value) {
		String finalValue = null;
		if (value.length() > 0) {
			List<Long> optionIdlist = new ArrayList<Long>();

			try {
				optionIdlist = Arrays.asList(value.split(",")).stream().map(s -> Long.parseLong(s.trim()))
						.collect(Collectors.toList());
			} catch (Exception e) {
				System.out.println(e);
				return finalValue;
			}

			List<EavAttributeOptionValue> eavAttributeOptionValue = eavAttributeOptionValueRepository
					.findByCorrespondingValueById(optionIdlist);

			int i = 0;

			for (EavAttributeOptionValue var : eavAttributeOptionValue) {
				if (i == 0) {
					finalValue = var.getValue();
				} else {
					finalValue = finalValue + "," + var.getValue();
				}
				i++;
			}

		}
		return finalValue;
	}

	private Map<String, String> calculateTaxClass(ProductIDX2 productIDX2, Map<String, String> customValues) {
		Pageable pageable = PageRequest.of(0, 1);
		Page<TaxCalculationRate> taxRate = taxRateJpaRepository.findTaxPercent(productIDX2.getTaxClassId(), pageable);
		boolean conditon = taxRate.isEmpty();

		Double taxAmount = 0.0;
		Double baseTaxAmount = 0.0;
		if (conditon) {
			productIDX2.setTaxClassId(productIDX2.getTaxClassId());
			productIDX2.setCalculateSubtotalOnly(0);
			productIDX2.setTax(0);
			customValues.put("cgst", "0.0");
			customValues.put("sgst", "0.0");
			customValues.put("igst", "0.0");
			customValues.put("calculateSubtotalOnly", "0");
			customValues.put("priceWithoutTaxamount", String.valueOf(productIDX2.getPrice()));
			customValues.put("taxAmount", String.valueOf(0.0));
			customValues.put("taxDefaultValue", String.valueOf(0));
		} else {
			productIDX2.setTaxClassId(productIDX2.getTaxClassId());
			productIDX2.setCalculateSubtotalOnly(taxRate.getContent().get(0).getCalculateSubtotal());
			productIDX2.setTax(taxRate.getContent().get(0).getRate());
			customValues.put("cgst", String.valueOf(taxRate.getContent().get(0).getCgst()));
			customValues.put("sgst", String.valueOf(taxRate.getContent().get(0).getSgst()));
			customValues.put("igst", String.valueOf(taxRate.getContent().get(0).getIgst()));
			customValues.put("priceWithoutTaxamount", String.valueOf(productIDX2.getPrice()));

			customValues.put("taxAmount", String.valueOf(0.0));
			customValues.put("taxDefaultValue", String.valueOf(0));
			if (productIDX2.getCalculateSubtotalOnly() == 1) {

				double specialPrice;
				double price;

				specialPrice = productIDX2.getSpecialPrice() * productIDX2.getTax() / 100;
				price = productIDX2.getPrice() * productIDX2.getTax() / 100;

				String pricess = productIDX2.getValues().get("Price");

				productIDX2.setCalculateSubtotalOnly(1);

				productIDX2.getValues().put("Price", String.valueOf(productIDX2.getPrice() + price));

				if (productIDX2.getSpecialPrice() != 0) {
					productIDX2.setSpecialPrice(productIDX2.getSpecialPrice() + specialPrice);
				}

				productIDX2.setPrice(productIDX2.getPrice() + price);

				double finalResult = productIDX2.getPrice() - price;

				double finalResultSpecialPrice = productIDX2.getSpecialPrice() - specialPrice;

				customValues.put("priceWithoutTaxamount", String.valueOf(finalResult));
				customValues.put("taxAmount", String.valueOf(price));
				customValues.put("taxDefaultValue", String.valueOf(1));
				customValues.put("specialPriceWithoutTaxamount", String.valueOf(finalResultSpecialPrice));
				customValues.put("taxAmountSpecialPrice", String.valueOf(specialPrice));

			} else {

				taxAmount = TaxUtils.getTaxAmout(productIDX2.getPrice(), taxRate.getContent().get(0).getRate(), 0, 0,
						taxRate.getContent().get(0).getRate());

				baseTaxAmount = TaxUtils.getTaxAmout(productIDX2.getSpecialPrice(),
						taxRate.getContent().get(0).getRate(), 0, 0, taxRate.getContent().get(0).getRate());
				productIDX2.setCalculateSubtotalOnly(0);

				double finalResult = productIDX2.getPrice() - taxAmount;

				double finalResultSpecialPrice = productIDX2.getSpecialPrice() - baseTaxAmount;

				customValues.put("priceWithoutTaxamount", String.valueOf(finalResult));
				customValues.put("taxAmount", String.valueOf(taxAmount));
				customValues.put("taxDefaultValue", String.valueOf(1));
				customValues.put("specialPriceWithoutTaxamount", String.valueOf(finalResultSpecialPrice));
				customValues.put("taxAmountSpecialPrice", String.valueOf(taxAmount));

			}
			productIDX2.setIsCustomizedValue(customValues);
		}

		return customValues;

	}

	public List<String> getImageByEntity(int entityId) {
		return templateJpaRepository.getImageByEntity(entityId);
	}

	public void computeFields(ProductIDX2 productIDX2) {
		double specialPrice;
		double discountPercentage;
		double emi;
		if (productIDX2.getType().equalsIgnoreCase("bundle")) {
			specialPrice = productIDX2.getPrice() * productIDX2.getSpecialPrice() / 100;
			productIDX2.setSpecialPrice(specialPrice);
		} else {
			specialPrice = productIDX2.getSpecialPrice();
			productIDX2.setSpecialPrice(specialPrice);
		}
		discountPercentage = ((productIDX2.getPrice() - productIDX2.getSpecialPrice()) / productIDX2.getPrice()) * 100;
		productIDX2.setDiscountPercentage(discountPercentage);

		emi = productIDX2.getSpecialPrice() / 12;
		productIDX2.setEmi(emi);
	}

	public void setCalculateAvgRating(ProductIDX2 productIDX2) {
		RatingOptionVote ratingOptionVote = ratingOptionVoteJpaRepository
				.findByRatingOptionVoteEntityPkValue(Long.valueOf(productIDX2.getEntityId()), 1);
		if (ratingOptionVote != null) {
			productIDX2.setCalculateAvgRating(ratingOptionVote.getCount());
			productIDX2.setAverageRating(ratingOptionVote.getAverageRating());
		} else {
			productIDX2.setCalculateAvgRating(0);
			productIDX2.setAverageRating(0);
		}
	}

	public void setCategoryId(long productId, ProductIDX2 productIDX2) {

		List<Long> categoryIds = catalogCategoryProductRepository.findByCategoryIdWithProductId(productId);

		List<CategoryIDX> categoryNames = categoryIdxV2Repository.findByCategoryIdIn(categoryIds);

		List<String> categoryName = new ArrayList<String>();
		categoryNames.stream().forEachOrdered(category -> {
			categoryName.add(category.getCategoryName());

		});
		productIDX2.setCategoryNames(categoryName);
		productIDX2.setCategoryId(categoryIds);

	}

	private String setAttributeSetName(int attributeSetId, ProductIDX2 productIDX2, String tenantId) {

		EavAttributeSet set = eavAttributeSetJpaRepository.fetchAttributeSetName(Long.valueOf(attributeSetId),
				tenantId);

		if (set != null) {
			return set.getAttributeSetName();
		} else {
			return "Default";
		}

	}

	public void setConfigurableProductList(ProductIDX2 productIDX2, double price, double specialPrice) {

		List<CatalogProductSuperLink> catalogProductSuperLink = catalogProductSuperLinkRepository
				.findByParentId(productIDX2.getEntityId());
		Map<String, String> configurableProductDropdown = new HashMap<>();

		Map<String, String> customValues = new HashMap<>();

		customValues.putAll(productIDX2.getIsCustomizedValue());

		Map<String, String> configProduct = new HashMap<>();

		int[] idx = { 0 };

		double[] priceList = { 0.0, 0.0 };

		List<ConfigProdJson> jsonList = new ArrayList<ConfigProdJson>();

		catalogProductSuperLink.stream().forEachOrdered(superLinkObject -> {

			List<ProductIDX2> productIDX2List = productIDx2Repository.findByEntityId(superLinkObject.getProductId());

			if (!productIDX2List.isEmpty()) {
				ProductIDX2 productIDX2Object = productIDX2List.get(0);

				if (productIDX2Object.getConfigurableProductMap() != null) {

					if (productIDX2Object.getConfigurableProductMap().size() > 0) {

						if (idx[0] == 0) {
							priceList[0] = productIDX2Object.getPrice();
							priceList[1] = productIDX2Object.getSpecialPrice();
						}

						if (productIDX2Object.getConfigurableProductMap().size() == 1) {
							productIDX2Object.getConfigurableProductMap().forEach((key, value) -> {
								boolean keyExist = checkIfKeyExist(key, superLinkObject.getParentId());
								if (keyExist) {
									boolean mapExist = false;
									try {
										if (configurableProductDropdown.size() == 0) {
											mapExist = true;
										} else {
											mapExist = false;
										}

									} catch (Exception e) {
										mapExist = true;
									}

									if (mapExist) {
										int configAttributeType = 1;

										EavAttribute eavAttribute = eavAttributeJpaRepository
												.findByFrontendLabelAndTenantId(key, productIDX2.getTenantId());

										if (eavAttribute.getConfigType() == 0) {
											configAttributeType = 1;
										} else {
											configAttributeType = eavAttribute.getConfigType();
										}
										configurableProductDropdown.put("configAttributeType",
												String.valueOf(configAttributeType));
										configurableProductDropdown.put(key,
												value + "#" + productIDX2Object.getEntityId());

									} else {

										String totalValue = configurableProductDropdown.get(key);

										totalValue = totalValue + "," + value + "#" + productIDX2Object.getEntityId();

										configurableProductDropdown.put(key, totalValue);

									}

								}

							});
						} else if (productIDX2Object.getConfigurableProductMap().size() == 2) {

							int[] idx1 = { 0 };

							int size = productIDX2Object.getConfigurableProductMap().size() - 1;

							ConfigProdJson json = new ConfigProdJson();
							List<HashMap<String, Object>> recursuive = new ArrayList<HashMap<String, Object>>();

							List<String> keyList = productIDX2Object.getConfigurableProductMap().keySet().stream()
									.collect(Collectors.toList());

							List<EavAttribute> eavAttribute1 = eavAttributeJpaRepository
									.findAttributeByPosition(keyList, productIDX2.getTenantId());

							for (EavAttribute entry : eavAttribute1) {
								String key = entry.getFrontendLabel();
								String value = productIDX2Object.getConfigurableProductMap().get(key);
								int configAttributeType = 1;

								EavAttribute eavAttribute = eavAttributeJpaRepository
										.findByFrontendLabelAndTenantId(key, productIDX2.getTenantId());

								if (eavAttribute.getConfigType() == 0) {
									configAttributeType = 1;
								} else {
									configAttributeType = eavAttribute.getConfigType();
								}

								if (idx1[0] == 0) {
									json = new ConfigProdJson();
								}
								boolean listIndexZero = true;

								if (!jsonList.isEmpty() && idx1[0] == 0) {
									ConfigProdJson json1 = new ConfigProdJson();
									json1 = containsName(jsonList, key, value);

									if (json1 != null) {
										json = json1;
										recursuive = json.getRecursuive();
										listIndexZero = false;
									}

								}

								if (idx1[0] == 0 && listIndexZero) {
									json.setKey(key);
									json.setProductId(productIDX2Object.getEntityId());
									json.setValue(value);
									json.setConfigAttributeType(configAttributeType);
								} else if (idx1[0] > 0) {

									setRecursiveValue(json, key, value, productIDX2Object.getEntityId(), recursuive,
											configAttributeType, productIDX2.getTenantId());

								}

								if (size == idx1[0]) {
									jsonList.add(json);
								}
								idx1[0]++;
							}
						}

						String value = null;
						try {
							value = objectMapper.writeValueAsString(productIDX2Object);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

						if (value != null) {
							configProduct.put(String.valueOf(superLinkObject.getProductId()), value);

						}

					}
				}
			}

			idx[0]++;

		});

		System.out.println(jsonList);

		if (!jsonList.isEmpty()) {

			String value = null;
			try {
				value = objectMapper.writeValueAsString(jsonList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			if (value != null) {

				System.out.println(String.valueOf(value));
				configurableProductDropdown.put("new", String.valueOf(value));

			}

		}

		if (!catalogProductSuperLink.isEmpty())

		{

			productIDX2.setMaintainStockIndConfProduct(catalogProductSuperLink.get(0).getManageStockSeperately());
			productIDX2.setMaintainImageIndConfProduct(catalogProductSuperLink.get(0).getManageImageSeperately());
			productIDX2.setMaintainPriceIndConfProduct(catalogProductSuperLink.get(0).getManagePriceSeperately());
			customValues.put("manageDescriptionSeperately",
					String.valueOf(catalogProductSuperLink.get(0).getManageDescriptionSeperately()));
			customValues.put("manageAdditionalInformationSeperately",
					String.valueOf(catalogProductSuperLink.get(0).getManageAdditionalInformationSeperately()));
//			if (catalogProductSuperLink.get(0).getManageImageSeperately() == 1) {
//				productIDX2.setPrice(priceList[0]);
//				productIDX2.setSpecialPrice(priceList[1]);
//			}
		}
		productIDX2.setIsCustomizedValue(customValues);

		productIDX2.setConfigurableProductDropdown(configurableProductDropdown);
		productIDX2.setConfigProduct(configProduct);

	}

	private void setRecursiveValue(ConfigProdJson json, String key, String value, Long productId,
			List<HashMap<String, Object>> recursuive, int configAttributeType, String tenantId) {

		EavAttribute eavAttribute = eavAttributeJpaRepository.findByFrontendLabelAndTenantId(key, tenantId);

		List<HashMap<String, Object>> emptyList = new ArrayList<HashMap<String, Object>>();

		if (eavAttribute != null) {

			if (eavAttribute.getConfigType() == 0) {
				configAttributeType = 1;
			} else {
				configAttributeType = eavAttribute.getConfigType();
			}

		}

		HashMap<String, Object> recursiveJson = new HashMap<String, Object>();

		recursiveJson.put("value", value);
		recursiveJson.put("key", key);
		recursiveJson.put("productId", productId);
		recursiveJson.put("recursuive", emptyList);
		recursiveJson.put("configAttributeType", configAttributeType);
		recursuive.add(recursiveJson);
		json.setRecursuive(recursuive);

	}

	private boolean checkIfKeyExist(String key, long productId) {
		System.out.println("s");
		List<CatalogProductSuperAttribute> catalogProductSuperAttribute = catalogProductSuperAttributeRepository
				.findByProductIdAndCatalogProductSuperAttributeLabelValue(productId, key);
		if (catalogProductSuperAttribute.isEmpty()) {
			return false;
		} else {

			return true;
		}

	}

	private ConfigProdJson containsName(List<ConfigProdJson> jsonList, String key, String value) {

		for (ConfigProdJson item : jsonList) {

			if (item.getKey().equalsIgnoreCase(key) && item.getValue().equalsIgnoreCase(value)) {
				jsonList.remove(item);
				return item;
			}
		}

		return null;

	}

}
