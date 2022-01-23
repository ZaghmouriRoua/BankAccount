package com.bank.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
	private static final Logger LOG = LoggerFactory.getLogger(LoggerUtils.class);

	
	/**
	 * 
	 * @param msg : the info message
	 */
	public static void logInfo(String msg,Object ...obj) {
		LOG.info(msg, obj);

	}

	/**
	 * 
	 * @param msg: the Error Msg
	 * @param e : the Exception
	 * @param An object 
	 */
	public static void logError(String msg, Exception e, Object ...obj){
		LOG.error(msg,obj,e);
		
	}
	
	/**
	 * 
	 * @param msg: the Error Msg
	 * @param An object 
	 */
	public static void logError(String msg, Object ...obj){
		LOG.error(msg,obj);
		
	}
	

}
