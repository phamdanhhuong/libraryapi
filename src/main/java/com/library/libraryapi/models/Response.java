package com.library.libraryapi.models;

import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Response {
	private Boolean status; 
	private String message; 
	private Object body; 
}
