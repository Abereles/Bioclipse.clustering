package net.bioclipse.chem.clustering.clusterhandling;

import net.bioclipse.core.business.BioclipseException;

public interface IClusteringAlgorithm {
/*
 * This class that implements is concerned with embodying a clustering algorithm,
 * one example of which would be "single linkage". 
 */
 public abstract ClustIndexPair doPass(ClusterList clusterList, IDistanceAlgorithm distanceAlgorithm) throws BioclipseException;
 
}
