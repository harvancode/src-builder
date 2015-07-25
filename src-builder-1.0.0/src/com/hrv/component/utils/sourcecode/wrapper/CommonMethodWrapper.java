/*
 * @(#)CommonMethodWrapper.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.wrapper;

import com.hrv.component.utils.sourcecode.ReturnType;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public interface CommonMethodWrapper extends SourceWrapper, ModifierWrapper, CommonWrapper, MethodWrapper {
	public void setReturnType(ReturnType returnType);

	public ReturnType getReturnType();

	public void addParameter(String className, String objVar);

	public String getParameterString();
}
