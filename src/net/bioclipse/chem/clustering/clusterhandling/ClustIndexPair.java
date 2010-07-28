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
