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
package com.devamatre.logger;

import org.apache.log4j.helpers.OptionConverter;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * The <code>LogUtility</code> class helps to log devamatre logger logs.
 * <p>
 * Log4j can not log sometimes and then it is useful to log. You can enable
 * these internal logging by defining the <b>debugEnabled</b> property.
 * <p>
 * All log4j internal debug calls go to <code>System.out</code> where as
 * internal error messages are sent to <code>System.err</code>. All internal
 * messages are prepended with the string "log4j:".
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 2:51:50 PM
 * @since 1.0.0
 */
public final class LogUtility {

    /**
     * Defining this value makes log4j print log4j-internal debug statements to
     * <code>System.out</code>.
     *
     * <p>
     * The value of this string is <b>log4j.debug</b>.
     *
     * <p>
     * Note that the search for all option names is case sensitive.
     */
    public static final String DEBUG_KEY = "log4j.debug".intern();

    /**
     * LINE_SEPARATOR
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator").intern();

    /**
     * USER_HOME
     */
    public static final String USER_HOME = System.getProperty("user.home").intern();

    /**
     * USER_DIR
     */
    public static final String USER_DIR = System.getProperty("user.dir").intern();

    /**
     * OS_NAME
     */
    public static final String OS_NAME = System.getProperty("os.name").intern();

    /**
     * OS_VERSION
     */
    public static final String OS_VERSION = System.getProperty("os.version").intern();

    /**
     * FILE_SEPARATOR
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator").intern();

    /**
     * HTAB - Insert a tab in the text at this point.
     */
    public static final String HTAB = "\t".intern();

    /**
     * BACKSPACE - Insert a backspace in the text at this point.
     */
    public static final String BACKSPACE = "\b".intern();

    /**
     * NEWLINE - Insert a newline in the text at this point.
     */
    public static final String NEWLINE = "\n".intern();

    /**
     * CARRIAGE_RETURN - Insert a carriage return in the text at this point.
     */
    public static final String CARRIAGE_RETURN = "\r".intern();

    /**
     * FORM_FEED - Insert a formfeed in the text at this point.
     */
    public static final String FORM_FEED = "\f".intern();

    /**
     * SINGLE_QUOTE - Insert a single quote character in the text at this point.
     */
    public static final String SINGLE_QUOTE = "\'".intern();

    /**
     * DOUBLE_QUOTE - Insert a double quote character in the text at this point.
     */
    public static final String DOUBLE_QUOTE = "\"".intern();

    /**
     * BACKSLASH - Insert a backslash character in the text at this point.
     */
    public static final String BACKSLASH = "\\".intern();

    /**
     * SPACE
     */
    public static final String SPACE = " ".intern();

    /**
     * <code>DEBUG_ENABLED</code>
     */
    private static boolean DEBUG_ENABLED = false;

    /**
     * <code>LOG_ENABLED</code>
     */
    private static boolean LOG_ENABLED = true;

    /**
     * <code>CONSOLE_ENABLED</code>
     */
    private static boolean CONSOLE_ENABLED = true;

    /**
     * The <code>DISABLE_ALL</code> suppresses the logging of all the logs
     * including errors.
     */
    private static boolean DISABLE_ALL = false;

    /**
     * LOG PREFIXES.
     */
    private static final String PREFIX = "LogUtility:";

