package com.forward.exceptionHandler.util;

public class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8411382662472806859L;
    
    private String descr;
	public String getDescr() {
		return descr;
	}
	public BaseException(String descr){
		super(400+"");
		this.descr=descr;
	}
	public BaseException(String message,String discr)
	{
		super(message);
		this.descr=discr;
	}
	 

}
