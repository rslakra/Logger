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
package com.devamatre.logger.junit;

import com.devamatre.logger.LogLevel;
import com.devamatre.logger.Logger;
import com.devamatre.logger.mock.MockLogManager;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2009-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoggerTestCase extends AbstractTestCase {

	private static Logger logger = MockLogManager.getLogger(LoggerTestCase.class);

	public void testFatal() {
		logger.fatal("FATAL");
		assertTrue("Fatal Error", "FATAL".equals(LogLevel.FATAL));
	}

	public void testError() {
		logger.error("ERROR");
		assertTrue("Error", "ERROR".equals(LogLevel.ERROR));
	}

	public void testWarn() {
		logger.warn("WARN");
		assertTrue("Warn Error", "WARN".equals(LogLevel.WARN));
	}

	public void testInfo() {
		logger.info("INFO");
		assertTrue("Info Error", "INFO".equals(LogLevel.INFO));
	}

	public void testDebug() {
		logger.debug("DEBUG");
		assertTrue("Debug Error", "DEBUG".equals(LogLevel.DEBUG));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoggerTestCase ltc = new LoggerTestCase();
		ltc.testDebug();
		ltc.testError();
		ltc.testFatal();
		ltc.testInfo();
		ltc.testWarn();
	}
}