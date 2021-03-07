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
package com.devamatre.logger.testcases;

import com.devamatre.logger.*;
import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 2:51:50 PM
 * @since 1.0.0
 */
public class LoggerTest {

    static {
        MockLogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
    }

    @Test
    public void testLogger() {
        testLogger(true);
        testLogger(false);
        testLoggerPackageTest();
    }

    /**
     * @param useNullLogger
     */
    private void testLogger(boolean useNullLogger) {
        // initialize logger.
        final Logger LOGGER = LogManager.getLogger((useNullLogger ? null : LoggerTest.class));
        LOGGER.info("+testLogger({})", useNullLogger);

        LOGGER.info("+testLogger()");
        LOGGER.info("This is My logger testing.");
        LOGGER.warn("My Logger Warning");
        LOGGER.debug("My logger debug testing.");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("isDebugEnabled ... :)");
        }

        messages(LOGGER);

        LOGGER.error("My Logger error testing");
        LOGGER.fatal("My Logger Fatal Testing.");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Debugging Enabled!");
        }

        messages3(LOGGER);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Info Enabled!");
        }

        // loggerPackageTest
        testLoggerPackageTest();

        try {
            throwException(LOGGER);
        } catch (Exception e) {
            LOGGER.error("Error!!!", e);
        }

        LOGGER.info("Starting TestLogUtility");
        LoggerTest loggerTest = new LoggerTest();
        loggerTest.testLoggerPackageTest();
        LOGGER.info("Ended TestLogUtility");

        LogUtility.debug("-testLogger()");
    }

    /**
     * @param LOGGER
     */
    private void messages(final Logger LOGGER) {
        LOGGER.info("+messages({})", LOGGER);
        LOGGER.info("LOGGER: {}", LOGGER);
        messages1(LOGGER);
        LOGGER.info("-messages");
    }

    /**
     * @param LOGGER
     */
    private void messages1(final Logger LOGGER) {
        LOGGER.info("+messages1({})", LOGGER);
        LOGGER.info("messages1 Method Call!");
        messages2(LOGGER);
        messages5(LOGGER);
        LOGGER.info("-messages1");
    }

    /**
     * @param LOGGER
     */
    private void messages2(final Logger LOGGER) {
        LOGGER.info("+messages2({})", LOGGER);
        LOGGER.info("messages2 Method Call!");
        LOGGER.info("-messages2");
    }

    /**
     * @param LOGGER
     */
    private void messages3(final Logger LOGGER) {
        LOGGER.info("+messages3({})", LOGGER);
        LOGGER.info("messages3 Method Call!");
        LOGGER.info("-messages3");
    }

    /**
     * @param LOGGER
     */
    private void messages4(final Logger LOGGER) {
        LOGGER.info("messages4({})", LOGGER);
    }

    /**
     * @param LOGGER
     */
    private void messages5(final Logger LOGGER) {
        LOGGER.info("messages5({})", LOGGER);
        messages3(LOGGER);
        messages4(LOGGER);
    }

    /**
     * Throws an exception.
     *
     * @throws Exception
     */
    private void throwException(final Logger LOGGER) throws Exception {
        LOGGER.warn("throwException({})", LOGGER);
        throw new IllegalArgumentException("Exception Testing!");
    }

    /**
     * Test Logger Package.
     */
    public void testLoggerPackageTest() {
        // initialize logger.
        final Logger LOGGER = LogManager.getLogger(LoggerTest.class);
        LOGGER.debug(LogLevel.DEBUG);
        LOGGER.debug("DEBUG: {}", LogLevel.DEBUG);
        LOGGER.info(LogLevel.INFO);
        LOGGER.info("INFO: {}", LogLevel.INFO);
        LOGGER.warn(LogLevel.WARN);
        LOGGER.warn("WARN: {}", LogLevel.WARN);
        LOGGER.error(LogLevel.ERROR);
        LOGGER.error("ERROR: {}", LogLevel.ERROR);
        LOGGER.fatal(LogLevel.FATAL);
        LOGGER.fatal("FATAL: {}", LogLevel.FATAL);
    }
}