    static {
        final String debugLog4jKey = OptionConverter.getSystemProperty(DEBUG_KEY, null);
        if (!isNullOrEmpty(debugLog4jKey)) {
            setDebugEnabled(OptionConverter.toBoolean(debugLog4jKey, true));
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // ////////////////////// GETTER/SETTER METHODS ////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Returns the <code>DEBUG_ENABLED</code> value.
     *
     * @return
     */
    public static boolean isDebugEnabled() {
        return DEBUG_ENABLED;
    }

    /**
     * Allows to enable/disable log4j internal logging.
     *
     * @param debugEnabled
     */
    public static void setDebugEnabled(final boolean debugEnabled) {
        DEBUG_ENABLED = debugEnabled;
    }

    /**
     * Returns the <code>DISABLE_ALL</code> value.
     *
     * @return
     */
    public static boolean isDisableAll() {
        return DISABLE_ALL;
    }

    /**
     * In disableAll mode, all the logs are suppresses strictly no output, not
     * even for errors.
     *
     * @param disableAll
     */
    public static void setDisableAll(final boolean disableAll) {
        DISABLE_ALL = disableAll;
    }

    /**
     * Returns the <code>LOG_ENABLED</code> value.
     *
     * @return
     */
    public static boolean isLogEnabled() {
        return LOG_ENABLED;
    }

    /**
     * The <code>LOG_ENABLED</code> to be set.
     *
     * @param logEnabled
     */
    public static void setLogEnabled(final boolean logEnabled) {
        LOG_ENABLED = logEnabled;
    }

    /**
     * Returns the <code>CONSOLE_ENABLED</code> value.
     *
     * @return
     */
    public static boolean isConsoleEnabled() {
        return CONSOLE_ENABLED;
    }

    /**
     * The <code>sConsoleEnabled</code> to be set.
     *
     * @param consoleEnabled
     */
    public static void setConsoleEnabled(final boolean consoleEnabled) {
        CONSOLE_ENABLED = consoleEnabled;
    }

    // /////////////////////////////////////////////////////////////////////////
    // //////////////////////// PRIVATE METHODS ////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Returns the loggable message string.
     *
     * @param message
     * @param message
     * @param throwable
     * @return
     */
    private static String toLogString(final String logPrefix, final String message, final Throwable throwable) {
        final StringBuilder logStringBuilder = new StringBuilder();
        if (isNotNullOrEmpty(logPrefix)) {
            logStringBuilder.append(logPrefix);
        }

        if (isNotNullOrEmpty(message)) {
            logStringBuilder.append(message);
        }

        if (isNotNull(throwable)) {
            logStringBuilder.append(NEWLINE);
            logStringBuilder.append(throwable.getLocalizedMessage());
            logStringBuilder.append(NEWLINE);
            logStringBuilder.append(toString(throwable));
        }

        return logStringBuilder.toString();
    }

    // /////////////////////////////////////////////////////////////////////////
    // ///////////////////////// PUBLIC METHODS ////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Returns true if the object is null otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNull(final Object object) {
        return (object == null);
    }

    /**
     * Returns true if the object is not null otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNotNull(final Object object) {
        return (!isNull(object));
    }

    /**
     * Returns true if the <code>object</code> is null or empty otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmpty(final Object object) {
        if (isNull(object)) {
            return true;
        } else if (CharSequence.class.isAssignableFrom(object.getClass())) {
            return (((CharSequence) object).length() == 0 || ((CharSequence) object).toString().trim().length() == 0);
        } else if (object.getClass().isArray()) {
            return (Array.getLength(object) == 0);
        } else if (Collection.class.isAssignableFrom(object.getClass())) {
            return ((Collection<?>) object).isEmpty();
        } else if (Map.class.isAssignableFrom(object.getClass())) {
            return ((Map<?, ?>) object).isEmpty();
        }

        return false;
    }

    /**
     * Returns true if the <code>object</code> is neither null nor empty
     * otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNotNullOrEmpty(final Object object) {
        return (!isNullOrEmpty(object));
    }

    /**
     * Returns the name of the package of the specified <code>Class<?></code>
     * class as string. If the <code>packageNameAsDirPath</code> is true, the
     * package name '.' is replaced with 'File.separator' (i.e. '/' or '\')
     * character.
     *
     * @param klass
     * @param packageNameAsDirPath
     * @return
     */
    public static String getPackageName(final Class<?> klass, final boolean packageNameAsDirPath) {
        String packageName = (isNotNull(klass) ? klass.getPackage().getName() : null);
        packageName = (packageNameAsDirPath && !isNullOrEmpty(packageName) ? packageName.replace(".", File.separator) : packageName);

        return packageName;
    }

    /**
     * Returns the name of the package of the specified class as string.
     *
     * @param klass
     * @return
     */
    public static String getPackageName(final Class<?> klass) {
        return getPackageName(klass, false);
    }

    /**
     * Logs the internal debug logs of this logger to <code>System.out</code>.
     *
     * @param message
     */
    public static void debug(final String message) {
        debug(message, null);
    }

    /**
     * Logs the internal debug logs of this logger to <code>System.out</code>.
     *
     * @param message
     * @param throwable
     */
    public static void debug(final String message, final Throwable throwable) {
        if (isDebugEnabled() && !isDisableAll()) {
            System.out.println(toLogString(LogLevel.DEBUG.logPrefixString(PREFIX), message, throwable));
        }
    }

    /**
     * Logs the internal info logs of this logger to <code>System.out</code>.
     *
     * @param message
     */
    public static void info(final String message) {
        info(message, null);
    }

    /**
     * Logs the internal info logs of this logger to <code>System.out</code>.
     *
     * @param message
     * @param throwable
     */
    public static void info(final String message, final Throwable throwable) {
        if (!isDisableAll()) {
            System.out.println(toLogString(LogLevel.INFO.logPrefixString(PREFIX), message, throwable));
        }
    }

    /**
     * Logs the internal warn logs of this logger to <code>System.out</code>.
     *
     * @param message
     */
    public static void warn(final String message) {
        warn(message, null);
    }

    /**
     * Logs the internal warn logs of this logger to <code>System.out</code>.
     *
     * @param message
     * @param throwable
     */
    public static void warn(final String message, final Throwable throwable) {
        if (!isDisableAll()) {
            System.out.println(toLogString(LogLevel.WARN.logPrefixString(PREFIX), message, throwable));
        }
    }

    /**
     * Logs the internal error logs of this logger to <code>System.err</code>.
     * Even if you have disabled all the logs, the error logs are always logged.
     *
     * @param message
     */
    public static void error(final String message) {
        error(message, null);
    }

    /**
     * Logs the internal error logs of this logger to <code>System.err</code>.
     * Even if you have disabled all the logs, the error logs are always logged.
     *
     * @param message
     * @param throwable
     */
    public static void error(final String message, final Throwable throwable) {
        System.err.println(toLogString(LogLevel.ERROR.logPrefixString(PREFIX), message, throwable));
    }

    /**
     * Prints the <code>properties</code> all key/value.
     *
     * @param properties
     * @param printAsKeyValue
     */
    public static void printProperties(Properties properties, boolean printAsKeyValue) {
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (printAsKeyValue) {
                System.out.println("Key:" + nextElement + ", Value:" + properties.get(nextElement));
            } else {
                debug(nextElement.toString() + " = " + properties.get(nextElement));
            }
        }
    }

