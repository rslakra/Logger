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
