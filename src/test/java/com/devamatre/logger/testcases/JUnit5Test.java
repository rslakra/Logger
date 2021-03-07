package com.devamatre.logger.testcases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 06, 2021 16:56:25
 */
public class JUnit5Test {

    @Test
    public void testIntegerSum() {
        assertEquals(17, Integer.sum(16, 01));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(16, 0);
        });
    }
}
