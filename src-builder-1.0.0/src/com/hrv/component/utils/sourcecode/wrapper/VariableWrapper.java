/*
 * @(#)VariableWrapper.java	0.1 29/06/15
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
public interface VariableWrapper extends SourceWrapper, ModifierWrapper, CommonWrapper {
	public void setClassName(String className);

	public String getClassName();

	public String getDefaultValue();
}