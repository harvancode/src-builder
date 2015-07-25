/*
 * @(#)ClassWrapper.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.wrapper;

import java.util.List;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public interface ClassWrapper extends SourceWrapper, ModifierWrapper, CommonWrapper, InnerWrapper {
	public void setPackage(String pckg);

	public String getPackage();

	public void addImport(String imprt);

	public String getImportString();

	public void setExtend(String extend);

	public String getExtend();

	public void addInterface(String impl);

	public List<String> getInterfaceList();

	public String getInterfaceString();

	public void addVariable(VariableWrapper var);

	public String getVariableString();

	public void setClassInitMethod(MethodWrapper mb);

	public String getClassInitMethodString();

	public void addMethod(MethodWrapper mb);

	public String getMethodString();

	public String getClassName();

	public void addInnerClass(ClassWrapper cw);

	public String getClassString();
}