    /**
     * Prints all the system properties.
     *
     * @param title
     * @param printAsKeyValue
     */
    public static void printSystemProperties(String title, boolean printAsKeyValue) {
        if (isNullOrEmpty(title)) {
            printProperties(System.getProperties(), printAsKeyValue);
        } else {
            printLineWithHeading(title, '=', title.length());
            printProperties(System.getProperties(), printAsKeyValue);
            title = "End of " + title;
            printLineWithHeading(title, '=', title.length());
            print(NEWLINE);
            print(OS_NAME);
            print(OS_VERSION);
            print(USER_HOME);
        }
    }

    /**
     * Prints all the system properties.
     *
     * @param printAsKeyValue
     */
    public static void printSystemProperties(boolean printAsKeyValue) {
        printSystemProperties(null, printAsKeyValue);
    }

    /**
     * Prints the <code>object</code>.
     *
     * @param object
     */
    public static void print(Object object) {
        print(object.toString(), true);
    }

    /**
     * Prints the <code>object</code> at the new line.
     *
     * @param object
     * @param atNextLine
     */
    public static void print(Object object, boolean atNextLine) {
        print(object.toString(), null, atNextLine);
    }

    /**
     *
     */
    public static void print() {
        print("", null, true);
    }

    /**
     * @param str
     */
    public static void print(String str) {
        print(str, null, true);
    }

    /**
     * Prints the <code>object</code>.
     *
     * @param string
     * @param delimiter
     * @param atNextLine
     */
    public static void print(String string, String delimiter, boolean atNextLine) {
        if (isLogEnabled()) {
            if (isConsoleEnabled()) {
                if (atNextLine) {
                    System.out.println(string);
                } else {
                    string += (isNullOrEmpty(delimiter) ? SPACE : delimiter);
                    System.out.print(string);
                }
            } else {
                /* TODO: Write into file. */
            }
        }
    }

    /**
     * Prints the specified strings separated with the given delimiter.
     *
     * @param strings
     * @param atNextLine
     */
    public static void print(final String[] strings, String delimiter, boolean atNextLine) {
        for (int i = 0; i < strings.length; i++) {
            print(strings[i], delimiter, atNextLine);
        }
    }

