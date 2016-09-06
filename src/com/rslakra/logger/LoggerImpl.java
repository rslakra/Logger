/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.logger;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;

/**
 *
 * LoggerImpl.java
 *
 * The <code>LoggerImpl</code> class is the implementation of the Logger
 * interface.
 * It has the definition of all logger methods like warn, debug etc.
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 9, 2009 2:51:50 PM
 */
public class LoggerImpl implements Logger {
	
	/* The Root logger. */
	private Category logger;
	
	/* Maintains gaps among log statements. */
	private String[] indents;
	
	/**
	 * Parameterized Constructor.
	 *
	 * @param logger parent logger.
	 */
	public LoggerImpl(Category logger) {
		this.logger = logger;
		indents = new String[DEFAULT_INDENT];
		// Initialize indents with default;
		// StringBuffer sBuffer = new StringBuffer();
		// for (int i = 0; i < indents.length; i++) {
		// sBuffer.append(" ");
		// indents[i] = sBuffer.toString();
		// //TODO:uncomment it after implementing indentation.
		//// sBuffer.append(" ");
		// }
	}
	
	/**
	 * This method returns the string after indentation upto the passed depth.
	 *
	 * @param depth
	 * @return string after indentation.
	 */
	private String indents(int depth) {
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < depth; i++) {
			sBuffer.append(" ");
		}
		return sBuffer.toString();
	}
	
	/**
	 * This method indents.
	 * 
	 * @param object
	 * @return object to be logged.
	 */
	private Object getIndents(Object object) {
		return getIndents(object, true);
	}
	
	/**
	 * This method indents.
	 * 
	 * @param object
	 * @param indent
	 * @return object to be logged.
	 */
	private Object getIndents(Object object, boolean indent) {
		// System.out.println("getIndents(" + object + ", " + indent + ")!");
		if(!indent) {
			return object;
		}
		int depth = new Throwable().getStackTrace().length;
		// System.out.println("depth : " + depth);
		depth -= 2; // adjust for this method
		// depth = (depth < DEFAULT_INDENT ? depth : DEFAULT_INDENT - 1);
		// System.out.println("indents.length : " + indents.length);
		return (indents[depth] == null) ? object : (indents[depth] + object);
		// int depth = new Throwable().getStackTrace().length;
		// return indents(depth) + object;
	}
	
	/**
	 * @see com.rslakra.logger.Logger#fatal(java.lang.Object)
	 */
	public void fatal(Object object) {
		logger.fatal(getIndents(object));
	}
	
	/**
	 * @see com.rslakra.logger.Logger#fatal(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void fatal(Object object, Throwable throwable) {
		logger.fatal(getIndents(object), throwable);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#error(java.lang.Object)
	 */
	public void error(Object object) {
		logger.error(getIndents(object));
	}
	
	/**
	 * @see com.rslakra.logger.Logger#error(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void error(Object object, Throwable throwable) {
		logger.error(getIndents(object), throwable);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#warn(java.lang.Object)
	 */
	public void warn(Object object) {
		logger.warn(getIndents(object));
	}
	
	/**
	 * @see com.rslakra.logger.Logger#warn(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void warn(Object object, Throwable throwable) {
		logger.warn(getIndents(object), throwable);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#info(java.lang.Object)
	 */
	public void info(Object object) {
		logger.info(getIndents(object));
	}
	
	/**
	 * @see com.rslakra.logger.Logger#info(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void info(Object object, Throwable throwable) {
		logger.info(getIndents(object), throwable);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#debug(java.lang.Object)
	 */
	public void debug(Object object) {
		logger.debug(getIndents(object));
	}
	
	/**
	 * @see com.rslakra.logger.Logger#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void debug(Object object, Throwable throwable) {
		logger.debug(getIndents(object), throwable);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return logger.isEnabledFor(Priority.INFO);
	}
	
	/**
	 * @see com.rslakra.logger.Logger#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
}