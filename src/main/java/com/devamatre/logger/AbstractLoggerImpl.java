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

    /**
     *
     */
    public AbstractLoggerImpl() {
        super();
        supportIndentation = false;
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
        LogUtility.debug("+getIndents({}, {})", allowedIndentation, object);

        /* check indentation supported or not. */
        if (allowedIndentation) {
            /* get current stack trace and adjust for this method. */
            int depth = (new Throwable().getStackTrace().length - 4);
            object = indent(depth) + object;
        }

        LogUtility.debug("-getIndents(), object: {}", object);
        return object;
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
     * Returns the supported <code>LogBinderType</code>.
     *
     * @return
     */
    @Override
    public LogBinderType getBinderType() {
        return null;
    }
}
