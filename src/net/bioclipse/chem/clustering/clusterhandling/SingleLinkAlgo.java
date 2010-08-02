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

public class SingleLinkAlgo implements IClusteringAlgorithm {
	static final double LARGE_DISTANCE = 1000000.0;
	public ClustIndexPair doPass(ClusterList cL, IDistanceAlgorithm dA )throws BioclipseException 
	{
		/*
		 * SingleLinkAlgo uses closest two elements in clusters.
		 * One pass consists of finding two closest clusters
		 */
		
		Combo2 priorCombo = new Combo2(0,1);
		int length = cL.getSize();
		double curMin = LARGE_DISTANCE;
		double curDist = 0;
		Combo2 curCombo = null;
		Combo2 minCombo = new Combo2(0,0);
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
	private Combo2 nChoose2(int length, Combo2 priorCombo)
	{
		/*
		 * This function is able to find the 2-combo given the prior
		 * combo
		 */
		if (priorCombo.index1 < length-2){
			
		if (priorCombo.index2 == length-1){
			return new Combo2(priorCombo.index1+1,priorCombo.index1+2);
		}
		else return new Combo2(priorCombo.index1, priorCombo.index2+1);
		}
		else return (Combo2) null;
			
		
			
			
	}
	private class Combo2
	{
		int index1;
		int index2;
		Combo2(int index1, int index2)
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
