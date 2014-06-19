package com.forward.exceptionHandler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

public class BaseExceptionHandlerResolver extends
		SimpleMappingExceptionResolver {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		Exception exception = ex;
		ModelAndView mv = new ModelAndView();
		if (ex instanceof BaseException) {
			log.debug(ex.getMessage());
		} else {
			log.error(ex.getMessage(), ex.getCause());
		}
		if (this.exceptionAttribute != null) {

			if (log.isDebugEnabled()) {
				logger.debug("Exposing Exception as model attribute '"
						+ this.exceptionAttribute + "'");
			}
			if (exception != null) {
				if (exception instanceof MissingServletRequestParameterException) {
					MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) exception;
					mv.addObject("field", 501);
					mv.addObject("message", msrpe.getParameterName());

				} else if (exception instanceof MaxUploadSizeExceededException) {
					MaxUploadSizeExceededException musee = (MaxUploadSizeExceededException) exception;
					mv.addObject("field", 501);
					mv.addObject("message", musee.getMaxUploadSize());
				} else if (exception instanceof BaseException) {
					BaseException be = (BaseException) exception;
					mv.addObject("field", 501);
					mv.addObject("message", be.getDescr());
				} else if (exception instanceof ConversionNotSupportedException) {
					ConversionNotSupportedException cnse = (ConversionNotSupportedException) exception;
					mv.addObject("field", 501);
					mv.addObject("message", "need" + cnse.getRequiredType());
				} else if (exception instanceof HttpMediaTypeNotSupportedException) {
					HttpMediaTypeNotSupportedException htnse = (HttpMediaTypeNotSupportedException) exception;
					mv.addObject("field", 415);
					mv.addObject("message", htnse.getMessage());
				} else if (exception instanceof NoSuchRequestHandlingMethodException) {
					NoSuchRequestHandlingMethodException nsrhme = (NoSuchRequestHandlingMethodException) exception;
					mv.addObject("field", "404");
					mv.addObject("message",
							"nonsupport" + nsrhme.getMethodName());
				} else if (exception instanceof TypeMismatchException) {
					TypeMismatchException tme = (TypeMismatchException) exception;
					mv.addObject("field", 501);
					mv.addObject("message", "need" + tme.getRequiredType());
				} else {
					mv.addObject("field", 500);
					mv.addObject("message", exception.getMessage());
				}

			}
		}
		return mv;

	}
}
