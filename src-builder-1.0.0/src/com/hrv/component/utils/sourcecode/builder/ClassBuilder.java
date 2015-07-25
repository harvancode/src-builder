/*
 * @(#)ClassBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import java.util.ArrayList;
import java.util.List;

import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.wrapper.ClassWrapper;
import com.hrv.component.utils.sourcecode.wrapper.MethodWrapper;
import com.hrv.component.utils.sourcecode.wrapper.ModifierWrapper;
import com.hrv.component.utils.sourcecode.wrapper.VariableWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class ClassBuilder extends ModifierBuilder implements ClassWrapper {
	private String pckg = "";
	private List<String> importList = new ArrayList<String>();
	private String name;
	private String extend;
	private MethodWrapper classInitMethod;
	private List<String> interfaceList = new ArrayList<String>();
	private List<VariableWrapper> variableList = new ArrayList<VariableWrapper>();
	private List<MethodWrapper> methodList = new ArrayList<MethodWrapper>();
	private List<ClassWrapper> classList = new ArrayList<ClassWrapper>();
	private Integer depth = 0;
	private StringBuilder sbDepth = new StringBuilder();

	public void setPackage(String pckg) {
		this.pckg = pckg;
	}

	public String getPackage() {
		return pckg;
	}

	public void addImport(String imprt) {
		importList.add("import " + imprt + ";");
	}

	public String getImportString() {
		StringBuilder sb = new StringBuilder();

		for (String str : importList) {
			sb.append(str).append("\n");
		}
		if (!importList.isEmpty()) {
			sb.append("\n");
		}

		return sb.toString();
	}

	@Override
	public ModifierWrapper addModifier(Modifier modifier) {
		if (modifier != null) {
			if (modifier == Modifier.STATIC) {
				if ((getModifier(Modifier.PUBLIC) == null || getModifier(Modifier.PRIVATE) != null)) {
					return super.addModifier(modifier);
				} else {
					return this;
				}
			} else {
				return super.addModifier(modifier);
			}
		} else {
			return this;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getExtend() {
		return extend;
	}

	public void addInterface(String impl) {
		interfaceList.add(impl);
	}

	public List<String> getInterfaceList() {
		return interfaceList;
	}

	public String getInterfaceString() {
		StringBuilder sb = new StringBuilder();

		String str;
		for (int i = 0; i < interfaceList.size(); i++) {
			str = interfaceList.get(i);
			sb.append(str);
			if (i + 1 < interfaceList.size()) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	public void addVariable(VariableWrapper var) {
		variableList.add(var);
	}

	public String getVariableString() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (VariableWrapper str : variableList) {
			i++;
			sb.append("\t").append(str).append(";");
			if (i <= variableList.size()) {
				sb.append("\n");
			}
		}
		if (!variableList.isEmpty()) {
			sb.append("\n");
		}

		return sb.toString();
	}

	public void setClassInitMethod(MethodWrapper classInitMethod) {
		classInitMethod.addCurrentDepth(getCurrentDepth());
		this.classInitMethod = classInitMethod;
	}

	public String getClassInitMethodString() {
		return classInitMethod != null ? classInitMethod.toString() : "";
	}

	public void addMethod(MethodWrapper mb) {
		mb.addCurrentDepth(getCurrentDepth());
		methodList.add(mb);
	}

	public String getMethodString() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (MethodWrapper obj : methodList) {
			i++;
			sb.append(obj);
			if (i < methodList.size()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public void addInnerClass(ClassWrapper cw) {
		cw.addCurrentDepth(this.getCurrentDepth());
		classList.add(cw);
	}

	public String getClassString() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (ClassWrapper str : classList) {
			i++;
			sb.append(str);
			if (i < classList.size()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public void addCurrentDepth(int depth) {
		this.depth = this.depth + depth + 1;

		for (int i = 0; i < getCurrentDepth(); i++) {
			sbDepth.append("\t");
		}
	}

	public int getCurrentDepth() {
		return depth;
	}

	public String getClassName() {
		return getPackage() + "." + getName();
	}

	private StringBuilder getDepthAppender() {
		return sbDepth;
	}

	public String create() throws Exception {
		if (getName() == null || getName().trim().equals("")) {
			throw new Exception("Class name cannot be empty.");
		}

		StringBuilder sbDepth = getDepthAppender();

		StringBuilder sb = new StringBuilder();

		if (getPackage().trim() != "") {
			sb.append("package ").append(getPackage()).append(";\n\n");
		}
		if (!importList.isEmpty()) {
			sb.append(getImportString());
		}

		sb.append(getCurrentDepth() > 0 ? "\n" : "");
		sb.append(getModifierString());
		sb.append(sbDepth).append("class ");
		sb.append(getName()).append(" ");

		if (getExtend() != null && !getExtend().trim().equals("")) {
			sb.append("extends ").append(getExtend()).append(" ");
		}

		if (!getInterfaceList().isEmpty()) {
			sb.append("implements ").append(getInterfaceString()).append(" ");
		}

		sb.append("{\n");
		sb.append(getVariableString());
		sb.append(getMethodString());
		sb.append(getClassString());
		sb.append(methodList.isEmpty() ? "" : "\n").append(getClassInitMethodString());
		sb.append(sbDepth).append("}");
		sb.append(getCurrentDepth() > 0 ? "\n" : "");

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