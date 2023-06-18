package com.calsoft.pos.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {

	private static SimpleDateFormat formatter = null;

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public CustomDateSerializer() {
		this(null);
	}

	public CustomDateSerializer(Class t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		if (formatter == null) {
			formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		}

		gen.writeString(formatter.format(value));
	}
}
