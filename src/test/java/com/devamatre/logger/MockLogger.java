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

/**
 * The default logger manager for test cases.
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2010-08-09 2:51:50 PM
 * @since 1.0.0
 */
public class MockLogger extends AbstractLoggerImpl implements Logger {

    private Logger LOGGER;

    /**
     * @param logClass
     */
    public MockLogger(Class<?> logClass) {
        super(false, true);
        LOGGER = new Log4jLoggerImpl(logClass);
    }

    @Override
    public boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return LOGGER.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return LOGGER.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return LOGGER.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return LOGGER.isFatalEnabled();
    }

    /**
     * FATAL - 5
     * <p>
     * Logs a message object with the {@link LogBinderType FATAL} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     */
    @Override
    public void fatal(Object object) {

    }

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
    @Override
    public void fatal(Object object, Throwable throwable) {

    }

    /**
     * ERROR - 4
     * <p>
     * Logs a message object with the {@link LogBinderType ERROR} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     */
    @Override
    public void error(Object object) {

    }

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
    @Override
    public void error(Object object, Throwable throwable) {

    }

    /**
     * WARN - 3
     * <p>
     * Logs a message object with the {@link LogBinderType WARN} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     */
    @Override
    public void warn(Object object) {

    }

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
    @Override
    public void warn(Object object, Throwable throwable) {

    }

    /**
     * INFO - 2
     * <p>
     * Logs a message object with the {@link LogBinderType INFO} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     */
    @Override
    public void info(Object object) {

    }

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
    @Override
    public void info(Object object, Throwable throwable) {

    }

    /**
     * DEBUG - 1
     * <p>
     * Logs a message object with the {@link LogBinderType DEBUG} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     */
    @Override
    public void debug(Object object) {

    }

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
    @Override
    public void debug(Object object, Throwable throwable) {

    }
}
