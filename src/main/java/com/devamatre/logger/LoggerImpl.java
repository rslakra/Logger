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

import org.apache.log4j.Category;
import org.apache.log4j.Level;

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
public final class LoggerImpl implements Logger {

    /* The Root logger. */
    private Category rootLogger;

    /* The Root logger. */
    private boolean supportIndentation;

    /* Maintains indentation of the log messages. */
    private final StringBuilder indentBuilder = new StringBuilder();

    /* debugEnabled - user internally. */
    private boolean debugEnabled;

    /*
     * This code is added to optimize the indentation for performance.
     */
    private int lastDepth = 0;
    private String indentString;

    /**
     * Parameterized Constructor.
     *
     * @param logClass
     */
    public LoggerImpl(Class<?> logClass) {
        this.rootLogger = org.apache.log4j.LogManager.getLogger(logClass);
        supportIndentation = false;
        debugEnabled = true;
    }

    /**
     * This method returns the string after indentation up to the passed depth.
     *
     * @param depth
     * @return string after indentation.
     */
    private String indent(int depth) {
        LogUtility.debug("+indent(" + depth + ")");
        if (lastDepth != depth) {
            this.lastDepth = depth;

            /* prepare indented string by emptying it. */
            indentBuilder.delete(0, indentBuilder.length());
            for (int i = 0; i < depth; i++) {
                indentBuilder.append(LogUtility.HTAB);
            }

            // avoid multiple object creation.
            indentString = indentBuilder.toString();
        }

        LogUtility.debug("-indent(), indentString:" + indentString);
        return indentString;
    }

    /**
     * Returns the object to be logged with required indents.
     *
     * @param object
     * @return object to be logged.
     */
    private Object getIndents(final Object object) {
        return getIndents(object, supportIndentation);
    }

    /**
     * Returns the object to be logged with required indents.
     *
     * @param object
     * @param indentation
     * @return
     */
    private Object getIndents(Object object, final boolean indentation) {
        LogUtility.debug("+getIndents(" + object + ", " + indentation + ")");

        /* check indentation supported or not. */
        if (indentation) {
            /* get current stack trace and adjust for this method. */
            int depth = (new Throwable().getStackTrace().length - 4);
            object = indent(depth) + object;
        }

        LogUtility.debug("-getIndents(), object: " + object);
        return object;
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
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments);
        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(final Throwable throwable, final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
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
     * @param format
     * @param arguments
     */
    @Override
    public void warn(final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments);
        this.warn(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void warn(final Throwable throwable, final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
        this.warn(logTuple.toMessage(), logTuple.getThrowable());
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
     * @param format
     * @param arguments
     */
    @Override
    public void info(final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments);
        this.info(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void info(final Throwable throwable, final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
        this.info(logTuple.toMessage(), logTuple.getThrowable());
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

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void debug(final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments);
        this.debug(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void debug(final Throwable throwable, final String format, final Object... arguments) {
        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
        this.debug(logTuple.toMessage(), logTuple.getThrowable());
    }

    /**
     * Checks whether this category is enabled for the <code>DEBUG</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return rootLogger.isEnabledFor(Level.DEBUG) && debugEnabled;
    }

    /**
     * The debugEnabled to be set.
     *
     * @param debugEnabled
     * @see com.devamatre.logger.Logger#setDebugEnabled(boolean)
     */
    @Override
    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    /**
     * Checks whether this category is enabled for the <code>INFO</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return rootLogger.isEnabledFor(Level.WARN);
    }

    /**
     * Checks whether this category is enabled for the <code>WARN</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return rootLogger.isEnabledFor(Level.WARN);
    }

    /**
     * Checks whether this category is enabled for the <code>ERROR</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return rootLogger.isEnabledFor(Level.ERROR);
    }

    /**
     * Checks whether this category is enabled for the <code>FATAL</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled() {
        return rootLogger.isEnabledFor(Level.FATAL);
    }
}