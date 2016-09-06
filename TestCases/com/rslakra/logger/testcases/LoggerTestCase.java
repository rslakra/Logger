/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.junit.logger;

import com.rslakra.junit.AbstractTestCase;
import com.rslakra.logger.Logger;
import com.rslakra.mock.logger.MockLogManager;

/**
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 */
public class LoggerTestCase extends AbstractTestCase {
	
	private static String[] MESSAGES = {
			"OFF",
			"FATAL",
			"ERROR",
			"WARN",
			"INFO",
			"DEBUG",
			"ALL" };
	private static Logger logger = MockLogManager.getLogger(LoggerTestCase.class);
	
	public void testFatal() {
		logger.fatal("FATAL");
		assertTrue("Fatal Error", MESSAGES[1].equals("FATAL"));
	}
	
	public void testError() {
		logger.error("ERROR");
		assertTrue("Fatal Error", MESSAGES[2].equals("ERROR"));
	}
	
	public void testWarn() {
		logger.warn("WARN");
		assertTrue("Fatal Error", MESSAGES[3].equals("WARN"));
	}
	
	public void testInfo() {
		logger.info("INFO");
		assertTrue("Fatal Error", MESSAGES[4].equals("INFO"));
	}
	
	public void testDebug() {
		logger.debug("DEBUG");
		assertTrue("Fatal Error", MESSAGES[5].equals("DEBUG"));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}