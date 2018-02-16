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
package com.rslakra.logger;

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

/**
 * LogManager.java
 *
 * The <code>LogManager</code> class provides the generic client utilized
 * method.
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 9, 2009 2:53:33 PM
 */
public final class LogManager {
	
	/* Default Log File. */
	public final String LOG_FILE = "Logger.log";
	
	/**
	 * LEVELS
	 */
	public static final String[] LEVELS = {
			"OFF",
			"FATAL",
			"ERROR",
			"WARN",
			"INFO",
			"DEBUG",
			"ALL" };
	
	/* Default Logging Level. */
	public static final String DEFAULT_LEVEL = LEVELS[3];
	
	/* Default Logging Pattern. */
	public static final String DEFAULT_PATTERN = "%-6r [%-16.16t] %-5p [%-22.22c{1}] (%L) - %m%n";
	
	/* Default Remote Log Host. */
	public static final String DEFAULT_REMOTE_LOG_HOST = "localhost";
	
	/* Default Logger File */
	public static final String LOG4J_PROPERTY_FILE = "log4j.properties";
	
	/* log4j configuration file */
	public static final String DEFAULT_LOG4J_PROPERTY_FILE = "dLog4j.properties";
	
	/**
	 * Default Constructor.
	 */
	private LogManager() {
	}
	
	/**
	 * This method returns the Logger for the specified class.
	 *
	 * @param klass
	 * @return
	 */
	public static Logger getLogger(Class<?> klass) {
		return new LoggerImpl(Category.getInstance(klass));
	}
	
	/**
	 * Initialized Logger with default values.
	 */
	public static void configure() {
		configure(false, DEFAULT_LEVEL, DEFAULT_PATTERN, getDefaultConfigPath(), false, DEFAULT_REMOTE_LOG_HOST);
	}
	
	/**
	 * Starting Point of logger to initialize the logger with default values.
	 */
	public static void configure(String configPath) {
		if(configPath != null && !configPath.equals("")) {
			/* Initialize based on the specified configuration file. */
			configure(false, DEFAULT_LEVEL, DEFAULT_PATTERN, configPath, false, DEFAULT_REMOTE_LOG_HOST);
		} else {
			/*
			 * Get Properties form configuration file and pass in the following
			 * method
			 */
			// gives a set of defaults...
			// configure(true, "WARN", null, "/User Preferences/log",false,
			// "localhost");
			configure(false, DEFAULT_LEVEL, DEFAULT_PATTERN, getDefaultConfigPath(), false, DEFAULT_REMOTE_LOG_HOST);
		}
	}
	
	/**
	 * This method returns the default config path for the logger.
	 * 
	 * @return loggerpath
	 */
	public static String getDefaultConfigPath() {
		return getDefaultConfigPath(false);
	}
	
	/**
	 * This method returns the default configuration path for the logger.
	 * 
	 * @return absolute
	 */
	public static String getDefaultConfigPath(boolean absolute) {
		String log4jFilePath = null;
		String userDir = System.getProperty("user.dir");
		if(absolute) {
			String fileSeperator = System.getProperty("file.separator");
			String loggerPkg = LogManager.class.getPackage().getName().replace('.', '/');
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
	 * @param forceLogToConsole - true if log should be printed at console only
	 *            otherwise false.
	 *            Default value is false.
	 * @param level - level of logging e.g. WARN etc. Default value is WARN.
	 * @param pattern - logging pattern. Default value is null;
	 * @param configPath - location of "log4j.properties" file.
	 * @param isServerEnabled - true, if logs should be stored at server
	 *            otherwise false.
	 * @param remoteHost - the address of the remote server where logs should be
	 *            stored.
	 *            isServerEnabled is true, it must be provided otherwise it can
	 *            be null;
	 *            e.g.
	 *            configure(true, "WARN", null, "c:/",false, "localhost");
	 */
	public static void configure(boolean forceLogToConsole, String level, String pattern, String configPath, boolean serverEnabled, String remoteHost) {
		System.out.println("forceLogToConsole : " + forceLogToConsole + ", level : " + level + ", pattern : " + pattern + ", configPath : " + configPath + ", serverEnabled : " + serverEnabled + ", remoteHost : " + remoteHost);
		if(forceLogToConsole) {
			Category root = Category.getRoot();
			root.setLevel(getLevel(level));
			if(pattern == null) {
				pattern = DEFAULT_PATTERN;
			}
			
			System.out.println("serverEnabled : " + serverEnabled);
			/**
			 * Check RMI Server is enabled or not.
			 * If server is enabled, follow the remote logging standard,
			 * otherwise, follow the local logging standard.
			 */
			if(serverEnabled && (remoteHost != null && !remoteHost.equals(""))) {
				// remote logging; for RMI test server
				try {
					// Verify Remote Host accepts sockets on standard SysLog
					// port 514.
					String TEST_MESSAGGE = "Test Message for Remote Logging";
					System.out.println("Verifying RemoteLogHost " + remoteHost + " is accepting datagram packets on port 514...");
					DatagramSocket datagramSocket = new DatagramSocket();
					// DatagramPacket pack = new
					// DatagramPacket(TEST_MESSAGGE.getBytes(),
					// TEST_MESSAGGE.length(),
					// InetAddress.getByName(remoteHost), 514);
					datagramSocket.send(new DatagramPacket(TEST_MESSAGGE.getBytes(), TEST_MESSAGGE.length(), InetAddress.getByName(remoteHost), 514));
					datagramSocket.close();
					// Remote host is accepting on this socket, create a
					// SyslogAppender
					System.out.println("Creating Remote SyslogAppender...");
					root.addAppender(new SyslogAppender(new PatternLayout(pattern), remoteHost, SyslogAppender.LOG_USER));
				} catch(UnknownHostException e) {
					e.printStackTrace();
					System.out.println("Remote Syslog not reachable.  Creating Local FileAppender...");
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				} catch(IOException e) {
					e.printStackTrace();
					System.out.println("Remote Syslog not reachable. Creating Local FileAppender....");
					// root.addAppender(new FileAppender(new
					// PatternLayout(pattern), System.out));
				}
			} else { // local logging; standard
				try {
					root.addAppender(new ConsoleAppender(new PatternLayout(pattern)));
				} catch(Exception e) {
					// TODO: handle exception
					System.out.println("Exception:" + e);
				}
			}
		} else {
			PropertyConfigurator.configure(configPath + File.separator + LOG4J_PROPERTY_FILE);
		}
	}
	
	/**
	 * Returns the logging level based on the specified leve.
	 * 
	 * @param level
	 * @return
	 */
	private static Level getLevel(String level) {
		if(level != null) {
			if(LEVELS[0].equalsIgnoreCase(level)) {
				return Level.OFF;
			} else if(LEVELS[1].equalsIgnoreCase(level)) {
				return Level.FATAL;
			} else if(LEVELS[2].equalsIgnoreCase(level)) {
				return Level.ERROR;
			} else if(LEVELS[3].equalsIgnoreCase(level)) {
				return Level.WARN;
			} else if(LEVELS[4].equalsIgnoreCase(level)) {
				return Level.INFO;
			} else if(LEVELS[5].equalsIgnoreCase(level)) {
				return Level.DEBUG;
			} else if(LEVELS[6].equalsIgnoreCase(level)) {
				return Level.ALL;
			} else {
				Thread.dumpStack();
				throw new RuntimeException("Invalid Logger Level : " + level);
			}
		}
		
		return Level.WARN;
	}
}