package com.forward.exceptionHandler.utilController;

import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.forward.exceptionHandler.util.BaseException;
import com.forward.exceptionHandler.util.FieldErrorCompose;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public BaseException processesBaseException(BaseException bex){
		return new BaseException(bex.getMessage(),bex.getDescr());
	}
	
     @ExceptionHandler(BindException.class)
     @ResponseBody
	public FieldErrorCompose ExceptionValidationProcesses(BindException bex){
    	        BindingResult bresult= bex.getBindingResult();
    	                List<FieldError> filedErrors= bresult.getFieldErrors();
    	                
		return processesField(filedErrors);
		
    	 
	}
     private FieldErrorCompose  processesField(List<FieldError> listerrors){
    	      FieldErrorCompose fieldErrorCompose=new FieldErrorCompose();
    	      for(FieldError fieldError:listerrors){
    	    	   fieldErrorCompose.addFieldError(fieldError.getField(),fieldError.getDefaultMessage());
    	      }
    	      return fieldErrorCompose;
     }
}
