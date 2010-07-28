package net.bioclipse.chem.clustering.clusterhandling;

import net.bioclipse.core.business.BioclipseException;

/*
 * This interface is for classes that can be used to get distance
 * between elements (as opposed to clusters of elements)
 * Classes that implement this interface are located in this package,
 * which might have to be reconsidered design-wise
 */
public interface IDistanceAlgorithm {
	public double calculateElementDistance(Object obj1, Object obj2) throws BioclipseException;

}
