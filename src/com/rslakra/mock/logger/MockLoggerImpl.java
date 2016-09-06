/***************************************************************************
 * Copyright (C) RSLakra 2009
 **************************************************************************/
package com.rslakra.mock.logger;

import org.apache.log4j.Category;

import com.rslakra.logger.LoggerImpl;

/**
 *
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 *
 */
public class MockLoggerImpl extends LoggerImpl {
	
	public MockLoggerImpl(Category logger) {
		super(logger);
	}
}