/*******************************************************************************
 * Copyright (c) 2010  Jeff Miller <jrmillerwork@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.chem.clustering.clusterhandling;

import net.bioclipse.core.business.BioclipseException;

/*
 * This interface is for classes that can be used to get distance
 * between elements (as opposed to clusters of elements)
 * Default classes that implement this interface are located in this package.
 */
public interface IDistanceAlgorithm <T> {
	public double calculateElementDistance(T element1, T element2) throws BioclipseException;

}
