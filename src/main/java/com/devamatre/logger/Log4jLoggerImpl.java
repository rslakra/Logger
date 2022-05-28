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

import org.apache.logging.log4j.Level;

/**
 * The <code>LoggerImpl</code> class is the implementation of the Logger
 * interface. It has the definition of all logger methods like warn, debug etc.
 * <code>
 * [Indentation Example - Instance 0]
 * 2009/09/09 22:06:28.109: Section start
 * :   Subsection A
 * ....
 * :        Subsection A.1
 * ....
 * :            Subsection A.1.1
 * ....
 * :            End Subsection A.1.1
 * ....
 * :        End of Subsection A.1
 * ....
 * :   End of Subsection A
 * </code>
 * <p>
 * This indentation will help to know in understanding of the logs.
 * </p>
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 2:51:50 PM
 * @since 1.0.0
 */
public final class Log4jLoggerImpl extends AbstractLoggerImpl implements Logger {

    /* The Root logger. */
    private org.apache.logging.log4j.Logger rootLogger;

    /**
     * Parameterized Constructor.
     *
     * @param logClass
     */
    public Log4jLoggerImpl(final Class<?> logClass) {
        super(false, true);
        rootLogger = LogManager.getRootLogger(logClass);
    }

    /**
     * Checks whether this category is enabled for the <code>DEBUG</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return rootLogger.isEnabled(Level.DEBUG) && isDebugEnabled();
    }

    /**
     * Checks whether this category is enabled for the <code>INFO</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return rootLogger.isEnabled(Level.WARN);
    }

    /**
     * Checks whether this category is enabled for the <code>WARN</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return rootLogger.isEnabled(Level.WARN);
    }

    /**
     * Checks whether this category is enabled for the <code>ERROR</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return rootLogger.isEnabled(Level.ERROR);
    }

    /**
     * Checks whether this category is enabled for the <code>FATAL</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled() {
        return rootLogger.isEnabled(Level.FATAL);
    }


    /**
     * Logs a message object with the {@link Level#FATAL FATAL} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object)
     */
    @Override
    public void fatal(final Object object) {
        if (isFatalEnabled()) {
            rootLogger.fatal(getIndents(object));
        }
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
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void fatal(final Object object, final Throwable throwable) {
        if (isFatalEnabled()) {
            rootLogger.fatal(getIndents(object), throwable);
        }
    }


    /**
     * Logs a message object with the {@link Level#ERROR ERROR} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see com.devamatre.logger.Logger#error(java.lang.Object)
     */
    @Override
    public void error(final Object object) {
        if (isErrorEnabled()) {
            rootLogger.error(getIndents(object));
        }
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
     * @see com.devamatre.logger.Logger#error(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void error(final Object object, final Throwable throwable) {
        if (isErrorEnabled()) {
            rootLogger.error(getIndents(object), throwable);
        }
    }


    /**
     * Logs a message object with the {@link Level#WARN WARN} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see com.devamatre.logger.Logger#warn(java.lang.Object)
     */
    @Override
    public void warn(final Object object) {
        if (isWarnEnabled()) {
            rootLogger.warn(getIndents(object));
        }
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
     * @see com.devamatre.logger.Logger#warn(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void warn(final Object object, final Throwable throwable) {
        if (isWarnEnabled()) {
            rootLogger.warn(getIndents(object), throwable);
        }
    }


    /**
     * Logs a message object with the {@link Level#INFO INFO} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see com.devamatre.logger.Logger#info(java.lang.Object)
     */
    @Override
    public void info(final Object object) {
        if (isInfoEnabled()) {
            rootLogger.info(getIndents(object));
        }
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
     * @see com.devamatre.logger.Logger#info(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void info(final Object object, final Throwable throwable) {
        if (isInfoEnabled()) {
            rootLogger.info(getIndents(object), throwable);
        }
    }


    /**
     * Logs a message object with the {@link Level#DEBUG DEBUG} Level. It
     * delegates the calls to <code>org.apache.log4j.Category</code>.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see com.devamatre.logger.Logger#debug(java.lang.Object)
     */
    @Override
    public void debug(final Object object) {
        if (isDebugEnabled()) {
            rootLogger.debug(getIndents(object));
        }
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
     * @see com.devamatre.logger.Logger#debug(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void debug(final Object object, final Throwable throwable) {
        if (isDebugEnabled()) {
            rootLogger.debug(getIndents(object), throwable);
        }
    }
}
