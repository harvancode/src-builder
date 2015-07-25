/*
 * @(#)ModifierBuilder.java	0.1 29/06/15
 *
 * Copyright 2015 Harvan Irsyadi, Inc. All rights reserved.
 *
 */
package com.hrv.component.utils.sourcecode.builder;

import java.util.HashMap;
import java.util.Map;

import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.wrapper.ModifierWrapper;

/**
 * 
 * @author Harvan Irsyadi
 * 
 */
public class ModifierBuilder implements ModifierWrapper {

	private Map<Modifier, Modifier> modifierMap = new HashMap<Modifier, Modifier>();

	protected Modifier getModifier(Modifier modifier) {
		return modifierMap.get(modifier);
	}

	public ModifierWrapper addModifier(Modifier modifier) {
		if (modifier != null) {
			if (modifier == Modifier.PUBLIC || modifier == Modifier.PROTECTED || modifier == Modifier.PRIVATE) {
				// generate new modifier
				modifierMap.remove(Modifier.PUBLIC);
				modifierMap.remove(Modifier.PROTECTED);
				modifierMap.remove(Modifier.PRIVATE);

				modifierMap.put(modifier, modifier);
			} else {
				Modifier mod = modifierMap.get(modifier);
				if (mod == null) {
					modifierMap.put(modifier, modifier);
				}
			}
		}
		return this;
	}

	public String getModifierString() {
		StringBuilder sb = new StringBuilder();

		if (modifierMap.get(Modifier.PUBLIC) != null) {
			sb.append(modifierMap.get(Modifier.PUBLIC));
		}
		if (modifierMap.get(Modifier.PROTECTED) != null) {
			sb.append(modifierMap.get(Modifier.PROTECTED));
		}
		if (modifierMap.get(Modifier.PRIVATE) != null) {
			sb.append(modifierMap.get(Modifier.PRIVATE));
		}
		if (modifierMap.get(Modifier.ABSTRACT) != null) {
			sb.append(modifierMap.get(Modifier.ABSTRACT));
		}
		if (modifierMap.get(Modifier.STATIC) != null) {
			sb.append(modifierMap.get(Modifier.STATIC));
		}
		if (modifierMap.get(Modifier.FINAL) != null) {
			sb.append(modifierMap.get(Modifier.FINAL));
		}
		if (modifierMap.get(Modifier.INTERFACE) != null) {
			sb.append(modifierMap.get(Modifier.INTERFACE));
		}

		return sb.toString();
	}
}