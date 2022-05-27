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
package com.devamatre.logger.testcases;

import com.devamatre.logger.AbstractTestCase;
import com.devamatre.logger.LogLevel;
import com.devamatre.logger.Logger;
import com.devamatre.logger.MockLogManager;
import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 2:51:50 PM
 * @since 1.0.0
 */
public class LoggerTestCase extends AbstractTestCase {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = MockLogManager.getLogger(LoggerTestCase.class);

    @Test
    public void testFatal() {
        LOGGER.fatal(LogLevel.FATAL);
        LOGGER.fatal("FATAL: {}", LogLevel.FATAL);
        assertTrue(LogLevel.of(LogLevel.FATAL.name()) == LogLevel.FATAL);
    }

    @Test
    public void testError() {
        LOGGER.error(LogLevel.ERROR);
        LOGGER.error("ERROR: {}", LogLevel.ERROR);
        assertTrue(LogLevel.of(LogLevel.ERROR.name()) == LogLevel.ERROR);
    }

    @Test
    public void testWarn() {
        LOGGER.warn(LogLevel.WARN);
        LOGGER.warn("WARN: {}", LogLevel.WARN);
        assertTrue(LogLevel.of(LogLevel.WARN.name()) == LogLevel.WARN);
    }

    @Test
    public void testInfo() {
        LOGGER.info(LogLevel.INFO);
        LOGGER.info("INFO: {}", LogLevel.INFO);
        assertTrue(LogLevel.of(LogLevel.INFO.name()) == LogLevel.INFO);
    }

    @Test
    public void testDebug() {
        LOGGER.debug(LogLevel.DEBUG);
        LOGGER.debug("DEBUG: {}", LogLevel.DEBUG);
        assertTrue(LogLevel.of(LogLevel.DEBUG.name()) == LogLevel.DEBUG);
    }

}
