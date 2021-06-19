package com.pool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pool.model.OktaResponse;

public class JsonConverter<T> {

	private static JsonConverter jsonConverter;
	private static ObjectMapper objectMapper = null;

	private JsonConverter() {

	}

	public static JsonConverter<Object> getSingletonLazyInstance() {
		if (null == jsonConverter) {
			jsonConverter = new JsonConverter();
			objectMapper = new ObjectMapper();
			System.out.println(objectMapper.hashCode());
		}
		return jsonConverter;
	}

	public<T> T convertToJavaObject(String input, Class clazz) {
		T jsonResponse = null;
		try {
			jsonResponse = (T) objectMapper.readValue(input, clazz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}


}
