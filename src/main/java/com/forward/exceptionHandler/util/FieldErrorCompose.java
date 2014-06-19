package com.forward.exceptionHandler.util;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorCompose {
	private boolean success=false;
	private Content content; 
	private List<FieldError> FieldErrors=new ArrayList<FieldError>();
	public void addFieldError(String field,String message){
		FieldError fieldError=new FieldError(field, message);
		FieldErrors.add(fieldError);
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<FieldError> getFieldErrors() {
		return FieldErrors;
	}
	public void setFieldErrors(List<FieldError> fieldErrors) {
		FieldErrors = fieldErrors;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}

}
