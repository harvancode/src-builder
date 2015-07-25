/*
 * @(#)VariableBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import com.hrv.component.utils.sourcecode.wrapper.VariableWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class VariableBuilder extends ModifierBuilder implements VariableWrapper {
	private String className;
	private String name;
	private String defaultValue;

	public VariableBuilder(String className, String name, String defaultValue) {
		this.className = className;
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String create() throws Exception {
		if (getName() == null || getName().trim().equals("")) {
			throw new Exception("Variable name cannot be empty.");
		}

		StringBuilder sb = new StringBuilder();

		sb.append(getModifierString());
		sb.append(getClassName()).append(" ").append(getName());

		if (getDefaultValue() != null) {
			sb.append(" = ").append(getDefaultValue());
		}

		return sb.toString();
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