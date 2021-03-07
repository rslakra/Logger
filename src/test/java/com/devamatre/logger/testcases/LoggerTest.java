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

    private Logger LOGGER;

//    @PostConstruct
//    public void init() {
//        LOGGER.info("Initialized");
//    }

//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // LogManager.printSystemProperties();
//        // LogManager.configure(LogManager.LOG4J_XML_FILE);
//        LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
//        // LogManager.configure(true, LogEnum.WARN);
//        LoggerTest loggerTest = new LoggerTest();
//        loggerTest.testLogger(true);
//        loggerTest.testLogger(false);
//
//        loggerTest.testLoggerPackageTest();
//    }


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
        LogUtility.debug("+testLogger(" + useNullLogger + ")");
        // initialize logger.
        LOGGER = LogManager.getLogger((useNullLogger ? null : LoggerTest.class));

        LOGGER.info("+testLogger()");
        LOGGER.info("This is My logger testing.");
        LOGGER.warn("My Logger Warning");
        LOGGER.debug("My logger debug testing.");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("LOGGER IS ENABLED ... :)");
        }

        messages();

        LOGGER.error("My Logger error testing");
        LOGGER.fatal("My Logger Fatal Testing.");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Debugging Enabled!");
        }

        messages3();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Info Enabled!");
        }

        // loggerPackageTest
        testLoggerPackageTest();

        try {
            throwException();
        } catch (Exception e) {
            LOGGER.error("Error!!!", e);
        }

        LOGGER.info("-testLogger()");

        LOGGER.info("Starting TestLogUtility");
        LoggerTest loggerTest = new LoggerTest();
        loggerTest.testLoggerPackageTest();
        LOGGER.info("Ended TestLogUtility");

        LogUtility.debug("-testLogger()");
    }

    /**
     *
     */
    private void messages() {
        LOGGER.info("+messages");
        LOGGER.info("messages Method Call!");
        messages1();
        LOGGER.info("-messages");
    }

    /**
     *
     */
    private void messages1() {
        LOGGER.info("+messages1");
        LOGGER.info("messages1 Method Call!");
        messages2();
        messages5();
        LOGGER.info("-messages1");
    }

    /**
     *
     */
    private void messages2() {
        LOGGER.info("+messages2");
        LOGGER.info("messages2 Method Call!");
        LOGGER.info("-messages2");
    }

    /**
     *
     */
    private void messages3() {
        LOGGER.info("+messages3");
        LOGGER.info("messages3 Method Call!");
        LOGGER.info("-messages3");
    }

    /**
     *
     */
    private void messages4() {
        LOGGER.info("messages4 Method Call!");
    }

    /**
     *
     */
    private void messages5() {
        LOGGER.info("messages5 Method Call!");
        messages3();
        messages4();
    }

    /**
     * Throws an exception.
     *
     * @throws Exception
     */
    private void throwException() throws Exception {
        throw new IllegalArgumentException("Exception Testing!");
    }

    /**
     * Test Logger Package.
     */
    public void testLoggerPackageTest() {
        // initialize logger.
        LOGGER = LogManager.getLogger(LoggerTest.class);
        LOGGER.debug(LogLevel.DEBUG);
        LOGGER.info(LogLevel.INFO);
        LOGGER.warn(LogLevel.WARN);
        LOGGER.error(LogLevel.ERROR);
        LOGGER.fatal(LogLevel.FATAL);
    }
}