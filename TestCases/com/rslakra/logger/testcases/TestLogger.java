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
package com.rslakra.logger.testcases;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-16 08:21:25 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestLogger {

	private static Logger logger;

	public static void main(String[] args) {
		// printSystemProperties();

		// LogManager.configure();
		// LogManager.configure("E:");
		LogManager.configure(
				LogManager.getDefaultConfigPath(false) + File.separator + LogManager.DEFAULT_LOG4J_PROPERTY_FILE);
		// LogManager.configure(true, null,null,null,false,null);
		logger = LogManager.getLogger(TestLogger.class);

		logger.info("=================================================");
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

		logger.info("=================================================");
	}

	private static void messages() {
		logger.info("=================================================");
		logger.info("messages Method Call!");
		messages1();
		logger.info("=================================================");
	}

	private static void messages1() {
		logger.info("=================================================");
		logger.info("messages1 Method Call!");
		messages2();
		messages5();
		logger.info("=================================================");
	}

	private static void messages2() {
		logger.info("=================================================");
		logger.info("messages2 Method Call!");
		logger.info("=================================================");
	}

	private static void messages3() {
		logger.info("messages3 Method Call!");
	}

	private static void messages4() {
		logger.info("messages4 Method Call!");
	}

	private static void messages5() {
		logger.info("messages5 Method Call!");
		messages3();
		messages4();
	}

	/**
	 * Prints System Properties
	 */
	private static void printSystemProperties() {
		Properties prop = System.getProperties();
		Iterator itr = prop.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			System.out.println("key : " + key + ", Value : " + System.getProperty(key));
		}
	}

}
