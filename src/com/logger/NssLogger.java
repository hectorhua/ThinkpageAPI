package com.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */
 
public class NssLogger {
	public enum CfgType{
		XML,
		Property
	}
	private static Logger rootLogger;
	private static Logger debugLogger;
	private static Logger infoLogger;
	private static Logger statLogger;
	private static Logger warnLogger;
	private static Logger errorLogger;
	private static Logger fatalLogger;
	private static Logger statisticslogger;
	
	public static void init(String cfgFile,CfgType cfgtype){
		switch(cfgtype){
		case XML:
			DOMConfigurator.configure(cfgFile);
			break;
		case Property:
			PropertyConfigurator.configure(cfgFile);
			break;
		default:
			break;
		}
		
		rootLogger = Logger.getRootLogger();
		debugLogger = Logger.getLogger("DEBUG_LOGGER");
		infoLogger = Logger.getLogger("INFO_LOGGER");
		statLogger = Logger.getLogger("STAT_LOGGER");
		warnLogger = Logger.getLogger("WARN_LOGGER");
		errorLogger = Logger.getLogger("ERROR_LOGGER");
		fatalLogger = Logger.getLogger("FATAL_LOGGER");
		statisticslogger = Logger.getLogger("STATISTICS_LOGGER");
		
	}
	
	public static void debug(String msg){
		if(debugLogger == null)
			debugLogger = rootLogger;
		
		debugLogger.debug(msg);
	}
	
	public static void error(String msg){
		if(errorLogger == null)
			errorLogger = rootLogger;
		errorLogger.error(msg);
	}
	
	public static void error(Object object, Throwable t){
		if(errorLogger == null)
			errorLogger = rootLogger;
	
		errorLogger.error(object, t);
	}
	
	public static void info(String msg){
		if(infoLogger == null)
			infoLogger = rootLogger;
		   infoLogger.info(msg);
	}
	public static void warn(String msg){
		if(warnLogger == null)
			warnLogger = rootLogger;
		   warnLogger.warn(msg);
	}
	
	public static void fatal(String msg){
		if(fatalLogger == null)
			fatalLogger = rootLogger;
		fatalLogger.fatal(msg);
	}
	
	public static void stat(String msg){
		if(statLogger == null)
			statLogger = rootLogger;
		
		statLogger.info(msg);
	}
	
	public static void statistics(String msg){
		if (statisticslogger == null)
			statisticslogger = rootLogger;
		statisticslogger.info(msg);
	}
	
	public static void main(String[] args){
		NssLogger.init("config/log4j.cfg", CfgType.Property);
		
		NssLogger.debug("XXXXXXXXXXXX");
		NssLogger.error("AAAAAAAAAAAAA");
		NssLogger.fatal("HHHHHHHHHHHH");
		NssLogger.info("EEEEEEEEEEEEE");
		NssLogger.stat("stat");	
	}
}
