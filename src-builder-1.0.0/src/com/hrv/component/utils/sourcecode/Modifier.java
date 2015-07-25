/*
 * @(#)Modifier.java	0.1 29/06/15
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
public enum Modifier {
	PUBLIC("public "), PROTECTED("protected "), PRIVATE("private "), ABSTRACT("abstract "), STATIC("static "), FINAL("final "), INTERFACE("interface ");

	private String value;

	private Modifier(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}