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
