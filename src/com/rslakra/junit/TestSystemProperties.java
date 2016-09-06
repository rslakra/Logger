/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.junit;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 */
public class TestSystemProperties {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dumpSystemProperties();
	}
	
	public static void dumpSystemProperties() {
		Properties props = System.getProperties();
		for(Iterator iter = props.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
		}
	}
}
