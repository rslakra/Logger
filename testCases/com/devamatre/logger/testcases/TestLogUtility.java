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

import java.io.IOException;

import com.devamatre.logger.LogLevel;
import com.devamatre.logger.LogManager;
import com.devamatre.logger.LogUtility;
import com.devamatre.logger.Logger;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2010-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestLogUtility {

	/** logger */
	private static Logger logger = LogManager.getLogger(TestLogUtility.class);

	/**
	 * Starting Point.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		LogUtility.setDebugEnabled(true);
		LogUtility.printSystemProperties(false);
		LogUtility.debug("Rohtash Singh Lakra");
		LogUtility.printSystemProperties("System Properties", true);

		LogManager.configure(LogManager.LOG4J_XML_FILE);
		TestLogUtility testLogUtility = new TestLogUtility();
		testLogUtility.test();
	}

	/**
	 * 
	 */
	public void test() {
		logger.info("+test()");
		String[] myName = { "Rohtash", "Singh", "Lakra" };
		logger.debug(myName);
		String[][] names = { myName, { "Harsh", "Lakra" } };
		logger.debug(names);
		logger.debug(LogLevel.DEBUG);
		logger.info(LogLevel.INFO);
		logger.warn(LogLevel.WARN);
		logger.error(LogLevel.ERROR);
		logger.fatal(LogLevel.FATAL);
		logger.info("-test()");
	}

}
