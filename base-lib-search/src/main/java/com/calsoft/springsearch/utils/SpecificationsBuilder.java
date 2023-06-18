package com.calsoft.springsearch.utils;

import com.calsoft.springsearch.utils.CriteriaParser;
import com.calsoft.springsearch.utils.NullSpecification;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

public final class SpecificationsBuilder {
	private Specification specs = (Specification) (new NullSpecification());
	private final CriteriaParser parser = new CriteriaParser();

	@NotNull
	public final SpecificationsBuilder withSearch(@NotNull String search) {
		Intrinsics.checkParameterIsNotNull(search, "search");
		this.specs = this.parser.parse(search);
		return this;
	}

	@NotNull
	public final Specification build() {
		return this.specs;
	}
}