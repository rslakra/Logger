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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.helpers.OptionConverter;

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
 * @created 2009-08-09 2:51:50 PM
 * @version 1.0.0
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

	/** LINE_SEPARATOR */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator").intern();

	/** USER_HOME */
	public static final String USER_HOME = System.getProperty("user.home").intern();

	/** USER_DIR */
	public static final String USER_DIR = System.getProperty("user.dir").intern();

	/** OS_NAME */
	public static final String OS_NAME = System.getProperty("os.name").intern();

	/** OS_VERSION */
	public static final String OS_VERSION = System.getProperty("os.version").intern();

	/** FILE_SEPARATOR */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator").intern();

	/** HTAB - Insert a tab in the text at this point. */
	public static final String HTAB = "\t".intern();

	/** BACKSPACE - Insert a backspace in the text at this point. */
	public static final String BACKSPACE = "\b".intern();

	/** NEWLINE - Insert a newline in the text at this point. */
	public static final String NEWLINE = "\n".intern();

	/** CARRIAGE_RETURN - Insert a carriage return in the text at this point. */
	public static final String CARRIAGE_RETURN = "\r".intern();

	/** FORM_FEED - Insert a formfeed in the text at this point. */
	public static final String FORM_FEED = "\f".intern();

	/**
	 * SINGLE_QUOTE - Insert a single quote character in the text at this point.
	 */
	public static final String SINGLE_QUOTE = "\'".intern();

	/**
	 * DOUBLE_QUOTE - Insert a double quote character in the text at this point.
	 */
	public static final String DOUBLE_QUOTE = "\"".intern();

	/** BACKSLASH - Insert a backslash character in the text at this point. */
	public static final String BACKSLASH = "\\".intern();

	/** SPACE */
	public static final String SPACE = " ".intern();

	/** debugEnabled */
	private static boolean debugEnabled = false;

	/** logEnabled */
	private static boolean logEnabled = true;

	/** consoleEnabled */
	private static boolean consoleEnabled = true;

	/**
	 * The disableAll suppresses the logging of all the logs including errors.
	 */
	private static boolean disableAll = false;

	private static final String PREFIX = "LogUtility:log4j:".intern();
	private static final String ERROR = PREFIX + "ERROR ".intern();
	private static final String WARN = PREFIX + "WARN ".intern();

	static {
		String keyLog4jDebug = OptionConverter.getSystemProperty(DEBUG_KEY, null);
		if (!isNullOrEmpty(keyLog4jDebug)) {
			LogUtility.debugEnabled = OptionConverter.toBoolean(keyLog4jDebug, true);
		}
	}

	// /////////////////////////////////////////////////////////////////////////
	// ////////////////////// GETTER/SETTER METHODS ////////////////////////////
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * Allows to enable/disable log4j internal logging.
	 * 
	 * @param debugEnabled
	 */
	public static void setDebugEnabled(boolean debugEnabled) {
		LogUtility.debugEnabled = debugEnabled;
	}

	/**
	 * In disableAll mode, all the logs are suppresses strictly no output, not
	 * even for errors.
	 * 
	 * @param disableAll
	 */
	public static void setDisableAll(boolean disableAll) {
		LogUtility.disableAll = disableAll;
	}

	/**
	 * Returns the logEnabled.
	 * 
	 * @return
	 */
	public static boolean isLogEnabled() {
		return logEnabled;
	}

	/**
	 * The logEnabled to be set.
	 * 
	 * @param logEnabled
	 */
	public static void setLogEnabled(boolean logEnabled) {
		LogUtility.logEnabled = logEnabled;
	}

	/**
	 * Returns the consoleEnabled.
	 * 
	 * @return
	 */
	public static boolean isConsoleEnabled() {
		return consoleEnabled;
	}

	/**
	 * The consoleEnabled to be set.
	 * 
	 * @param consoleEnabled
	 */
	public static void setConsoleEnabled(boolean consoleEnabled) {
		LogUtility.consoleEnabled = consoleEnabled;
	}

	// /////////////////////////////////////////////////////////////////////////
	// //////////////////////// PRIVATE METHODS ////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// ///////////////////////// PUBLIC METHODS ////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * Returns true if the object is null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		return (object == null);
	}

	/**
	 * Returns true if the object is not null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNotNull(Object object) {
		return (!isNull(object));
	}

	/**
	 * Returns true if the string is null or empty otherwise false.
	 * 
	 * @param string
	 */
	public static boolean isNullOrEmpty(CharSequence charSequence) {
		return (isNull(charSequence) || charSequence.toString().isEmpty()
				|| charSequence.toString().trim().length() == 0);
	}

	/**
	 * Returns the name of the package of the specified <code>Class<?></code>
	 * class as string. If the <code>packageNameAsDirPath</code> is true, the
	 * package name '.' is replaced with 'File.separator' (i.e. '/' or '\')
	 * character.
	 * 
	 * @param klass
	 * @return
	 */
	public static String getPackageName(Class<?> klass, boolean packageNameAsDirPath) {
		String packageName = (isNotNull(klass) ? klass.getPackage().getName() : null);
		packageName = (packageNameAsDirPath && !isNullOrEmpty(packageName) ? packageName.replace(".", File.separator)
				: packageName);

		return packageName;
	}

	/**
	 * Returns the name of the package of the specified class as string.
	 * 
	 * @param klass
	 * @return
	 */
	public static String getPackageName(Class<?> klass) {
		return getPackageName(klass, false);
	}

	/**
	 * This method is used to output log4j internal debug statements. Output
	 * goes to <code>System.out</code>.
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		debug(message, null);
	}

	/**
	 * This method is used to output log4j internal debug statements. Output
	 * goes to <code>System.out</code>.
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void debug(String message, Throwable throwable) {
		if (debugEnabled && !disableAll) {
			System.out.println(PREFIX + message);
			if (null != throwable) {
				throwable.printStackTrace();
			}
		}
	}

	/**
	 * This method is used to output log4j internal error statements. There is
	 * no way to disable error statements. Output goes to
	 * <code>System.err</code>.
	 * 
	 * @param message
	 */
	public static void error(String message) {
		error(message, null);
	}

	/**
	 * This method is used to output log4j internal error statements. There is
	 * no way to disable error statements. Output goes to
	 * <code>System.err</code>.
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void error(String message, Throwable throwable) {
		if (!disableAll) {
			System.err.println(ERROR + message);
			if (null != throwable) {
				throwable.printStackTrace();
			}
		}
	}

	/**
	 * This method is used to output log4j internal warning statements. There is
	 * no way to disable warning statements. Output goes to
	 * <code>System.err</code>.
	 * 
	 * @param message
	 */
	public static void warn(String message) {
		warn(message, null);
	}

	/**
	 * This method is used to output log4j internal warnings. There is no way to
	 * disable warning statements. Output goes to <code>System.err</code>.
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void warn(String message, Throwable throwable) {
		if (!disableAll) {
			System.err.println(WARN + message);
			if (null != throwable) {
				throwable.printStackTrace();
			}
		}
	}

	/**
	 * Prints the <code>properties</code> all key/value.
	 * 
	 * @param prop
	 */
	public static void printProperties(Properties properties, boolean printAsKeyValue) {
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			Object nextElement = keys.nextElement();
			if (printAsKeyValue) {
				debug("Key:" + nextElement + ", Value:" + properties.get(nextElement));
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
	 * @param obj
	 * @param atNextLine
	 */
	public static void print(Object object, boolean atNextLine) {
		print(object.toString(), null, atNextLine);
	}

	/**
	 * 
	 * @param str
	 */
	public static void print() {
		print("", null, true);
	}

	/**
	 * 
	 * @param str
	 */
	public static void print(String str) {
		print(str, null, true);
	}

	/**
	 * 
	 * @param str
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
	 * Closes the specified <code>writer</code> writer, if it's not null;
	 * 
	 * @param writer
	 */
	public static void close(Writer writer) {
		// close outputStream
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Closes the specified <code>outputStream</code> stream, if it's not null;
	 * 
	 * @param outputStream
	 */
	public static void close(OutputStream outputStream) {
		// close outputStream
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
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
	public static String stackTraceAsString(Throwable throwable) {
		ByteArrayOutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			outputStream = new ByteArrayOutputStream();
			printWriter = new PrintWriter(outputStream);
			throwable.printStackTrace(printWriter);
			printWriter.flush();
		} catch (Exception ex) {
			error("Error converting stack trace to string.", throwable);
		} finally {
			// close writer
			close(printWriter);
			// close outputStream
			close(outputStream);
		}

		return new String(outputStream.toByteArray());
	}

	/**
	 * Removes the contents of the given <code>path</code> recursively.
	 *
	 * @param rootPath
	 *            The rootPath to deleted recursively.
	 */
	public static void deleteRecursively(File rootPath, boolean logFileInfo) {
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
	 * 
	 */
	public static void dumpSystemProperties() {
		Properties props = System.getProperties();
		for (Iterator<Map.Entry<Object, Object>> iter = props.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iter.next();
			System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
		}
	}

}
