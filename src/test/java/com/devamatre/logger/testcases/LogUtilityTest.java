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

import com.devamatre.logger.LogUtility;
import org.junit.jupiter.api.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2010-08-09 2:51:50 PM
 * @since 1.0.0
 */
public class LogUtilityTest {

    @Test
    public void testIsNull() {
        assertTrue(LogUtility.isNull(null));
    }

    @Test
    public void testIsNotNull() {
        assertTrue(LogUtility.isNotNull(new LogUtilityTest()));
    }

    @Test
    public void testIsNullOrEmpty() {
        assertTrue(LogUtility.isNullOrEmpty(null));
        assertTrue(LogUtility.isNullOrEmpty(""));
        assertTrue(LogUtility.isNullOrEmpty(new int[]{}));
        assertTrue(LogUtility.isNullOrEmpty(new ArrayList<>()));
        assertTrue(LogUtility.isNullOrEmpty(new HashMap<String, String>()));
    }

    @Test
    public void testIsNotNullOrEmpty() {
        assertTrue(LogUtility.isNotNullOrEmpty(new LogUtilityTest()));
        assertTrue(LogUtility.isNotNullOrEmpty("Rohtash Lakra"));
        assertTrue(LogUtility.isNotNullOrEmpty(Arrays.asList("Rohtash")));
        HashMap<String, String> keyValues = new HashMap<>();
        keyValues.put("firstName", "Rohtash Singh");
        assertTrue(LogUtility.isNotNullOrEmpty(keyValues));
    }

    @Test
    public void testGetPackageName() {
        assertEquals("com.devamatre.logger.testcases", LogUtility.getPackageName(LogUtilityTest.class));
        assertEquals("com.devamatre.logger.testcases", LogUtility.getPackageName(LoggerTest.class));
    }

    @Test
    public void testLogMessages() {
        LogUtility.error("error");
        LogUtility.error("error", new Error());
        LogUtility.warn("warn");
        LogUtility.warn("warn", new RuntimeException("warning"));
        LogUtility.info("info");
        LogUtility.info("info", new RuntimeException("info"));
        LogUtility.debug("debug");
        LogUtility.debug("debug", new RuntimeException("debug"));
        assertTrue(true);
    }

    @Test
    public void testPrintProperties() {
        Properties properties = new Properties();
        properties.setProperty("firstName", "Rohtash Singh");
        properties.setProperty("lastName", "Lakra");
        LogUtility.printProperties(properties, true);
        LogUtility.printProperties(properties, false);
        assertTrue(true);
    }

    @Test
    public void testPrintSystemProperties() {
        LogUtility.printSystemProperties(true);
        LogUtility.printSystemProperties(false);
        LogUtility.logSystemProperties();
        assertTrue(true);
    }

    @Test
    public void testPrint() {
        LogUtility.print(true);
        LogUtility.print(false);
        LogUtility.print("testPrint");
        LogUtility.print("testPrint", true);
        assertTrue(true);
    }

    @Test
    public void testPrintLine() {
        LogUtility.printLine('-', 10);
        assertTrue(true);
    }

    @Test
    public void testPrintLineWithHeading() {
        LogUtility.printLineWithHeading("Hello", '*', 20);
        assertTrue(true);
    }

    @Test
    public void testCloseSilently() {
        LogUtility.closeSilently(new Closeable() {
            @Override
            public void close() throws IOException {

            }
        });
        assertTrue(true);
    }

    @Test
    public void testToString() {
        String logString = LogUtility.toString(new RuntimeException("toString"));
        LogUtility.printLineWithHeading(logString, '-', 20);
        assertTrue(logString.contains("java.lang.RuntimeException: toString"));
    }

    @Test
    public void testIsArray() {
        assertFalse(LogUtility.isArray(null));
        assertTrue(LogUtility.isArray(new Object[]{}));
        assertTrue(LogUtility.isArray(new int[][]{}));
        assertTrue(LogUtility.isArray(new ArrayList[]{}));
    }

    @Test
    public void testIsPrimitiveArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{"Rohtash", "Lakra"}));
        assertTrue(LogUtility.isPrimitiveArray(new boolean[]{true}));
        assertTrue(LogUtility.isPrimitiveArray(new byte[]{1}));
        assertTrue(LogUtility.isPrimitiveArray(new char[]{'r'}));
        assertTrue(LogUtility.isPrimitiveArray(new short[]{1}));
        assertTrue(LogUtility.isPrimitiveArray(new int[]{197401}));
        assertTrue(LogUtility.isPrimitiveArray(new float[]{1974.01f}));
        assertTrue(LogUtility.isPrimitiveArray(new long[]{2000, 1974}));
        assertTrue(LogUtility.isPrimitiveArray(new double[]{1974.01}));
    }

    @Test
    public void testIsByteArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{}));
        assertTrue(LogUtility.isPrimitiveArray(new byte[]{}));
    }

    @Test
    public void testIsBooleanArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{1, 2}));
        assertTrue(LogUtility.isPrimitiveArray(new boolean[]{true}));
    }

    @Test
    public void testIsShortArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{1, 2}));
        assertTrue(LogUtility.isPrimitiveArray(new short[]{1, 2}));
    }


    @Test
    public void testIsCharArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{false}));
        assertTrue(LogUtility.isPrimitiveArray(new char[]{'r'}));
    }


    @Test
    public void testIsIntArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{}));
        assertTrue(LogUtility.isPrimitiveArray(new int[]{1}));
    }

    @Test
    public void testIsFloatArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{}));
        assertTrue(LogUtility.isPrimitiveArray(new float[]{1.0f}));
    }

    @Test
    public void testIsDoubleArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{false}));
        assertTrue(LogUtility.isPrimitiveArray(new double[]{1.0d}));
    }

    @Test
    public void testIsLongArray() {
        assertFalse(LogUtility.isPrimitiveArray(null));
        assertFalse(LogUtility.isPrimitiveArray(new Object[]{false}));
        assertTrue(LogUtility.isPrimitiveArray(new long[]{1}));
    }

    @Test
    public void testToArray() {
        Object object = null;
        Object[] result = null;
        result = LogUtility.toArray(object);
        assertNull(result);
        assertFalse(LogUtility.isArray(result));

        // char array
        object = new char[]{'r', 's', 'l'};
        result = LogUtility.toArray(object);
        assertNotNull(result);
        assertTrue(LogUtility.isArray(result));
        assertEquals(Character.class, result[0].getClass());
        assertEquals(3, result.length);

        // boolean array
        object = new boolean[]{true, false};
        result = LogUtility.toArray(object);
        assertNotNull(result);
        assertTrue(LogUtility.isArray(result));
        assertEquals(Boolean.class, result[0].getClass());
        assertEquals(2, result.length);

        // short array
        object = new short[]{1, 2};
        result = LogUtility.toArray(object);
        assertNotNull(result);
        assertTrue(LogUtility.isArray(result));
        assertEquals(Short.class, result[0].getClass());
        assertEquals(2, result.length);

        // short array
        object = new float[]{1974.01f, 2.003f};
        result = LogUtility.toArray(object);
        assertNotNull(result);
        assertTrue(LogUtility.isArray(result));
        assertEquals(Float.class, result[0].getClass());
        assertEquals(2, result.length);
    }
}
