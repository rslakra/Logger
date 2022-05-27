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
 * The <code>NullLogger</code> class is the default implementation of the
 * <code>Logger</code> interface which does nothing and will be used before the
 * configuration of actual logger.
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 1:51:50 PM
 * @since 1.0.0
 */
public final class NullLogger implements Logger {

    /**
     * Default Constructor.
     */
    public NullLogger() {
    }

    /**
     * Checks whether this category is enabled for the <code>DEBUG</code> Level.
     *
     * @return
     * @see com.devamatre.logger.Logger#isDebugEnabled()
     */
    public boolean isDebugEnabled() {
        return false;
    }

    /**
     * The debugEnabled to be set.
     *
     * @param debugEnabled
     * @see com.devamatre.logger.Logger#setDebugEnabled(boolean)
     */
    @Override
    public void setDebugEnabled(boolean debugEnabled) {
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isErrorEnabled()
     */
    public boolean isErrorEnabled() {
        return false;
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isFatalEnabled()
     */
    public boolean isFatalEnabled() {
        return false;
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isInfoEnabled()
     */
    public boolean isInfoEnabled() {
        return false;
    }

    /**
     * @return
     */
    public boolean isTraceEnabled() {
        return false;
    }

    /**
     * @return
     * @see com.devamatre.logger.Logger#isWarnEnabled()
     */
    public boolean isWarnEnabled() {
        return false;
    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object)
     */
    @Override
    public void fatal(Object object) {

    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#fatal(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void fatal(Object object, Throwable throwable) {

    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(String format, Object... arguments) {

    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void fatal(Throwable throwable, String format, Object... arguments) {

    }


    /**
     * @param object
     * @see com.devamatre.logger.Logger#error(java.lang.Object)
     */
    @Override
    public void error(Object object) {

    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#error(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void error(Object object, Throwable throwable) {

    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void error(String format, Object... arguments) {

    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void error(Throwable throwable, String format, Object... arguments) {

    }

    /**
     * @param object
     * @see com.devamatre.logger.Logger#warn(java.lang.Object)
     */
    @Override
    public void warn(Object object) {

    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#warn(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void warn(Object object, Throwable throwable) {

    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void warn(String format, Object... arguments) {

    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void warn(Throwable throwable, String format, Object... arguments) {

    }


    /**
     * @param object
     * @see com.devamatre.logger.Logger#info(java.lang.Object)
     */
    @Override
    public void info(Object object) {

    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#info(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void info(Object object, Throwable throwable) {

    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void info(String format, Object... arguments) {

    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void info(Throwable throwable, String format, Object... arguments) {

    }


    /**
     * @param object
     * @see com.devamatre.logger.Logger#debug(java.lang.Object)
     */
    @Override
    public void debug(Object object) {

    }

    /**
     * @param object
     * @param throwable
     * @see com.devamatre.logger.Logger#debug(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public void debug(Object object, Throwable throwable) {

    }

    /**
     * @param format
     * @param arguments
     */
    @Override
    public void debug(String format, Object... arguments) {

    }

    /**
     * @param throwable
     * @param format
     * @param arguments
     */
    @Override
    public void debug(Throwable throwable, String format, Object... arguments) {

    }

}
