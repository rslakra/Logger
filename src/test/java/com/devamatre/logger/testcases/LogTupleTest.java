package com.devamatre.logger.testcases;

import com.devamatre.logger.LogFormatter;
import com.devamatre.logger.LogTuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 13, 2021 19:55:27
 */
public class LogTupleTest {

    @Test
    public void testToMessage() {
        LogTuple logTuple = LogFormatter.format("Testing: {}", Integer.valueOf(10), new Exception("LogTuple"));
        assertNotNull(logTuple);
    }

}
