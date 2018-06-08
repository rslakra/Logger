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
package com.devamatre.logger;

import org.apache.log4j.Level;

/**
 * The <code>Logger</code> interface defines all the generic logging methods. It
 * delegates the calls to <code>org.apache.log4j.Category</code>.
 * 
 * <pre>
 * <code>
 * Logging Levels:
 * OFF - 6, 
 * FATAL - 5, 
 * ERROR - 4, 
 * WARN - 3, 
 * INFO - 2, 
 * DEBUG - 1,
 * ALL - 0
 * </code>
 * </pre>
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2009-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Logger {
	
	/**
	 * FATAL - 5
	 * 
	 * Logs a message object with the {@link Level#FATAL FATAL} Level. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 */
	void fatal(Object object);
	
	/**
	 * Log a message object with the <code>FATAL</code> level including the
	 * stack trace of the {@link Throwable} <code>t</code> passed as parameter.
	 * It delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 * @param throwable
	 */
	void fatal(Object object, Throwable throwable);
	
	/**
	 * ERROR - 4
	 * 
	 * Logs a message object with the {@link Level#ERROR ERROR} Level. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 */
	void error(Object object);
	
	/**
	 * Log a message object with the <code>ERROR</code> level including the
	 * stack trace of the {@link Throwable} <code>t</code> passed as parameter.
	 * It delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 * @param throwable
	 */
	void error(Object object, Throwable throwable);
	
	/**
	 * WARN - 3
	 * 
	 * Logs a message object with the {@link Level#WARN WARN} Level. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 */
	void warn(Object object);
	
	/**
	 * Log a message object with the <code>WARN</code> level including the stack
	 * trace of the {@link Throwable} <code>t</code> passed as parameter. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 * @param throwable
	 */
	void warn(Object object, Throwable throwable);
	
	/**
	 * INFO - 2
	 * 
	 * Logs a message object with the {@link Level#INFO INFO} Level. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 */
	void info(Object object);
	
	/**
	 * Log a message object with the <code>INFO</code> level including the stack
	 * trace of the {@link Throwable} <code>t</code> passed as parameter. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 * @param throwable
	 */
	void info(Object object, Throwable throwable);
	
	/**
	 * DEBUG - 1
	 * 
	 * Logs a message object with the {@link Level#DEBUG DEBUG} Level. It
	 * delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 */
	void debug(Object object);
	
	/**
	 * Log a message object with the <code>DEBUG</code> level including the
	 * stack trace of the {@link Throwable} <code>t</code> passed as parameter.
	 * It delegates the calls to <code>org.apache.log4j.Category</code>.
	 * 
	 * <p>
	 * See <code>org.apache.log4j.Category</code> for more detailed information.
	 * </p>
	 * 
	 * @param object
	 * @param throwable
	 */
	void debug(Object object, Throwable throwable);
	
	/**
	 * Checks whether this category is enabled for the <code>DEBUG</code> Level.
	 * 
	 * @return
	 */
	boolean isDebugEnabled();
	
	/**
	 * The debugEnabled to be set.
	 * 
	 * @param debugEnabled
	 */
	void setDebugEnabled(boolean debugEnabled);
	
	/**
	 * Checks whether this category is enabled for the <code>INFO</code> Level.
	 * 
	 * @return
	 */
	boolean isInfoEnabled();
	
	/**
	 * Checks whether this category is enabled for the <code>WARN</code> Level.
	 * 
	 * @return
	 */
	boolean isWarnEnabled();
	
	/**
	 * Checks whether this category is enabled for the <code>ERROR</code> Level.
	 * 
	 * @return
	 */
	boolean isErrorEnabled();
	
	/**
	 * Checks whether this category is enabled for the <code>FATAL</code> Level.
	 * 
	 * @return
	 */
	boolean isFatalEnabled();
}