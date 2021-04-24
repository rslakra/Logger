/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2021. All rights reserved.
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
package com.devamatre.logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 06, 2021 20:46:28
 */
public final class LogFormatter {

    private static final char START_DELIMITER = '{';
    private static final String DELIMITER_PAIR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * @param messagePattern
     * @param objects
     * @param throwable
     * @return
     */
    public static final LogTuple logFormatter(final String messagePattern, final Object[] objects, Throwable throwable) {
        if (LogUtility.isNull(messagePattern)) {
            return new LogTuple(null, objects, throwable);
        }

        if (LogUtility.isNullOrEmpty(objects)) {
            return new LogTuple(messagePattern);
        }

        int fromIndex = 0;
        int pairIndex;
        // use string builder for better performance
        final StringBuilder sBuilder = new StringBuilder(messagePattern.length() + 100);
        for (int index = 0; index < objects.length; index++) {
            pairIndex = messagePattern.indexOf(DELIMITER_PAIR, fromIndex);
            if (pairIndex == -1) {
                // no more variables
                if (fromIndex == 0) { // this is a simple string
                    return new LogTuple(messagePattern, objects, throwable);
                } else { // add the tail string which contains no variables and return
                    // the result.
                    sBuilder.append(messagePattern, fromIndex, messagePattern.length());
                    return new LogTuple(sBuilder.toString(), objects, throwable);
                }
            } else {
                if (isDelimiterEscaped(messagePattern, pairIndex)) {
                    if (!isDoubleEscaped(messagePattern, pairIndex)) {
                        index--; // START_DELIMITER was escaped, thus should not be incremented
                        sBuilder.append(messagePattern, fromIndex, pairIndex - 1);
                        sBuilder.append(START_DELIMITER);
                        fromIndex = pairIndex + 1;
                    } else {
                        // The escape character preceding the delimiter start is
                        // itself escaped: "message x:\\{}", so consume one backward slash
                        sBuilder.append(messagePattern, fromIndex, pairIndex - 1);
                        deeplyAppendParameter(sBuilder, objects[index], new HashMap<Object[], Object>());
                        fromIndex = pairIndex + 2;
                    }
                } else {
                    // normal case
                    sBuilder.append(messagePattern, fromIndex, pairIndex);
                    deeplyAppendParameter(sBuilder, objects[index], new HashMap<Object[], Object>());
                    fromIndex = pairIndex + 2;
                }
            }
        }

        // append the characters following the last {} pair.
        sBuilder.append(messagePattern, fromIndex, messagePattern.length());
        return new LogTuple(sBuilder.toString(), objects, throwable);
    }

    /**
     * @param messagePattern
     * @param startIndex
     * @return
     */
    public static final boolean isDelimiterEscaped(final String messagePattern, final int startIndex) {
        return (startIndex > 0 && ESCAPE_CHAR == messagePattern.charAt(startIndex - 1));
    }

    /**
     * @param messagePattern
     * @param startIndex
     * @return
     */
    public static final boolean isDoubleEscaped(final String messagePattern, final int startIndex) {
        return (startIndex >= 2 && ESCAPE_CHAR == messagePattern.charAt(startIndex - 2));
    }

    /**
     * @param sBuilder
     * @param object
     * @param arguments
     */
    private static void deeplyAppendParameter(final StringBuilder sBuilder, Object object, final Map<Object[], Object> arguments) {
        if (object == null) {
            sBuilder.append("null");
            return;
        }

        if (!object.getClass().isArray()) {
            safeObjectAppend(sBuilder, object);
        } else {
            // check for primitive array types because they unfortunately cannot be cast to Object[]
            if (object instanceof boolean[]) {
                appendBooleanArray(sBuilder, (boolean[]) object);
            } else if (object instanceof byte[]) {
                appendByteArray(sBuilder, (byte[]) object);
            } else if (object instanceof char[]) {
                appendCharArray(sBuilder, (char[]) object);
            } else if (object instanceof short[]) {
                appendShortArray(sBuilder, (short[]) object);
            } else if (object instanceof int[]) {
                appendIntArray(sBuilder, (int[]) object);
            } else if (object instanceof long[]) {
                appendLongArray(sBuilder, (long[]) object);
            } else if (object instanceof float[]) {
                appendFloatArray(sBuilder, (float[]) object);
            } else if (object instanceof double[]) {
                appendDoubleArray(sBuilder, (double[]) object);
            } else {
                appendObjects(sBuilder, (Object[]) object, arguments);
            }
        }
    }

