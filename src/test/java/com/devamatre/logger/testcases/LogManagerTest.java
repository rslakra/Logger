/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2023. All rights reserved.
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Mar 13, 2021 18:07:23
 */
public class LogManagerTest {

    @Test
    public void testGetNullLogger() {
        final Logger logger = LogManager.getLogger(null);
        assertNotNull(logger);
        assertEquals(NullLogger.class, logger.getClass());
    }

    @Test
    public void testGetLogger() {
        final Logger logger = LogManager.getLogger(LogManagerTest.class);
        assertNotNull(logger);
        assertEquals(LoggerImpl.class, logger.getClass());
    }

    @Test
    public void testGetDefaultConfigPath() {
        String defaultConfigPath = LogManager.getDefaultConfigPath(false);
        assertTrue(LogUtility.isNotNullOrEmpty(defaultConfigPath));
        assertEquals(LogUtility.USER_DIR, defaultConfigPath);
    }

    @Test
    public void testGetDefaultAbsoluteConfigPath() {
        String defaultConfigPath = LogManager.getDefaultConfigPath(true);
        assertTrue(LogUtility.isNotNullOrEmpty(defaultConfigPath));
        assertNotEquals(LogUtility.USER_DIR, defaultConfigPath);
        assertTrue(defaultConfigPath.endsWith("com/devamatre/logger"));
    }

    @Test
    public void testConfigure() {
        LogManager.configure("testLog4j.properties");
        final Logger logger = LogManager.getLogger(null);
        assertNotNull(logger);
        assertEquals(NullLogger.class, logger.getClass());
        logger.info("Configure (testLog4j) logger.");
    }

}
