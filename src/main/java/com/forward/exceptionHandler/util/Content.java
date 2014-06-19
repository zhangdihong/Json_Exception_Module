package com.forward.exceptionHandler.util;

public class Content {
	private String field;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	public Content(String field,String message)
	{
		this.field=field;
		this.message=message;
	}

}
