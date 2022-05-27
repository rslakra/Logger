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

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 06, 2021 17:12:25
 */
public class JUnit5TagTest {

    @Tag("slow")
    @Test
    public void testAddMaxInteger() {
        assertEquals(2147383646, Integer.sum(2147183646, 200000));
    }

    @Tag("fast")
    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(16, 0);
        });
    }
}
