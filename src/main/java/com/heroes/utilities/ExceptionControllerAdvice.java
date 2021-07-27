package com.heroes.utilities;

import java.time.LocalDateTime;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.heroes.exception.HeroException;

import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice 
public class ExceptionControllerAdvice {
	
//	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	private static final Logger logger = LogManager.getLogger(ExceptionControllerAdvice.class);

	
	@Autowired 
		private Environment environment;
	
	
	@ExceptionHandler(HeroException.class)
	public ResponseEntity<ErrorInfo> heroExceptionHandler(HeroException exception){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage())); 
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	
	/*
	 * General Exception Handler
	 */
	@ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE")); 
			errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorInfo.setTimeStamp(LocalDateTime.now());
			
			return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}


