package com.pool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.pool.model.UserModel;
import com.pool.model.exception.ValidationResponse;

@Component
public class PresentationUtil {

	public String userIdGenerator(UserModel model) {
		StringBuilder builder = new StringBuilder("");
		builder.append(model.getFirstName().toUpperCase().charAt(0)).append(model.getLastName().toUpperCase().charAt(0))
				.append(UUID.randomUUID());
		return builder.toString().split("-")[0];
	}

	public ResponseEntity<?> validationResponseExtractorResult(BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			List<ValidationResponse> responses = new ArrayList<>();
			responses = errors.stream()
					.map(error -> new ValidationResponse(error.getField(), error.getDefaultMessage()))
					.collect(Collectors.toList());
			return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
