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

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Category;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.net.SyslogAppender;

/**
 * The <code>LogManager</code> class provides the generic client utilized
 * method.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2009-08-09 2:51:50 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public final class LogManager {

	/* Default Log File. */
	public final String LOG_FILE = "dLogger.log".intern();

	/* Default Logging Level. */
	public static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.WARN;

	/* devamatre Default Logging Pattern. */
	public static final String DEFAULT_PATTERN = "[%d{MM-dd-yyyy hh:mm:ss a}] [%t] %5p [%c{1}(%L)] - %m%n";

	/* Default Remote Log Host. */
	public static final String DEFAULT_REMOTE_HOST = "localhost".intern();

	/* Default Logger File */
	public static final String LOG4J_PROPERTY_FILE = "dLog4j.properties";

	/* devamatre log4j configuration file */
	public static final String LOG4J_XML_FILE = "dLog4j.xml";

	/* Remote Log Host Port */
	public static final int REMOTE_LOG_HOST_PORT = 514;

	/* cached logger. */
	private static final Map<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();

	/**
	 * Default Constructor which blocks to create objects outside of this class.
	 */
	private LogManager() {
		throw new UnsupportedOperationException("Object instantiation is not allowed!");
	}

	/**
	 * This method returns the <code>Logger</code> object for the
	 * <code>Class<?></code> class. By default, the <code>debugEnabled</code> is
	 * true for that <code>Logger</code> object but can be set to false, if
	 * required.
	 * 
	 * @param klass
	 * @param debugEnabled
	 * @return
	 */
	public static Logger getLogger(Class<?> logClass, boolean debugEnabled) {
		Logger logger = null;
		synchronized (LogManager.class) {
			String className = (LogUtility.isNull(logClass) ? NullLogger.class.getName() : logClass.getName());
			logger = loggers.get(className);
			if (LogUtility.isNull(logger)) {
				if (LogUtility.isNull(logClass)) {
					logger = new NullLogger();
				} else {
					logger = new LoggerImpl(logClass);
				}

				/* cache this class logger to reuse */
				logger.setDebugEnabled(debugEnabled);

				/* cache this class logger to reuse */
				loggers.put(className, logger);
			}
		}

		return logger;
	}

	/**
	 * This method returns the Logger for the specified class.
	 * 
	 * @param klass
	 * @return
	 */
	public static Logger getLogger(Class<?> klass) {
		return getLogger(klass, true);
	}

	/**
	 * Starting Point of logger to initialize the logger with default values.
	 * 
	 * @param log4jFilePath
	 */
	public static void configure(String log4jFilePath) {
		if (LogUtility.isNullOrEmpty(log4jFilePath)) {
			/*
			 * Get Properties form configuration file and pass in the following
			 * method. gives a set of defaults... configure(true, "WARN", null,
			 * "/User Preferences/log",false, "localhost");
			 */
			log4jFilePath = getDefaultConfigPath() + File.separator + LOG4J_PROPERTY_FILE;
		} else if (LOG4J_XML_FILE.equals(log4jFilePath.trim()) || LOG4J_PROPERTY_FILE.equals(log4jFilePath)) {
			log4jFilePath = getDefaultConfigPath() + File.separator + log4jFilePath;
		}

		/* Initialize based on the specified configuration file. */
		configure(false, null, null, log4jFilePath, false, null);
	}

	/**
	 * This method returns the default configuration path for the logger.
	 * 
	 * @return String
	 */
	private static String getDefaultConfigPath() {
		return getDefaultConfigPath(false);
	}

	/**
	 * This method returns the default configuration path for the logger. If
	 * absolute is true, it tries to find the log4j.properties file under the
	 * same package which is of LogManager.java class, otherwise, it returns the
	 * 'user.dir' path.
	 * 
	 * @return String
	 */
	public static String getDefaultConfigPath(boolean absolute) {
		String log4jFilePath = LogUtility.USER_DIR;
		if (absolute) {
			String packageNameAsDirPath = LogUtility.getPackageName(LogManager.class, true);
			LogUtility.debug("packageNameAsDirPath:" + packageNameAsDirPath);
			log4jFilePath += LogUtility.FILE_SEPARATOR + "src" + LogUtility.FILE_SEPARATOR + packageNameAsDirPath;
		}

		LogUtility.debug("log4jFilePath:" + log4jFilePath);
		return log4jFilePath;
	}

	/**
	 * Configures the logger with the specified logging level.
	 * 
	 * @param forceLogToConsole
	 * @param level
	 */
	public static void configure(boolean forceLogToConsole, LogLevel level) {
		configure(forceLogToConsole, level, null, null, false, null);
	}

	/**
	 * Starting Point of logger to initialize the logger with the specified
	 * values.
	 * 
	 * @param forceLogToConsole
	 *            - true if log should be printed at console only otherwise
	 *            false. Default value is false.
	 * @param level
	 *            - level of logging e.g. WARN etc. Default value is WARN.
	 * @param pattern
	 *            - logging pattern. Default value is null;
	 * @param log4jFile
	 *            - location of "log4j.properties" file.
	 * @param isServerEnabled
	 *            - true, if logs should be stored at server otherwise false.
	 * @param remoteHost
	 *            - the address of the remote server where logs should be
	 *            stored. isServerEnabled is true, it must be provided otherwise
	 *            it can be null; e.g. configure(true, "WARN", null,
	 *            "c:/",false, "localhost");
	 */
	public static void configure(boolean forceLogToConsole, LogLevel level, String pattern, String log4jFile,
			boolean serverEnabled, String remoteHost) {
		LogUtility.debug("+configure() - forceLogToConsole:" + forceLogToConsole + ", level:" + level + ", pattern:"
				+ pattern + ", log4jFile:" + log4jFile + ", serverEnabled:" + serverEnabled + ", remoteHost:"
				+ remoteHost);
		if (forceLogToConsole) {
			Category root = org.apache.log4j.LogManager.getRootLogger();
			root.setLevel(getLevel(level));
			if (LogUtility.isNullOrEmpty(pattern)) {
				pattern = DEFAULT_PATTERN;
			}

			/**
			 * Check RMI Server is enabled or not. If server is enabled, follow
			 * the remote logging standard, otherwise, follow the local logging
			 * standard.
			 */
			if (serverEnabled && !LogUtility.isNullOrEmpty(remoteHost)) {
				// remote logging; for RMI test server
				try {
					/*
					 * Verify Remote Host accepts sockets on standard SysLog
					 * port 514.
					 */
					String TEST_MESSAGGE = "Test Message for Remote Logging";
					LogUtility.debug("RemoteLogHost [" + remoteHost + "] is accepting datagram packets on port 514...");
					DatagramSocket datagramSocket = new DatagramSocket();
					// DatagramPacket pack = new
					// DatagramPacket(TEST_MESSAGGE.getBytes(),
					// TEST_MESSAGGE.length(),
					// InetAddress.getByName(remoteHost), 514);
					datagramSocket.send(new DatagramPacket(TEST_MESSAGGE.getBytes(), TEST_MESSAGGE.length(),
							InetAddress.getByName(remoteHost), REMOTE_LOG_HOST_PORT));
					datagramSocket.close();
					// Remote host is accepting on this socket, create a
					// SyslogAppender
					LogUtility.debug("Creating Remote SyslogAppender...");
					root.addAppender(
							new SyslogAppender(new PatternLayout(pattern), remoteHost, SyslogAppender.LOG_USER));
				} catch (UnknownHostException ex) {
					LogUtility.debug("Remote Syslog not reachable.  Creating Local FileAppender...", ex);
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				} catch (IOException ex) {
					LogUtility.debug("Remote Syslog not reachable. Creating Local FileAppender....", ex);
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				}
			} else { // local logging; standard
				try {
					root.addAppender(new ConsoleAppender(new PatternLayout(pattern)));
				} catch (Exception ex) {
					LogUtility.error(ex.getLocalizedMessage(), ex);
				}
			}
		} else {
			/* loads the provided log4j file. */
			PropertyConfigurator.configure(log4jFile);
		}
	}

	/**
	 * Returns the logging level based on the specified level.
	 * 
	 * @param logLevel
	 * @return
	 */
	private static Level getLevel(LogLevel logLevel) {
		switch (logLevel) {
		case OFF:
			return Level.OFF;
		case FATAL:
			return Level.FATAL;
		case ERROR:
			return Level.ERROR;
		case WARN:
			return Level.WARN;
		case INFO:
			return Level.INFO;
		case DEBUG:
			return Level.DEBUG;
		case ALL:
			return Level.ALL;
		default:
			Thread.dumpStack();
			throw new RuntimeException("Invalid logLevel:" + logLevel);
		}
	}
}