package com.library.libraryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
	private boolean status; 
	private String message; 
	private Object data;
}
