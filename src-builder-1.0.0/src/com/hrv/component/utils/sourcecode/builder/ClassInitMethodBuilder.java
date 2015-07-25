/*
 * @(#)ClassInitMethodBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.wrapper.ClassWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class ClassInitMethodBuilder extends AbstractMethodBuilder {

	private ClassInitMethodBuilder() {
	}

	public ClassInitMethodBuilder(ClassWrapper cw) {
		cw.setClassInitMethod(this);
	}

	public String create() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("\t").append(Modifier.STATIC);
		sb.append("{\n");
		sb.append(getContent());

		sb.append("\t").append("}\n");
		return sb.toString();
	}
}