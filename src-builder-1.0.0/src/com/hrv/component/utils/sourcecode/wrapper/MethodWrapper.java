/*
 * @(#)MethodWrapper.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.wrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public interface MethodWrapper extends SourceWrapper {
	public StringBuilder getContent();

	public void addLineCode(String sb) throws Exception;

	public void addCurrentDepth(int depth);

	public int getCurrentDepth();
}