    /**
     * @param sBuilder
     * @param object
     */
    private static void safeObjectAppend(StringBuilder sBuilder, Object object) {
        try {
            sBuilder.append(object.toString());
        } catch (Throwable throwable) {
            LogUtility.print("Failed toString() invocation of an object of type [" + object.getClass().getName() + "], throwable: " + throwable);
            sBuilder.append("[Failed toString()]");
        }
    }

    /**
     * @param sBuilder
     * @param objects
     * @param visitedMap
     */
    private static void appendObjects(final StringBuilder sBuilder, final Object[] objects, final Map<Object[], Object> visitedMap) {
        sBuilder.append('[');
        if (!visitedMap.containsKey(objects)) {
            visitedMap.put(objects, null);
            final int len = objects.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sBuilder, objects[i], visitedMap);
                if (i != len - 1) {
                    sBuilder.append(", ");
                }
            }
            // allow repeats in siblings
            visitedMap.remove(objects);
        } else {
            sBuilder.append("...");
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendBooleanArray(final StringBuilder sBuilder, boolean[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendByteArray(final StringBuilder sBuilder, byte[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendCharArray(final StringBuilder sBuilder, char[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendShortArray(final StringBuilder sBuilder, short[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendIntArray(final StringBuilder sBuilder, int[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendLongArray(final StringBuilder sBuilder, long[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendFloatArray(final StringBuilder sBuilder, float[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * @param sBuilder
     * @param array
     */
    private static void appendDoubleArray(final StringBuilder sBuilder, double[] array) {
        sBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            sBuilder.append(array[i]);
            if (i != len - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(']');
    }

    /**
     * Normalizes the logging parameters. If a throwable argument is not provided directly, it
     * attempts to extract it from the arguments array.
     *
     * @param messagePattern
     * @param arguments
     * @param throwable
     * @return
     */
    public static LogTuple normalize(final String messagePattern, Object[] arguments, Throwable throwable) {
        if (LogUtility.isNotNull(throwable)) {
            return new LogTuple(messagePattern, arguments, throwable);
        } else if (LogUtility.isNotNullOrEmpty(arguments)) {
            throwable = LogUtility.getThrowableIfContains(arguments);
            if (LogUtility.isNotNull(throwable)) {
                // TODO: Check if truncate the message also or not.
//                arguments = LogUtility.arrayCopyWithoutThrowable(arguments);
                return new LogTuple(messagePattern, arguments, throwable);
            } else {
                return new LogTuple(messagePattern, arguments);
            }
        } else {
            return new LogTuple(messagePattern);
        }
    }

    /**
     * @param messagePattern
     * @param arguments
     * @return
     */
    public static LogTuple normalize(final String messagePattern, Object... arguments) {
        return normalize(messagePattern, arguments, null);
    }

    /**
     * Returns the <code>LogTuple</code> for the provided <code>messagePattern</code>, <code>argument</code> and
     * <code>throwable</code>  parameters.
     *
     * @param messagePattern
     * @param argument
     * @param throwable
     * @return
     */
    public static LogTuple format(final String messagePattern, Object argument, Object throwable) {
        return logFormatter(messagePattern, new Object[]{argument, throwable}, null);
    }

    /**
     * Assumes that objects only contains arguments with no throwable as last element.
     *
     * @param messagePattern
     * @param arguments
     */
    public static String format(final String messagePattern, final Object[] arguments) {
        return logFormatter(messagePattern, arguments, null).toMessage();
    }

    /**
     * @param logTuple
     * @return
     */
    public static String format(final LogTuple logTuple) {
        return logFormatter(logTuple.getMessage(), logTuple.getArguments(), logTuple.getThrowable()).toMessage();
    }

}
