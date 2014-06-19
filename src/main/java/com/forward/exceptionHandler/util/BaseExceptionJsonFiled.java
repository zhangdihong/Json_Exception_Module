package com.forward.exceptionHandler.util;

public class BaseExceptionJsonFiled {
	private boolean success=false;
	private Content content;
	public BaseExceptionJsonFiled(String field,String message)
	{
		this.content=new Content(field,message);
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	
	

}
