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

import com.devamatre.logger.log4j.Log4JLoggerImpl;

/**
 * The default logger manager for test cases.
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2010-08-09 2:51:50 PM
 * @since 1.0.0
 */
public class MockLogger implements Logger {

    /**
     * logger
     */
    private Logger logDelegator;

    /**
     * @param logClass
     */
    public MockLogger(Class<?> logClass) {
        logDelegator = new Log4JLoggerImpl(logClass);
    }

    /**
     * Returns the supported <code>LogBinderType</code>.
     *
     * @return
     */
    @Override
    public LogBinderType getBinderType() {
        return LogBinderType.LOG4J;
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object)
     */
    @Override
    public void fatal(Object object) {
        logDelegator.fatal(object);
    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void fatal(Object object, Throwable throwable) {
        logDelegator.fatal(object, throwable);
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(String format, Object... arguments) {
        logDelegator.fatal(format, arguments);
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(Throwable throwable, String format, Object... arguments) {
        logDelegator.fatal(throwable, format, arguments);
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#error(java.lang.Object)
     */
    @Override
    public void error(Object object) {
        logDelegator.error(object);
    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#error(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void error(Object object, Throwable throwable) {
        logDelegator.error(object, throwable);
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void error(String format, Object... arguments) {
        logDelegator.error(format, arguments);
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void error(Throwable throwable, String format, Object... arguments) {
        logDelegator.error(throwable, format, arguments);
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#warn(java.lang.Object)
     */
    @Override
    public void warn(Object object) {
        logDelegator.warn(object);
    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#warn(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void warn(Object object, Throwable throwable) {
        logDelegator.warn(object, throwable);
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void warn(String format, Object... arguments) {
        logDelegator.warn(format, arguments);
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void warn(Throwable throwable, String format, Object... arguments) {
        logDelegator.warn(throwable, format, arguments);
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#info(java.lang.Object)
     */
    @Override
    public void info(Object object) {
        logDelegator.info(object);
    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#info(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void info(Object object, Throwable throwable) {
        logDelegator.info(object, throwable);
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void info(String format, Object... arguments) {
        logDelegator.info(format, arguments);
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void info(Throwable throwable, String format, Object... arguments) {
        logDelegator.info(throwable, format, arguments);
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#debug(java.lang.Object)
     */
    @Override
    public void debug(Object object) {
        logDelegator.debug(object);
    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#debug(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void debug(Object object, Throwable throwable) {
        logDelegator.debug(object, throwable);
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void debug(String format, Object... arguments) {
        logDelegator.debug(format, arguments);
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void debug(Throwable throwable, String format, Object... arguments) {
        logDelegator.debug(throwable, format, arguments);
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logDelegator.isDebugEnabled();
    }

    /**
     * @param debugEnabled
     * @see com.devamatre.logger.Logger#setDebugEnabled(boolean)
     */
    @Override
    public void setDebugEnabled(boolean debugEnabled) {
        logDelegator.setDebugEnabled(debugEnabled);
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logDelegator.isInfoEnabled();
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return logDelegator.isWarnEnabled();
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return logDelegator.isErrorEnabled();
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled() {
        return logDelegator.isFatalEnabled();
    }
}
