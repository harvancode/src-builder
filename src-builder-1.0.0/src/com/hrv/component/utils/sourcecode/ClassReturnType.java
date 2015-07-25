/*
 * @(#)ClassReturnType.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class ClassReturnType implements ReturnType {
	private String className;

	public String getReturnType() {
		return className + " ";
	}

	public ClassReturnType(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return getReturnType();
	}
}