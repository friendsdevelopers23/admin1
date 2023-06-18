package com.calsoft.springsearch.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

@Metadata(mv = { 1, 1, 16 }, bv = { 1, 0, 3 }, k = 1, d1 = {
		"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J,\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013" }, d2 = {
				"Lcom/example/above18/SpecificationImpl;", "T", "Lorg/springframework/data/jpa/domain/Specification;",
				"criteria", "Lcom/example/above18/SearchCriteria;", "(Lcom/example/above18/SearchCriteria;)V",
				"getNestedRoot", "Ljavax/persistence/criteria/Path;", "root", "Ljavax/persistence/criteria/Root;",
				"nestedKey", "", "", "toPredicate", "Ljavax/persistence/criteria/Predicate;", "query",
				"Ljavax/persistence/criteria/CriteriaQuery;", "builder", "Ljavax/persistence/criteria/CriteriaBuilder;",
				"above18" })

public final class SpecificationImpl<T> implements Specification<T> {
	private final SearchCriteria criteria;

	@Nullable
	@Override
	public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query,
			@NotNull CriteriaBuilder builder) {
		if (criteria.getOperation() == SearchOperation.GREATER_THAN) {
			return builder.greaterThan(root.<Double>get(criteria.getKey()), Double.valueOf(criteria.getValue()));
		} else if (criteria.getOperation() == SearchOperation.LESS_THAN) {
			return builder.lessThan(root.<Double>get(criteria.getKey()), Double.valueOf(criteria.getValue()));
		} else if (criteria.getOperation() == SearchOperation.EQUALS) {
			Boolean value = "true".equalsIgnoreCase(criteria.getValue()) ? Boolean.TRUE
					: "false".equalsIgnoreCase(criteria.getValue()) ? Boolean.FALSE : null;
			if (value != null) {
				return builder.equal(root.get(criteria.getKey()), value);
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		} else if (criteria.getOperation() == SearchOperation.NOT_EQUALS) {
			return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
		} else if (criteria.getOperation() == SearchOperation.STARTS_WITH) {
			return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
		} else if (criteria.getOperation() == SearchOperation.ENDS_WITH) {
			return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
		} else if (criteria.getOperation() == SearchOperation.DOESNT_START_WITH) {
			return builder.notLike(root.get(criteria.getKey()), criteria.getValue() + "%");
		} else if (criteria.getOperation() == SearchOperation.DOESNT_END_WITH) {
			return builder.notLike(root.get(criteria.getKey()), "%" + criteria.getValue());
		} else if (criteria.getOperation() == SearchOperation.CONTAINS) {
			return builder.like(builder.lower(root.<String>get(criteria.getKey()).as(String.class)),
					"%" + criteria.getValue() + "%");
		} else if (criteria.getOperation() == SearchOperation.DOESNT_CONTAIN) {
			return builder.notLike(builder.lower(root.<String>get(criteria.getKey()).as(String.class)),
					"%" + criteria.getValue() + "%");
		} else if (criteria.getOperation() == SearchOperation.BETWEEN) {
			String min = criteria.getValue1();
			String max = criteria.getValue2();
			java.sql.Date fromDateSQL = null;
			java.sql.Date toDateSQL = null;
			java.sql.Date temp = null;
			java.sql.Date temp1 = null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try {
				java.util.Date fromDate = sdf1.parse(min);
				java.util.Date ToDate = sdf1.parse(max);
				fromDateSQL = new java.sql.Date(fromDate.getTime());
				toDateSQL = new java.sql.Date(ToDate.getTime());
				temp = toDateSQL;
				temp1 = fromDateSQL;
				if (fromDateSQL.compareTo(toDateSQL) > 0) {
					c.setTime(sdf1.parse(min));
					c.add(Calendar.DAY_OF_MONTH, 1);
					String newDate = sdf1.format(c.getTime());
					fromDateSQL = temp;
					ToDate = sdf1.parse(newDate);
					toDateSQL = new java.sql.Date(ToDate.getTime());

				} else {
					c.setTime(sdf1.parse(max));
					c.add(Calendar.DAY_OF_MONTH, 1);
					String newDate = sdf1.format(c.getTime());
					System.out.println(newDate);
					ToDate = sdf1.parse(newDate);
					toDateSQL = new java.sql.Date(ToDate.getTime());

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return builder.between(root.get(criteria.getKey()), fromDateSQL, toDateSQL);
		} else {
			return null;
		}
	}

	private final Path getNestedRoot(Root<T> root, List nestedKey) {
		ArrayList prefix = new ArrayList((Collection) nestedKey);
		prefix.remove(nestedKey.size() - 1);
		Path temp = (Path) root;

		Path var10000;
		for (Iterator var6 = prefix.iterator(); var6.hasNext(); temp = var10000) {
			String s = (String) var6.next();
			var10000 = temp.get(s);
			Intrinsics.checkExpressionValueIsNotNull(var10000, "temp.get(s)");
		}

		return temp;
	}

	public SpecificationImpl(@NotNull SearchCriteria criteria) {
		super();
		Intrinsics.checkParameterIsNotNull(criteria, "criteria");

		this.criteria = criteria;
	}
}
