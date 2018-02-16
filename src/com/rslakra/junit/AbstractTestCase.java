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