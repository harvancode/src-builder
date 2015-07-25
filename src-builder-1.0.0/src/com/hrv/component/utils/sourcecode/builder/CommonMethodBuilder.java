/*
 * @(#)CommonMethodBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import java.util.ArrayList;
import java.util.List;

import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.ReturnType;
import com.hrv.component.utils.sourcecode.wrapper.ClassWrapper;
import com.hrv.component.utils.sourcecode.wrapper.CommonMethodWrapper;
import com.hrv.component.utils.sourcecode.wrapper.ModifierWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class CommonMethodBuilder extends AbstractMethodBuilder implements CommonMethodWrapper {
	private List<String> listParameter = new ArrayList<String>();
	private ReturnType returnType;
	private String name;
	private ModifierWrapper mod = new ModifierBuilder();

	private CommonMethodBuilder() {
	}

	public CommonMethodBuilder(ClassWrapper cw) {
		cw.addMethod(this);
	}

	public ModifierWrapper addModifier(Modifier modifier) {
		if (modifier != null) {
			mod.addModifier(modifier);
		}
		return this;
	}

	public String getModifierString() {
		StringBuilder sb = new StringBuilder();

		sb.append(mod.getModifierString());

		return sb.toString();
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void addParameter(String className, String objVar) {
		listParameter.add(className + " " + objVar);
	}

	public String getParameterString() {
		StringBuilder sb = new StringBuilder();

		String str;
		for (int i = 0; i < listParameter.size(); i++) {
			str = listParameter.get(i);
			sb.append(str);
			if (i + 1 < listParameter.size()) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String create() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(getPaddingLeft()).append(getModifierString());
		sb.append(getReturnType());

		if (getName() == null || getName().trim().equals("")) {
			throw new Exception("Method name cannot be empty.");
		}
		sb.append(getName());

		if (getParameterString() == null) {
			sb.append("() ");
		} else {
			sb.append("(").append(getParameterString()).append(") ");
		}

		sb.append("{\n");
		sb.append(getContent());
		sb.append(getPaddingLeft()).append("}\n");

		return sb.toString();
	}
}