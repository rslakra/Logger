/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.junit;

import java.io.File;

import com.rslakra.mock.logger.MockLogManager;

import junit.framework.TestCase;

/**
 * Provides base functionality for all unit tests.
 * This class contains <code>protected</code> methods that should be
 * overridden by derived class.
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 */
public class AbstractTestCase extends TestCase {

	/** Configure Logger */
	static {
		MockLogManager.configure();
	}

	/* Constructor */
	public AbstractTestCase () {

        /* Clean up our mess when the tests are complete. */
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    /**
                     * @see  Runnable#run()
                     */
                    public void run() {
                    	System.out.println("Shutdown Hook!");
                    	/* don't uncomment this, it will delete all files from current folder. */
//                      delete(new File("."));
                    }
                }));
	}

    /**
     * (non-Javadoc)
     *
     * @throws  Exception
     * @see     junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tests Root Directory
     */
    public void testRootPath() {
        assertTrue("E:\\Rohtash\\Data\\Examples\\Logger".equalsIgnoreCase(System.getProperty("user.dir")));
    }

    /**
     * Removes the given <code>path</code> irrespective of its contents.
     *
     * @param  path  The path to delete.
     */
    private void delete(File path) {
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
            	delete(files[i]);
            }
            files[i].delete();
        }
        path.delete();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}