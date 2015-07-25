/*
 * @(#)AbstractMethodBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import com.hrv.component.utils.sourcecode.wrapper.MethodWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public abstract class AbstractMethodBuilder implements MethodWrapper {
	private StringBuilder content = new StringBuilder();
	private Integer depth = null;
	private StringBuilder sbPaddingLeft = new StringBuilder();

	public void setContent(StringBuilder content) {
		this.content = content;
	}

	public StringBuilder getContent() {
		return content;
	}

	public void addLineCode(String sb) throws Exception {
		if (depth == null) {
			throw new Exception("Add Method to class first.");
		}
		getContent().append(getPaddingLeft()).append("\t").append(sb).append(";\n");
	}

	public void addCurrentDepth(int depth) {
		this.depth = depth + 1;

		generatePaddingLeft();
	}

	private void generatePaddingLeft() {
		for (int i = 0; i < getCurrentDepth(); i++) {
			sbPaddingLeft.append("\t");
		}
	}

	public int getCurrentDepth() {
		return depth;
	}

	protected StringBuilder getPaddingLeft() {
		return sbPaddingLeft;
	}

	@Override
	public String toString() {
		try {
			return create();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}