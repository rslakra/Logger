/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.logger.mock;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Category;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.net.SyslogAppender;

import com.devamatre.logger.Logger;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-16 04:53:40 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MockLogManager {

	/* Default Log File. */
	public final String LOG_FILE = "Default.log";

	/* Default Logging Level. */
	public static final String DEFAULT_LEVEL = "WARN";

	/* Lakra Default Logging Pattern. */
	public static final String LAKRA_DEFAULT_PATTERN = "%-6r [%-16.16t] %-5p [%-22.22c{1}] (%L) - %m%n";

	/* Default Remote Log Host. */
	public static final String DEFAULT_REMOTE_LOG_HOST = "localhost";

	/* Default Logger File */
	public static final String LOG4J_PROPERTY_FILE = "log4j.properties";

	/**
	 * Default Constructor.
	 */
	private MockLogManager() {
	}

	/**
	 * This method returns the Logger for the specified class.
	 *
	 * @param classType
	 * @return
	 */
	public static Logger getLogger(Class<?> classType) {
		return new MockLoggerImpl(Category.getInstance(classType));
	}

	/**
	 * Initialized Logger with default values.
	 */
	public static void configure() {
		configure(false, DEFAULT_LEVEL, LAKRA_DEFAULT_PATTERN, getDefaultConfigPath(), false, DEFAULT_REMOTE_LOG_HOST);
	}

	/**
	 * Starting Point of logger to initialize the logger with default values.
	 */
	public static void configure(String configPath) {
		if (configPath != null && !configPath.equals("")) {
			/* Initialize based on the specified configuration file. */
			configure(false, DEFAULT_LEVEL, LAKRA_DEFAULT_PATTERN, configPath, false, DEFAULT_REMOTE_LOG_HOST);
		} else {
			/*
			 * Get Properties form configuration file and pass in the following
			 * method gives a set of defaults... configure(true, "WARN", null,
			 * "/User Preferences/log",false,"localhost");
			 */
			configure(false, DEFAULT_LEVEL, LAKRA_DEFAULT_PATTERN, getDefaultConfigPath(), false,
					DEFAULT_REMOTE_LOG_HOST);
		}
	}

	/**
	 * This method returns the default configuration path for the logger.
	 *
	 * @return loggerpath
	 */
	private static String getDefaultConfigPath() {
		return getDefaultConfigPath(false);
	}

	/**
	 * This method returns the default config path for the logger.
	 *
	 * @return loggerpath
	 */
	private static String getDefaultConfigPath(boolean absolute) {
		String log4jFilePath = null;
		String userDir = System.getProperty("user.dir");
		if (absolute) {
			String fileSeperator = System.getProperty("file.separator");
			String loggerPkg = MockLogManager.class.getPackage().getName().replace('.', '/');
			log4jFilePath = userDir + fileSeperator + fileSeperator + "src" + fileSeperator + loggerPkg;
		} else {
			log4jFilePath = userDir;
		}
		System.out.println("log4jFilePath: " + log4jFilePath);
		return log4jFilePath;
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
	 * @param configPath
	 *            - location of "log4j.properties" file.
	 * @param isServerEnabled
	 *            - true, if logs should be stored at server otherwise false.
	 * @param remoteHost
	 *            - the address of the remote server where logs should be
	 *            stored. isServerEnabled is true, it must be provided otherwise
	 *            it can be null; e.g. configure(true, "WARN", null,
	 *            "c:/",false, "localhost");
	 */
	public static void configure(boolean forceLogToConsole, String level, String pattern, String configPath,
			boolean serverEnabled, String remoteHost) {
		System.out.println("forceLogToConsole : " + forceLogToConsole + ", level : " + level + ", pattern : " + pattern
				+ ", configPath : " + configPath + ", serverEnabled : " + serverEnabled + ", remoteHost : "
				+ remoteHost);
		if (forceLogToConsole) {
			Category root = Category.getRoot();
			root.setLevel(getLevel(level));
			if (pattern == null) {
				pattern = LAKRA_DEFAULT_PATTERN;
			}

			System.out.println("serverEnabled : " + serverEnabled);
			/**
			 * Check RMI Server is enabled or not. If server is enabled, follow
			 * the remote logging standard, otherwise, follow the local logging
			 * standard.
			 */
			if (serverEnabled && (remoteHost != null && !remoteHost.equals(""))) {
				// remote logging; for RMI test server
				try {
					// Verify Remote Host accepts sockets on standard SysLog
					// port 514.
					String TEST_MESSAGGE = "Test Message for Remote Logging";
					System.out.println(
							"Verifying RemoteLogHost " + remoteHost + " is accepting datagram packets on port 514...");
					DatagramSocket datagramSocket = new DatagramSocket();
					// DatagramPacket pack = new
					// DatagramPacket(TEST_MESSAGGE.getBytes(),
					// TEST_MESSAGGE.length(),
					// InetAddress.getByName(remoteHost), 514);
					datagramSocket.send(new DatagramPacket(TEST_MESSAGGE.getBytes(), TEST_MESSAGGE.length(),
							InetAddress.getByName(remoteHost), 514));
					datagramSocket.close();
					// Remote host is accepting on this socket, create a
					// SyslogAppender
					System.out.println("Creating Remote SyslogAppender...");
					root.addAppender(
							new SyslogAppender(new PatternLayout(pattern), remoteHost, SyslogAppender.LOG_USER));
				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.err.println("Remote Syslog not reachable.  Creating Local FileAppender...");
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Remote Syslog not reachable. Creating Local FileAppender....");
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				}
			} else { // local logging; standard
				try {
					root.addAppender(new ConsoleAppender(new PatternLayout(pattern)));
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Exception:" + e);
				}
			}
		} else {
			PropertyConfigurator.configure(configPath + File.separator + LOG4J_PROPERTY_FILE);
		}
	}

	/**
	 * Returns the logging level based on the specified level.
	 *
	 * @param level
	 * @return
	 */
	private static Level getLevel(String level) {
		if (level != null) {
			if ("OFF".equalsIgnoreCase(level)) {
				return Level.OFF;
			} else if ("FATAL".equalsIgnoreCase(level)) {
				return Level.FATAL;
			} else if ("ERROR".equalsIgnoreCase(level)) {
				return Level.ERROR;
			} else if ("WARN".equalsIgnoreCase(level)) {
				return Level.WARN;
			} else if ("INFO".equalsIgnoreCase(level)) {
				return Level.INFO;
			} else if ("DEBUG".equalsIgnoreCase(level)) {
				return Level.DEBUG;
			} else if ("ALL".equalsIgnoreCase(level)) {
				return Level.ALL;
			} else {
				Thread.dumpStack();
				throw new RuntimeException("Invalid Logger Level : " + level);
			}
		} // end if
		return Level.WARN;
	}
}