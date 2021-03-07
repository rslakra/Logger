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
    private static final char STOP_DELIMITER = '}';
    private static final String DELIMITER_PAIR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * Returns the <code>LogTuple</code> for the provided <code>messagePattern</code> and
     * <code>argument</code> parameters.
     *
     * @param messagePattern
     * @param argument
     * @return
     */
    public static final LogTuple format(final String messagePattern, final Object argument) {
        return arrayFormat(messagePattern, new Object[]{argument});
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
    public static final LogTuple format(final String messagePattern, Object argument, Object throwable) {
        return arrayFormat(messagePattern, new Object[]{argument, throwable});
    }

    /**
     * @param messagePattern
     * @param arguments
     * @return
     */
    public static final LogTuple arrayFormat(final String messagePattern, final Object[] arguments) {
        Throwable throwable = LogUtility.getThrowableIfContains(arguments);
        Object[] args = arguments;
        if (throwable != null) {
            args = LogUtility.arrayCopyExcludingLastElement(arguments);
        }

        return arrayFormat(messagePattern, args, throwable);
    }

    /**
     * Assumes that objects only contains arguments with no throwable as last element.
     *
     * @param messagePattern
     * @param arguments
     */
    public static final String basicArrayFormat(final String messagePattern, final Object[] arguments) {
        return arrayFormat(messagePattern, arguments, null).getMessage();
    }

    /**
     * @param logTuple
     * @return
     */
    public static final String basicArrayFormat(final LogTuple logTuple) {
        return basicArrayFormat(logTuple.getMessage(), logTuple.getArguments());
    }

    /**
     * @param messagePattern
     * @param objects
     * @param throwable
     * @return
     */
    public static final LogTuple arrayFormat(final String messagePattern, final Object[] objects, Throwable throwable) {
        if (LogUtility.isNull(messagePattern)) {
            return new LogTuple(null, objects, throwable);
        }

        if (objects == null) {
            return new LogTuple(messagePattern);
        }

        int i = 0;
        int j;
        // use string builder for better performance
        final StringBuilder sBuilder = new StringBuilder(messagePattern.length() + 100);
        for (int index = 0; index < objects.length; index++) {
            j = messagePattern.indexOf(DELIMITER_PAIR, i);
            if (j == -1) {
                // no more variables
                if (i == 0) { // this is a simple string
                    return new LogTuple(messagePattern, objects, throwable);
                } else { // add the tail string which contains no variables and return
                    // the result.
                    sBuilder.append(messagePattern, i, messagePattern.length());
                    return new LogTuple(sBuilder.toString(), objects, throwable);
                }
            } else {
                if (isDelimiterEscaped(messagePattern, j)) {
                    if (!isDoubleEscaped(messagePattern, j)) {
                        index--; // START_DELIMITER was escaped, thus should not be incremented
                        sBuilder.append(messagePattern, i, j - 1);
                        sBuilder.append(START_DELIMITER);
                        i = j + 1;
                    } else {
                        // The escape character preceding the delimiter start is
                        // itself escaped: "message x:\\{}", so consume one backward slash
                        sBuilder.append(messagePattern, i, j - 1);
                        deeplyAppendParameter(sBuilder, objects[index], new HashMap<Object[], Object>());
                        i = j + 2;
                    }
                } else {
                    // normal case
                    sBuilder.append(messagePattern, i, j);
                    deeplyAppendParameter(sBuilder, objects[index], new HashMap<Object[], Object>());
                    i = j + 2;
                }
            }
        }

        // append the characters following the last {} pair.
        sBuilder.append(messagePattern, i, messagePattern.length());
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
     * @param seenMap
     */
    private static void appendObjects(final StringBuilder sBuilder, Object[] objects, Map<Object[], Object> seenMap) {
        sBuilder.append('[');
        if (!seenMap.containsKey(objects)) {
            seenMap.put(objects, null);
            final int len = objects.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sBuilder, objects[i], seenMap);
                if (i != len - 1) {
                    sBuilder.append(", ");
                }
            }
            // allow repeats in siblings
            seenMap.remove(objects);
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
     * attempts to extract it from the argument array.
     *
     * @param message
     * @param arguments
     * @param throwable
     * @return
     */
    public static LogTuple normalize(String message, Object[] arguments, Throwable throwable) {
        if (throwable != null) {
            return new LogTuple(message, arguments, throwable);
        }

        if (LogUtility.isNullOrEmpty(arguments)) {
            return new LogTuple(message, arguments, throwable);
        }

        Throwable throwableCandidate = LogUtility.getThrowableIfContains(arguments);
        if (throwableCandidate != null) {
            Object[] trimmedArguments = LogUtility.arrayCopyExcludingLastElement(arguments);
            return new LogTuple(message, trimmedArguments, throwableCandidate);
        } else {
            return new LogTuple(message, arguments);
        }
    }
}
