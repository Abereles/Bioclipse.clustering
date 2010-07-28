package net.bioclipse.chem.clustering.clusterhandling;

import net.bioclipse.core.business.BioclipseException;

public class SingleLinkAlgo implements IClusteringAlgorithm {
	static final double LARGE_DISTANCE = 1000000.0;
	public ClustIndexPair doPass(ClusterList cL, IDistanceAlgorithm dA )throws BioclipseException 
	{
		/*
		 * SingleLinkAlgo uses closest two elements in clusters.
		 * One pass consists of finding two closest clusters
		 */
		
		combo2 priorCombo = new combo2(0,1);
		int length = cL.getSize();
		double curMin = LARGE_DISTANCE;
		double curDist = 0;
		combo2 curCombo = null;
		combo2 minCombo = new combo2(0,0);
		while(((curCombo = nChoose2(length, priorCombo))!= null)) {
			priorCombo = curCombo;
			curDist = calcClusterDistance(cL.getClust(curCombo.index1),
					                      cL.getClust(curCombo.index2),
					                      dA);
			if (curDist < curMin) {
				curMin = curDist;
				minCombo = curCombo;
			}
		}
		return new ClustIndexPair(minCombo.index1,minCombo.index2);
	}
	private combo2 nChoose2(int length, combo2 priorCombo)
	{
		/*
		 * This function is able to find the 2-combo given the prior
		 * combo
		 */
		if (priorCombo.index1 < length-2){
			
		if (priorCombo.index2 == length-1){
			return new combo2(priorCombo.index1+1,priorCombo.index1+2);
		}
		else return new combo2(priorCombo.index1, priorCombo.index2+1);
		}
		else return (combo2) null;
			
		
			
			
	}
	private class combo2
	{
		int index1;
		int index2;
		combo2(int index1, int index2)
		{
			this.index1 = index1;
			this.index2 = index2;
		}
	}
	private static double calcClusterDistance(Cluster c1, Cluster c2,
			                                  IDistanceAlgorithm dA)throws BioclipseException
	{
		/*
		 * In the case of this algorithm, we want the distance twixt 2 closest
		 * elements in each cluster.
		 */
		double curMin = LARGE_DISTANCE;
		double curDist = 0;
		for (Object o1 : c1.elementList)
			for (Object o2: c2.elementList) {
				if(curMin > (curDist = dA.calculateElementDistance(o1,o2))){
					curMin = curDist;
				}
			
			}
		return curMin;
	}
}
