/*
 * @(#)VoidReturnType.java	0.1 29/06/15
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
public class VoidReturnType implements ReturnType {
	@Override
	public String toString() {
		return getReturnType();
	}

	public String getReturnType() {
		return "void ";
	}
}