/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.logger.testcases;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.LogUtility;
import com.devamatre.logger.Logger;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2009-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestLogger {

	private static Logger logger;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// LogManager.printSystemProperties();
		// LogManager.configure(LogManager.LOG4J_XML_FILE);
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		// LogManager.configure(true, LogEnum.WARN);

		TestLogger.testLogger(true);
		TestLogger.testLogger(false);

	}

	/**
	 * 
	 * @param useNullLogger
	 */
	private static void testLogger(boolean useNullLogger) {
		LogUtility.debug("+testLogger(" + useNullLogger + ")");

		// initialize logger.
		logger = LogManager.getLogger((useNullLogger ? null : TestLogger.class));

		logger.info("+testLogger()");
		logger.info("This is My logger testing.");
		logger.warn("My Logger Warning");
		logger.debug("My logger debug testing.");

		if (logger.isDebugEnabled()) {
			logger.debug("LOGGER IS ENABLED ... :)");
		}

		messages();

		logger.error("My Logger error testing");
		logger.fatal("My Logger Fatal Testing.");

		if (logger.isDebugEnabled()) {
			logger.info("Debugging Enabled!");
		}

		messages3();

		if (logger.isInfoEnabled()) {
			logger.info("Info Enabled!");
		}

		// loggerPackageTest
		TestLoggerPackage loggerPackage = new TestLoggerPackage();
		loggerPackage.testLoggerPackageTest();

		try {
			throwException();
		} catch (Exception e) {
			logger.error("Error!!!", e);
		}

		logger.info("-testLogger()");

		LogUtility.debug("-testLogger()");
	}

	/**
	 * 
	 */
	private static void messages() {
		logger.info("+messages");
		logger.info("messages Method Call!");
		messages1();
		logger.info("-messages");
	}

	/**
	 * 
	 */
	private static void messages1() {
		logger.info("+messages1");
		logger.info("messages1 Method Call!");
		messages2();
		messages5();
		logger.info("-messages1");
	}

	/**
	 * 
	 */
	private static void messages2() {
		logger.info("+messages2");
		logger.info("messages2 Method Call!");
		logger.info("-messages2");
	}

	/**
	 * 
	 */
	private static void messages3() {
		logger.info("+messages3");
		logger.info("messages3 Method Call!");
		logger.info("-messages3");
	}

	/**
	 * 
	 */
	private static void messages4() {
		logger.info("messages4 Method Call!");
	}

	/**
	 * 
	 */
	private static void messages5() {
		logger.info("messages5 Method Call!");
		messages3();
		messages4();
	}

	/**
	 * Throws an exception.
	 * 
	 * @throws Exception
	 */
	private static void throwException() throws Exception {
		throw new IllegalArgumentException("Exception Testing!");
	}
}