package com.devamatre.logger.testcases;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 06, 2021 17:06:46
 */
public class JUnit4Test {

    @Test
    public void testIntegerSum() {
        assertEquals(17, Integer.sum(16, 01));
    }
}
