package net.bioclipse.chem.clustering.clusterhandling;
import java.util.LinkedList;
import java.util.List;

import net.bioclipse.core.business.BioclipseException;
/*
 * This class represents the list of clusters. It will
 * be initialized with each element in its own cluster
 * and then using the algorithm passed in, as well as a separate
 * distance algorithm(?) we begin clustering and stop when N clusters
 * are reached. Note that each operation, the number of clusters diminishes by one
 * so if N < total elements, it will be hit.
 */
public class ClusterList {
	public List<Cluster> clusterList;
	private IClusteringAlgorithm cA;
	
	public void setcA(IClusteringAlgorithm cA) {
		this.cA = cA;
	}
	private IDistanceAlgorithm dA;
	
	public ClusterList(List<Object> elementList, IClusteringAlgorithm cA){
		this.cA = cA;
		/*
		 * Constructor will take lists of Elements and create
		 * a Cluster for each Element
		 */
		while (elementList.iterator().hasNext())
		{
			clusterList.add((Cluster)(elementList.iterator().next()));
		}
	}
	public ClusterList()
	{
		clusterList = new LinkedList<Cluster>();
	}
	public void setDa(IDistanceAlgorithm dA)
	{
		this.dA = dA;
	}
	public void add(Cluster cluster)
	{
		//adds a cluster to cluster list
		clusterList.add(cluster);
	}
	public int getSize()
	{
		return clusterList.size();
	}
	public Cluster getClust(int index)
	{
		return clusterList.get(index);
	}
	public int doClustering(int numberOfClusters) throws BioclipseException
	{
		/*
		 * Uses ClusteringAlgorithm to get indexes into
		 * itself for consolidation
		 */
		while((clusterList.size()> numberOfClusters))
		{
			ClustIndexPair cip = cA.doPass(this, dA);
			consolidate(cip);
		}
		return clusterList.size();
		
	}
	private void consolidate(ClustIndexPair cip)
	{
		/*
		 * Merges and deletes
		 */
		clusterList.get(cip.index1).merge(clusterList.get(cip.index2));
		clusterList.remove(clusterList.get(cip.index2));
	}
}
