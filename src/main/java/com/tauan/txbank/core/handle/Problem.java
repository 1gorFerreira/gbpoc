package com.tauan.txbank.core.handle;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;

	private String title;

	private OffsetDateTime timestamp;

	private String detail;

	private String userMessage;

	private List<ValidationError> validationErrors;

	@Builder
	@Getter
	public static class ValidationError {

		private String field;

		private String userMessage;

	}
	
}