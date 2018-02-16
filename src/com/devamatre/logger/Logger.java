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
package com.devamatre.logger;

/**
 * Logger.java
 *
 * The <code>Logger</code> Interface defines all the generic methods.
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 9, 2009 2:52:57 PM
 */
public interface Logger {
	
	/* Default Value of Indentation. */
	int DEFAULT_INDENT = 25;
	
	/****************************
	 * Logging Levels
	 *
	 * OFF - 6
	 * FATAL - 5
	 * ERROR - 4
	 * WARN - 3
	 * INFO - 2
	 * DEBUG - 1
	 * ALL - 0
	 ****************************/
	
	/**
	 * FATAL - 5
	 * 
	 * @param object
	 */
	void fatal(Object object);
	
	/**
	 * FATAL - 5
	 * 
	 * @param object
	 * @param throwable
	 */
	void fatal(Object object, Throwable throwable);
	
	/**
	 * ERROR - 4
	 * 
	 * @param object
	 */
	void error(Object object);
	
	/**
	 * ERROR - 4
	 * 
	 * @param o
	 * @param throwable
	 */
	void error(Object o, Throwable throwable);
	
	/**
	 * WARN - 3
	 * 
	 * @param object
	 */
	void warn(Object object);
	
	/**
	 * WARN - 3
	 * 
	 * @param object
	 * @param throwable
	 */
	void warn(Object object, Throwable throwable);
	
	/**
	 * INFO - 2
	 * 
	 * @param object
	 */
	void info(Object object);
	
	/**
	 * INFO - 2
	 * 
	 * @param object
	 * @param throwable
	 */
	void info(Object object, Throwable throwable);
	
	/**
	 * DEBUG - 1
	 * 
	 * @param object
	 */
	void debug(Object object);
	
	/**
	 * DEBUG - 1
	 * 
	 * @param object
	 * @param throwable
	 */
	void debug(Object object, Throwable throwable);
	
	/**
	 * Returns true if the info is enabled otherwise false.
	 * 
	 * @return
	 */
	boolean isInfoEnabled();
	
	/**
	 * Returns true if the debug is enabled otherwise false.
	 * 
	 * @return
	 */
	boolean isDebugEnabled();
}