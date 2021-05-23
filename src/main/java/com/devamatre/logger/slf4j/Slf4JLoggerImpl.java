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
package com.devamatre.logger.slf4j;

import com.devamatre.logger.*;
import org.slf4j.LoggerFactory;

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
public final class Slf4JLoggerImpl extends AbstractLoggerImpl implements Logger {

    /* The Root logger. */
    private org.slf4j.Logger logDelegator;

    /**
     * @param logClass
     */
    public Slf4JLoggerImpl(Class<?> logClass) {
        super();
        this.logDelegator = LoggerFactory.getLogger(logClass);
    }

    /**
     * Returns the supported <code>LogBinderType</code>.
     *
     * @return
     */
    @Override
    public LogBinderType getBinderType() {
        return LogBinderType.SLF4J;
    }

    /**
     * Logs a message object with the {@link Logger#fatal(Object)} Level.
     *
     * @param object
     * @see Logger#fatal(Object)
     */
    @Override
    public void fatal(final Object object) {
        error(object);
//        if (isFatalEnabled()) {
//            logDelegator.error(getIndents(object).toString());
//        }
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
     * @see Logger#fatal(Object,
     * Throwable)
     */
    @Override
    public void fatal(final Object object, final Throwable throwable) {
        error(object, throwable);
//        if (isFatalEnabled()) {
//            logDelegator.error(getIndents(object).toString(), throwable);
//        }
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(final String format, final Object... arguments) {
        error(format, arguments);
//        logDelegator.error(format, arguments);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(final Throwable throwable, final String format, final Object... arguments) {
        error(throwable, format, arguments);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Logs a message object with the {@link Logger#error(Object)} Level.
     *
     * <p>
     * See <code>org.apache.log4j.Category</code> for more detailed information.
     * </p>
     *
     * @param object
     * @see Logger#error(Object)
     */
    @Override
    public void error(final Object object) {
        if (isErrorEnabled()) {
            logDelegator.error(getIndents(object).toString());
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
     * @see Logger#error(Object,
     * Throwable)
     */
    @Override
    public void error(final Object object, final Throwable throwable) {
        if (isErrorEnabled()) {
            logDelegator.error(getIndents(object).toString(), throwable);
        }
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void error(final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments);
        this.error(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void error(final Throwable throwable, final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
        this.error(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Logs a message object with the {@link Logger#warn(Object)} Level.
     *
     * @param object
     * @see Logger#warn(Object)
     */
    @Override
    public void warn(final Object object) {
        if (isWarnEnabled()) {
            logDelegator.warn(getIndents(object).toString());
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
     * @see Logger#warn(Object,
     * Throwable)
     */
    @Override
    public void warn(final Object object, final Throwable throwable) {
        if (isWarnEnabled()) {
            logDelegator.warn(getIndents(object).toString(), throwable);
        }
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void warn(final String format, final Object... arguments) {
        logDelegator.warn(format, arguments);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.warn(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void warn(final Throwable throwable, final String format, final Object... arguments) {
        logDelegator.warn(format, arguments, throwable);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.warn(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Logs a message object with the {@link Logger#info(Object)} Level.
     *
     * @param object
     * @see Logger#info(Object)
     */
    @Override
    public void info(final Object object) {
        if (isInfoEnabled()) {
            logDelegator.info(getIndents(object).toString());
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
     * @see Logger#info(Object,
     * Throwable)
     */
    @Override
    public void info(final Object object, final Throwable throwable) {
        if (isInfoEnabled()) {
            logDelegator.info(getIndents(object).toString(), throwable);
        }
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void info(final String format, final Object... arguments) {
        logDelegator.info(format, arguments);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.info(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void info(final Throwable throwable, final String format, final Object... arguments) {
        logDelegator.info(format, arguments, throwable);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.info(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Logs a message object with the {@link Logger#debug(Object)} Level.
     *
     * @param object
     * @see Logger#debug(Object)
     */
    @Override
    public void debug(final Object object) {
        if (isDebugEnabled()) {
            logDelegator.debug(getIndents(object).toString());
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
     * @see Logger#debug(Object,
     * Throwable)
     */
    @Override
    public void debug(final Object object, final Throwable throwable) {
        if (isDebugEnabled()) {
            logDelegator.debug(getIndents(object).toString(), throwable);
        }
    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void debug(final String format, final Object... arguments) {
        logDelegator.debug(format, arguments);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.debug(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void debug(final Throwable throwable, final String format, final Object... arguments) {
        logDelegator.debug(format, arguments, throwable);
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.debug(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Checks whether this category is enabled for the <code>DEBUG</code> Level.
     *
     * @return
     * @see Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logDelegator.isDebugEnabled();
    }

    /**
     * The debugEnabled to be set.
     *
     * @param debugEnabled
     */
    @Override
    public void setDebugEnabled(boolean debugEnabled) {
    }

    /**
     * Checks whether this category is enabled for the <code>INFO</code> Level.
     *
     * @return
     * @see Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logDelegator.isInfoEnabled();
    }

    /**
     * Checks whether this category is enabled for the <code>WARN</code> Level.
     *
     * @return
     * @see Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return logDelegator.isWarnEnabled();
    }

    /**
     * Checks whether this category is enabled for the <code>ERROR</code> Level.
     *
     * @return
     * @see Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return logDelegator.isErrorEnabled();
    }

    /**
     * Checks whether this category is enabled for the <code>FATAL</code> Level.
     *
     * @return
     * @see Logger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled() {
        return isErrorEnabled();
    }
}
