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
public class ClustIndexPair {
	/*
	 * Holds pair of indexes into ClusterList to allow
	 * cluster consolidation after a pass of the clustering
	 * algorithm
	 */
	public int index1, index2;
	ClustIndexPair(int index1, int index2)
	{
		this.index1 = index1;
		this.index2 = index2;
	}
}
