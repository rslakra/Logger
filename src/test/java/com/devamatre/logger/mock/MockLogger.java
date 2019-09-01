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
package com.devamatre.logger.mock;

import com.devamatre.logger.Logger;
import com.devamatre.logger.LoggerImpl;

/**
 * The default logger manager for test cases.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2010-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class MockLogger implements Logger {

	/** logger */
	private Logger logger;

	/**
	 * 
	 * @param logClass
	 */
	public MockLogger(Class<?> logClass) {
		logger = new LoggerImpl(logClass);
	}

	/**
	 * @param object
	 * @see com.devamatre.logger.Logger#fatal(java.lang.Object)
	 */
	@Override
	public void fatal(Object object) {
		logger.fatal(object);
	}

	/**
	 * @param object
	 * @param throwable
	 * @see com.devamatre.logger.Logger#fatal(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	@Override
	public void fatal(Object object, Throwable throwable) {
		logger.fatal(object, throwable);
	}

	/**
	 * @param object
	 * @see com.devamatre.logger.Logger#error(java.lang.Object)
	 */
	@Override
	public void error(Object object) {
		logger.error(object);
	}

	/**
	 * @param object
	 * @param throwable
	 * @see com.devamatre.logger.Logger#error(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	@Override
	public void error(Object object, Throwable throwable) {
		logger.error(object, throwable);
	}

	/**
	 * @param object
	 * @see com.devamatre.logger.Logger#warn(java.lang.Object)
	 */
	@Override
	public void warn(Object object) {
		logger.warn(object);
	}

	/**
	 * @param object
	 * @param throwable
	 * @see com.devamatre.logger.Logger#warn(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	@Override
	public void warn(Object object, Throwable throwable) {
		logger.warn(object, throwable);
	}

	/**
	 * @param object
	 * @see com.devamatre.logger.Logger#info(java.lang.Object)
	 */
	@Override
	public void info(Object object) {
		logger.info(object);
	}

	/**
	 * @param object
	 * @param throwable
	 * @see com.devamatre.logger.Logger#info(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	@Override
	public void info(Object object, Throwable throwable) {
		logger.info(object, throwable);
	}

	/**
	 * @param object
	 * @see com.devamatre.logger.Logger#debug(java.lang.Object)
	 */
	@Override
	public void debug(Object object) {
		logger.debug(object);
	}

	/**
	 * @param object
	 * @param throwable
	 * @see com.devamatre.logger.Logger#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	@Override
	public void debug(Object object, Throwable throwable) {
		logger.debug(object, throwable);
	}

	/**
	 * @return
	 * @see com.devamatre.logger.Logger#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * @param debugEnabled
	 * @see com.devamatre.logger.Logger#setDebugEnabled(boolean)
	 */
	@Override
	public void setDebugEnabled(boolean debugEnabled) {
		logger.setDebugEnabled(debugEnabled);
	}

	/**
	 * @return
	 * @see com.devamatre.logger.Logger#isInfoEnabled()
	 */
	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * @return
	 * @see com.devamatre.logger.Logger#isWarnEnabled()
	 */
	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * @return
	 * @see com.devamatre.logger.Logger#isErrorEnabled()
	 */
	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * @return
	 * @see com.devamatre.logger.Logger#isFatalEnabled()
	 */
	@Override
	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}
}