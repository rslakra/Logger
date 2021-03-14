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

import com.devamatre.logger.LogFormatter;
import com.devamatre.logger.LogTuple;
import com.devamatre.logger.LogUtility;
import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 07, 2021 10:34:10
 */
public class LogFormatterTest {

    @Test
    public void testNormalize() {
        LogUtility.setLogEnabled(true);
        LogUtility.setConsoleEnabled(true);
        LogTuple logTuple = LogFormatter.normalize("LogTuple");
        LogUtility.print(logTuple.toMessage());

        logTuple = LogFormatter.normalize("Line: {}", new Object[]{new Integer(10)});
        LogUtility.print(logTuple.toMessage());

        logTuple = LogFormatter.normalize("First Name: {}, Last Name: {}", new Object[]{"Rohtash Singh", "Lakra"});
        LogUtility.print(logTuple.toMessage());

        logTuple = LogFormatter.normalize("First Name: {}, Last Name: {}", new Object[]{"Rohtash Singh", "Lakra"}, new RuntimeException("LogTuple"));
        LogUtility.print(logTuple.toMessage());

        logTuple = LogFormatter.normalize("First Name: {}, Last Name: {}, Exception: {}", new Object[]{"Rohtash Singh", "Lakra", new RuntimeException("Objects")});
        LogUtility.print(logTuple.toMessage());

    }

}
