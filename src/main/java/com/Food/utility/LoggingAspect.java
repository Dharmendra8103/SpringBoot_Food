package com.Food.utility;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut="execution(* com.medicine.service.*Impl.*(..))", throwing="exception")
	public void logExceptionFromService(Exception exception) throws Exception{
		log(exception);
	}
	
	private void log(Exception exception) {
		Logger logger=LoggerFactory.getLogger(this.getClass());
		if(exception.getMessage()!=null) {
			logger.error(exception.getMessage());
		}else {
			logger.error(exception.getMessage(),exception);
		}
	}
}
