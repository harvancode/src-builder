/*
 * @(#)ModifierWrapper.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.wrapper;

import com.hrv.component.utils.sourcecode.Modifier;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public interface ModifierWrapper {

	public ModifierWrapper addModifier(Modifier modifierMap);

	public String getModifierString();
}