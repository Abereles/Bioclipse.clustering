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

public interface IClusteringAlgorithm {
/*
 * This class that implements is concerned with embodying a clustering algorithm,
 * one example of which would be "single linkage". 
 */
 public abstract ClustIndexPair doPass(ClusterList clusterList, IDistanceAlgorithm distanceAlgorithm) throws BioclipseException;
 
}