    /**
     * Prints the specified strings separated with the given delimiter.
     *
     * @param strings
     * @param atNextLine
     */
    public static void print(final String[][] strings, String delimiter, boolean atNextLine) {
        for (int i = 0; i < strings.length; i++) {
            print(strings[i], delimiter, atNextLine);
            print();
        }
    }

    /**
     * Prints the specified printChar.
     *
     * @param printChar
     * @param length
     */
    public static void printLine(final char printChar, final int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(printChar);
        }
        System.out.println();
    }

    /**
     * Prints the specified printChar with the specified heading.
     *
     * @param heading
     * @param printChar
     * @param length
     */
    public static void printLineWithHeading(final String heading, final char printChar, final int length) {
        printLine(printChar, (length + heading.length() + 2));
        for (int i = 0; i < length; i++) {
            if (i == (length / 2)) {
                System.out.print(" " + heading + " ");
            }
            System.out.print(printChar);
        }
        printLine(printChar, (length + heading.length() + 2));
    }

    /**
     * Closes the specified <code>closeables</code> objects.
     *
     * @param closeables
     */
    public static void closeSilently(final Object... closeables) {
        if (isNotNull(closeables)) {
            for (Object closeable : closeables) {
                try {
                    if (closeable instanceof Closeable) {
                        ((Closeable) closeable).close();
                    } else if (closeable instanceof Socket) {
                        ((Socket) closeable).close();
                    } else if (closeable instanceof ServerSocket) {
                        ((ServerSocket) closeable).close();
                    }
                } catch (Throwable ex) {
                    error("Error on close! closeable:" + closeable, ex);
                }
            }
        }
    }

    /**
     * Returns the string representation of the specified <code>throwable</code>
     * object.
     *
     * @param throwable
     * @return
     */
    public static String toString(final Throwable throwable) {
        ByteArrayOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            outputStream = new ByteArrayOutputStream();
            printWriter = new PrintWriter(outputStream);
            throwable.printStackTrace(printWriter);
            printWriter.flush();
        } catch (Exception ex) {
            error("Error converting stack trace to string!", throwable);
        } finally {
            // close writer and outputStream
            closeSilently(printWriter, outputStream);
        }

        return new String(outputStream.toByteArray());
    }

    /**
     * Removes the contents of the given <code>path</code> recursively.
     *
     * @param rootPath The rootPath to deleted recursively.
     */
    public static void deleteRecursively(final File rootPath, final boolean logFileInfo) {
        if (rootPath.isFile()) {
            if (logFileInfo) {
                print(rootPath.getAbsolutePath());
            }
            rootPath.delete();
        } else if (rootPath.isDirectory()) {
            File[] listFiles = rootPath.listFiles();
            if (listFiles != null) {
                for (int index = 0; index < listFiles.length; index++) {
                    deleteRecursively(listFiles[index], logFileInfo);
                }
            }
            if (logFileInfo) {
                print(rootPath.getAbsolutePath());
            }
            rootPath.delete();
        }
    }

    /**
     * Prints all the system properties.
     */
    public static void logSystemProperties() {
        final Properties sysProperties = System.getProperties();
        Map.Entry<Object, Object> entry = null;
        for (Iterator<Map.Entry<Object, Object>> itr = sysProperties.entrySet().iterator(); itr.hasNext(); ) {
            entry = (Map.Entry<Object, Object>) itr.next();
            System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
            entry = null;
        }
    }

    /**
     * Returns all objects excluding the last element.
     *
     * @param objects
     * @return
     */
    public static Object[] arrayCopyWithoutThrowable(final Object[] objects) {
        if (isNullOrEmpty(objects)) {
            throw new IllegalStateException("Either empty or null array!");
        }

        final int argsLength = objects.length - 1;
        Object[] arguments = new Object[argsLength];
        if (argsLength > 0) {
            System.arraycopy(objects, 0, arguments, 0, argsLength);
        }

        return arguments;
    }

    /**
     * Returns the {@link Throwable} if an {@link Object} array contains as a last element otherwise null.
     *
     * @param objects
     * @return
     */
    public static Throwable getThrowableIfContains(final Object[] objects) {
        if (!isNullOrEmpty(objects)) {
            final Object lastEntry = objects[objects.length - 1];
            if (lastEntry instanceof Throwable) {
                return (Throwable) lastEntry;
            }
        }

        return null;
    }

}
