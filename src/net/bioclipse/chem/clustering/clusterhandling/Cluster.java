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

import java.util.LinkedList;
import java.util.List;
public class Cluster {
	
	public List<Object> elementList;
	public Cluster()
	{
		elementList = new LinkedList<Object>();
	}
	public void addElement(Object o)
	{
		elementList.add(o);
	}

	protected void merge(Cluster otherClust)
	{
		/*
		 * Merge this Cluster with another
		 */
		this.elementList.addAll(otherClust.elementList);

	}
    public Object get(int index)
    {
    	return elementList.get(index);
    }
    public int getSize()
    {
    	return elementList.size();
    }
}
