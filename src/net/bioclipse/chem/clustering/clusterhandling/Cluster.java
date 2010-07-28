package net.bioclipse.chem.clustering.clusterhandling;

import java.util.LinkedList;
import java.util.List;
public class Cluster {
	
	public List<Object> elementList;
	public Cluster()
	{
		elementList = new LinkedList();
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
    public Object getIndex(int index)
    {
    	return elementList.get(index);
    }
    public int getSize()
    {
    	return elementList.size();
    }
}
