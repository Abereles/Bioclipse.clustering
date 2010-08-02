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
package net.bioclipse.chem.clustering.business;

import net.bioclipse.chem.clustering.clusterhandling.ClusterList;
import net.bioclipse.chem.clustering.clusterhandling.IClusteringAlgorithm;
import net.bioclipse.chem.clustering.clusterhandling.IDistanceAlgorithm;
import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="Provides methods for clustering Molecules"
)
public interface IClusteringManager extends IBioclipseManager {
	@PublishedMethod(
	        params = "ClusterList cL, int numberClusters",
	        methodSummary = "Using default algorithms, produces" +
	                        "NumberClusters clusters from a " +
	                        "list of Molecules"
	    )
	public ClusterList doClustering(ClusterList cL, int numberClusts);
	@PublishedMethod(
	        params = "ClusterList cL, int numberClusters, IDistanceAlgorithm dA, IClusteringAlgorithm cA",
	        methodSummary = "Using specified algorithms, produces" +
	                        "NumberClusters clusters from a " +
	                        "list of Molecules"
	    )
	public ClusterList doClustering(ClusterList cL, int numberClusts, IDistanceAlgorithm dA, IClusteringAlgorithm cA);

}
