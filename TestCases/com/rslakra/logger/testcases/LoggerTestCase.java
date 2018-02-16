/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
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