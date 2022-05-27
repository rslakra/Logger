/******************************************************************************
 * Copyright (C) Devamatre 2009-2021. All rights reserved.
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
 * The <code>LogLevel</code> enum contains the details of the logging levels.
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 2:53:50 PM
 * @since 1.0.0
 */
public enum LogLevel {

    /**
     * Logging Level - ALL = 0
     */
    ALL,
    /**
     * Logging Level - DEBUG = 1
     */
    DEBUG,
    /**
     * Logging Level - INFO = 2
     */
    INFO,
    /**
     * Logging Level - WARN = 3
     */
    WARN,
    /**
     * Logging Level - ERROR = 4
     */
    ERROR,
    /**
     * Logging Level - FATAL = 5
     */
    FATAL,
    /**
     * Logging Level - OFF = 6
     */
    OFF;

    /**
     * The unreachable constructor to block creating the objects outside.
     */
    private LogLevel() {
    }

    /**
     * Returns the string representation of this debug level (See documentation
     * for log levels).
     *
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return name().toUpperCase().intern();
    }

    /**
     * Returns the log level position in its enum declaration (By default the
     * initial constant is assigned an ordinal of zero).
     *
     * @return ordinal
     */
    public int getLogLevel() {
        return this.ordinal();
    }

    /**
     * Returns true if the log level is > OFF otherwise false.
     *
     * @return
     */
    public boolean isLogEnabled() {
        return (getLogLevel() > LogLevel.OFF.getLogLevel());
    }

    /***
     * Returns the log string prefixed with the specified <code>prefix</code>.
     *
     * @param prefix
     * @return
     */
    public String logPrefixString(final String prefix) {
        return (prefix + toString() + ":").intern();
    }

    /**
     * Returns the <code>LogLevel</code> value of the <code>logLevel</code> string.
     *
     * @param logLevel
     * @return
     */
    public static LogLevel of(String logLevel) {
        return LogLevel.valueOf(logLevel.toUpperCase());
    }
}
