/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.junit.logger;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import com.rslakra.logger.LogManager;
import com.rslakra.logger.Logger;

/**
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 */
public class TestLogger {

    private static Logger logger;

    public static void main(String[] args) {
//        printSystemProperties();

//    	LogManager.configure();
//    	LogManager.configure("E:");
    	LogManager.configure(LogManager.getDefaultConfigPath(false) + File.separator + LogManager.DEFAULT_LOG4J_PROPERTY_FILE);
//    	LogManager.configure(true, null,null,null,false,null);
        logger = LogManager.getLogger(TestLogger.class);

        logger.info("=================================================");
        logger.info("This is My logger testing.");
        logger.warn("My Logger Warning");
        logger.debug("My logger debug testing.");
        if(logger.isDebugEnabled()) {
            logger.debug("LOGGER IS ENABLED ... :)");
        }
        messages();
        logger.error("My Logger error testing");
        logger.fatal("My Logger Fatal Testing.");
        if(logger.isDebugEnabled()) {
            logger.info("Debugging Enabled!");
        }
        messages3();
        if(logger.isInfoEnabled()) {
            logger.info("Info Enabled!");
        }

        logger.info("=================================================");
    }

    private static void messages() {
        logger.info("=================================================");
        logger.info("messages Method Call!");
        messages1();
        logger.info("=================================================");
    }

    private static void messages1() {
        logger.info("=================================================");
        logger.info("messages1 Method Call!");
        messages2();
        messages5();
        logger.info("=================================================");
    }

    private static void messages2() {
        logger.info("=================================================");
        logger.info("messages2 Method Call!");
        logger.info("=================================================");
    }

    private static void messages3() {
        logger.info("messages3 Method Call!");
    }

    private static void messages4() {
        logger.info("messages4 Method Call!");
    }

    private static void messages5() {
        logger.info("messages5 Method Call!");
        messages3();
        messages4();
    }

    /**
     * Prints System Properties
     */
    private static void printSystemProperties() {
        Properties prop = System.getProperties();
        Iterator itr = prop.keySet().iterator();
        while (itr.hasNext()) {
            String key = (String)itr.next();
            System.out.println("key : " + key + ", Value : " + System.getProperty(key));
        }
    }

}
