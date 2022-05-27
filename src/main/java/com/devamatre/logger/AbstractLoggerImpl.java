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
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

/**
 * The <code>AbstractLoggerImpl</code> class is the implementation of the Logger
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
public abstract class AbstractLoggerImpl implements Logger {

    /* If support log indentation */
    private boolean supportIndentation;

    /* Maintains indentation of the log messages. */
    private final StringBuilder indentBuilder = new StringBuilder();

    /*
     * This code is added to optimize the indentation for performance.
     */
    private int lastDepth = 0;
    private String indentString;

    /* debugEnabled - user internally. */
    private boolean debugEnabled;

    /* The Root logger. */
    private org.apache.logging.log4j.Logger rootLogger;

    /**
     * @param supportIndentation
     * @param debugEnabled
     */
    public AbstractLoggerImpl(final boolean supportIndentation, final boolean debugEnabled, final org.apache.logging.log4j.Logger rootLogger) {
        super();
        this.supportIndentation = supportIndentation;
        this.debugEnabled = debugEnabled;
        this.rootLogger = rootLogger;
    }

    /**
     * @return
     */
    public boolean isSupportIndentation() {
        return supportIndentation;
    }

    /**
     * @param supportIndentation
     */
    public void setSupportIndentation(boolean supportIndentation) {
        this.supportIndentation = supportIndentation;
    }

    public int getLastDepth() {
        return lastDepth;
    }

    /**
     * @param debugEnabled
     */
    @Override
    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    /**
     * @return
     */
    public org.apache.logging.log4j.Logger getRootLogger() {
        return rootLogger;
    }

    /**
     * This method returns the string after indentation up to the passed depth.
     *
     * @param depth
     * @return string after indentation.
     */
    protected final String indent(int depth) {
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
    protected final Object getIndents(final Object object) {
        return getIndents(supportIndentation, object);
    }

    /**
     * Returns the object to be logged with required indents.
     *
     * @param allowedIndentation
     * @param object
     * @return
     */
    protected final Object getIndents(final boolean allowedIndentation, Object object) {
//        LogUtility.debug("+getIndents({}, {})", allowedIndentation, object);

        /* check indentation supported or not. */
        if (allowedIndentation) {
            /* get current stack trace and adjust for this method. */
            int depth = (new Throwable().getStackTrace().length - 4);
            object = indent(depth) + object;
        }

//        LogUtility.debug("-getIndents(), object: {}", object);
        return object;
    }
//
//    /**
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void fatal(final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param throwable
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void fatal(final Throwable throwable, final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.fatal(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void error(final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.error(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param throwable
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void error(final Throwable throwable, final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.error(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void warn(final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.warn(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param throwable
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void warn(final Throwable throwable, final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.warn(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void info(final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.info(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param throwable
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void info(final Throwable throwable, final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.info(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void debug(final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments);
//        this.debug(logTuple.toMessage(), logTuple.getThrowable());
//    }
//
//    /**
//     * @param throwable
//     * @param format
//     * @param arguments
//     */
//    @Override
//    public void debug(final Throwable throwable, final String format, final Object... arguments) {
//        LogTuple logTuple = LogFormatter.normalize(format, arguments, throwable);
//        this.debug(logTuple.toMessage(), logTuple.getThrowable());
//    }

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
            getRootLogger().fatal(getIndents(object));
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
            getRootLogger().fatal(getIndents(object), throwable);
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
            getRootLogger().error(getIndents(object));
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
            getRootLogger().error(getIndents(object), throwable);
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
            getRootLogger().warn(getIndents(object));
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
            getRootLogger().warn(getIndents(object), throwable);
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
            getRootLogger().info(getIndents(object));
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
            getRootLogger().info(getIndents(object), throwable);
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
            getRootLogger().debug(getIndents(object));
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
            getRootLogger().debug(getIndents(object), throwable);
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


    @Override
    public LogBinderType getBinderType() {
        return null;
    }


    @Override
    public void catching(Level level, Throwable t) {

    }

    @Override
    public void catching(Throwable t) {

    }

    @Override
    public void debug(Marker marker, Message msg) {

    }

    @Override
    public void debug(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void debug(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void debug(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void debug(Marker marker, CharSequence message) {

    }

    @Override
    public void debug(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void debug(Marker marker, Object message) {

    }

    @Override
    public void debug(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void debug(Marker marker, String message) {

    }

    @Override
    public void debug(Marker marker, String message, Object... params) {

    }

    @Override
    public void debug(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void debug(Marker marker, String message, Throwable t) {

    }

    @Override
    public void debug(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void debug(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void debug(Message msg) {

    }

    @Override
    public void debug(Message msg, Throwable t) {

    }

    @Override
    public void debug(MessageSupplier msgSupplier) {

    }

    @Override
    public void debug(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void debug(CharSequence message) {

    }

    @Override
    public void debug(CharSequence message, Throwable t) {

    }

    @Override
    public void debug(String message) {

    }

    @Override
    public void debug(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void debug(String message, Throwable t) {

    }

    @Override
    public void debug(Supplier<?> msgSupplier) {

    }

    @Override
    public void debug(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void debug(String message, Object p0) {

    }

    @Override
    public void debug(String message, Object p0, Object p1) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void entry() {

    }

    @Override
    public void entry(Object... params) {

    }

    @Override
    public void error(Marker marker, Message msg) {

    }

    @Override
    public void error(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void error(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void error(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void error(Marker marker, CharSequence message) {

    }

    @Override
    public void error(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void error(Marker marker, Object message) {

    }

    @Override
    public void error(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void error(Marker marker, String message) {

    }

    @Override
    public void error(Marker marker, String message, Object... params) {

    }

    @Override
    public void error(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void error(Marker marker, String message, Throwable t) {

    }

    @Override
    public void error(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void error(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void error(Message msg) {

    }

    @Override
    public void error(Message msg, Throwable t) {

    }

    @Override
    public void error(MessageSupplier msgSupplier) {

    }

    @Override
    public void error(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void error(CharSequence message) {

    }

    @Override
    public void error(CharSequence message, Throwable t) {

    }

    @Override
    public void error(String message) {

    }

    @Override
    public void error(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void error(String message, Throwable t) {

    }

    @Override
    public void error(Supplier<?> msgSupplier) {

    }

    @Override
    public void error(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void error(Marker marker, String message, Object p0) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void error(String message, Object p0) {

    }

    @Override
    public void error(String message, Object p0, Object p1) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void exit() {

    }

    @Override
    public <R> R exit(R result) {
        return null;
    }

    @Override
    public void fatal(Marker marker, Message msg) {

    }

    @Override
    public void fatal(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void fatal(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, CharSequence message) {

    }

    @Override
    public void fatal(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, Object message) {

    }

    @Override
    public void fatal(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, String message) {

    }

    @Override
    public void fatal(Marker marker, String message, Object... params) {

    }

    @Override
    public void fatal(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void fatal(Marker marker, String message, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void fatal(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void fatal(Message msg) {

    }

    @Override
    public void fatal(Message msg, Throwable t) {

    }

    @Override
    public void fatal(MessageSupplier msgSupplier) {

    }

    @Override
    public void fatal(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void fatal(CharSequence message) {

    }

    @Override
    public void fatal(CharSequence message, Throwable t) {

    }

    @Override
    public void fatal(String message) {

    }

    @Override
    public void fatal(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void fatal(String message, Throwable t) {

    }

    @Override
    public void fatal(Supplier<?> msgSupplier) {

    }

    @Override
    public void fatal(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void fatal(String message, Object p0) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public <MF extends MessageFactory> MF getMessageFactory() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void info(Marker marker, Message msg) {

    }

    @Override
    public void info(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void info(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void info(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void info(Marker marker, CharSequence message) {

    }

    @Override
    public void info(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void info(Marker marker, Object message) {

    }

    @Override
    public void info(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void info(Marker marker, String message) {

    }

    @Override
    public void info(Marker marker, String message, Object... params) {

    }

    @Override
    public void info(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void info(Marker marker, String message, Throwable t) {

    }

    @Override
    public void info(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void info(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void info(Message msg) {

    }

    @Override
    public void info(Message msg, Throwable t) {

    }

    @Override
    public void info(MessageSupplier msgSupplier) {

    }

    @Override
    public void info(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void info(CharSequence message) {

    }

    @Override
    public void info(CharSequence message, Throwable t) {

    }

    @Override
    public void info(String message) {

    }

    @Override
    public void info(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void info(String message, Throwable t) {

    }

    @Override
    public void info(Supplier<?> msgSupplier) {

    }

    @Override
    public void info(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void info(Marker marker, String message, Object p0) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void info(String message, Object p0) {

    }

    @Override
    public void info(String message, Object p0, Object p1) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isEnabled(Level level) {
        return false;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker) {
        return false;
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isFatalEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void log(Level level, Marker marker, Message msg) {

    }

    @Override
    public void log(Level level, Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, CharSequence message) {

    }

    @Override
    public void log(Level level, Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, Object message) {

    }

    @Override
    public void log(Level level, Marker marker, Object message, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, String message) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object... params) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void log(Level level, Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void log(Level level, Message msg) {

    }

    @Override
    public void log(Level level, Message msg, Throwable t) {

    }

    @Override
    public void log(Level level, MessageSupplier msgSupplier) {

    }

    @Override
    public void log(Level level, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void log(Level level, CharSequence message) {

    }

    @Override
    public void log(Level level, CharSequence message, Throwable t) {

    }

    @Override
    public void log(Level level, Object message) {

    }

    @Override
    public void log(Level level, Object message, Throwable t) {

    }

    @Override
    public void log(Level level, String message) {

    }

    @Override
    public void log(Level level, String message, Object... params) {

    }

    @Override
    public void log(Level level, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void log(Level level, String message, Throwable t) {

    }

    @Override
    public void log(Level level, Supplier<?> msgSupplier) {

    }

    @Override
    public void log(Level level, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void log(Level level, String message, Object p0) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void printf(Level level, Marker marker, String format, Object... params) {

    }

    @Override
    public void printf(Level level, String format, Object... params) {

    }

    @Override
    public <T extends Throwable> T throwing(Level level, T t) {
        return null;
    }

    @Override
    public <T extends Throwable> T throwing(T t) {
        return null;
    }

    @Override
    public void trace(Marker marker, Message msg) {

    }

    @Override
    public void trace(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void trace(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void trace(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void trace(Marker marker, CharSequence message) {

    }

    @Override
    public void trace(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void trace(Marker marker, Object message) {

    }

    @Override
    public void trace(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void trace(Marker marker, String message) {

    }

    @Override
    public void trace(Marker marker, String message, Object... params) {

    }

    @Override
    public void trace(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void trace(Marker marker, String message, Throwable t) {

    }

    @Override
    public void trace(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void trace(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void trace(Message msg) {

    }

    @Override
    public void trace(Message msg, Throwable t) {

    }

    @Override
    public void trace(MessageSupplier msgSupplier) {

    }

    @Override
    public void trace(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void trace(CharSequence message) {

    }

    @Override
    public void trace(CharSequence message, Throwable t) {

    }

    @Override
    public void trace(Object message) {

    }

    @Override
    public void trace(Object message, Throwable t) {

    }

    @Override
    public void trace(String message) {

    }

    @Override
    public void trace(String message, Object... params) {

    }

    @Override
    public void trace(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void trace(String message, Throwable t) {

    }

    @Override
    public void trace(Supplier<?> msgSupplier) {

    }

    @Override
    public void trace(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void trace(String message, Object p0) {

    }

    @Override
    public void trace(String message, Object p0, Object p1) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public EntryMessage traceEntry() {
        return null;
    }

    @Override
    public EntryMessage traceEntry(String format, Object... params) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(String format, Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(Message message) {
        return null;
    }

    @Override
    public void traceExit() {

    }

    @Override
    public <R> R traceExit(R result) {
        return null;
    }

    @Override
    public <R> R traceExit(String format, R result) {
        return null;
    }

    @Override
    public void traceExit(EntryMessage message) {

    }

    @Override
    public <R> R traceExit(EntryMessage message, R result) {
        return null;
    }

    @Override
    public <R> R traceExit(Message message, R result) {
        return null;
    }

    @Override
    public void warn(Marker marker, Message msg) {

    }

    @Override
    public void warn(Marker marker, Message msg, Throwable t) {

    }

    @Override
    public void warn(Marker marker, MessageSupplier msgSupplier) {

    }

    @Override
    public void warn(Marker marker, MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void warn(Marker marker, CharSequence message) {

    }

    @Override
    public void warn(Marker marker, CharSequence message, Throwable t) {

    }

    @Override
    public void warn(Marker marker, Object message) {

    }

    @Override
    public void warn(Marker marker, Object message, Throwable t) {

    }

    @Override
    public void warn(Marker marker, String message) {

    }

    @Override
    public void warn(Marker marker, String message, Object... params) {

    }

    @Override
    public void warn(Marker marker, String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void warn(Marker marker, String message, Throwable t) {

    }

    @Override
    public void warn(Marker marker, Supplier<?> msgSupplier) {

    }

    @Override
    public void warn(Marker marker, Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void warn(Message msg) {

    }

    @Override
    public void warn(Message msg, Throwable t) {

    }

    @Override
    public void warn(MessageSupplier msgSupplier) {

    }

    @Override
    public void warn(MessageSupplier msgSupplier, Throwable t) {

    }

    @Override
    public void warn(CharSequence message) {

    }

    @Override
    public void warn(CharSequence message, Throwable t) {

    }

    @Override
    public void warn(String message) {

    }

    @Override
    public void warn(String message, Supplier<?>... paramSuppliers) {

    }

    @Override
    public void warn(String message, Throwable t) {

    }

    @Override
    public void warn(Supplier<?> msgSupplier) {

    }

    @Override
    public void warn(Supplier<?> msgSupplier, Throwable t) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void warn(String message, Object p0) {

    }

    @Override
    public void warn(String message, Object p0, Object p1) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void logMessage(Level level, Marker marker, String fqcn, StackTraceElement location, Message message, Throwable throwable) {
        getRootLogger().logMessage(level, marker, fqcn, location, message, throwable);
    }

    @Override
    public LogBuilder atTrace() {
        return getRootLogger().atTrace();
    }

    @Override
    public LogBuilder atDebug() {
        return getRootLogger().atDebug();
    }

    @Override
    public LogBuilder atInfo() {
        return getRootLogger().atInfo();
    }

    @Override
    public LogBuilder atWarn() {
        return getRootLogger().atWarn();
    }

    @Override
    public LogBuilder atError() {
        return getRootLogger().atError();
    }

    @Override
    public LogBuilder atFatal() {
        return getRootLogger().atFatal();
    }

    @Override
    public LogBuilder always() {
        return getRootLogger().always();
    }

    @Override
    public LogBuilder atLevel(Level level) {
        return getRootLogger().atLevel(level);
    }
}
