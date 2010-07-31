package net.bioclipse.chem.clustering;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

//import javax.swing.tree.TreePath;

import net.bioclipse.cdk.business.CDKManager;
import net.bioclipse.chem.clustering.business.ClusteringManager;
import net.bioclipse.chem.clustering.business.IClusteringManager;
import net.bioclipse.chem.clustering.clusterhandling.Cluster;
import net.bioclipse.chem.clustering.clusterhandling.ClusterList;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ViewClustering extends ViewPart implements ISelectionListener{
	private Text text;
	protected static ClusteringManager clustering;
	public ViewClustering() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		text = new Text(parent, SWT.BORDER);
		getSite().getPage().addSelectionListener((ISelectionListener) this);
		

	}
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		/*
		 * We should be able to get filename here, open it and set text to show
		 * clustered molecules. Path&Filename is within selection although
		 * sometimes no file is present and we should ignore such cases
		 * or somehow avoid being called at all
		 */
		org.eclipse.core.internal.resources.File file;
		final int NUMCLUSTS =2;		
		if ((file = getFileFromSelection(selection)) != null) {
		/*
		 * Get all the molecules out of the file and use them to initialize
		 * a clusterlist
		 */
			try{
				ClusterList cl = new ClusterList();
				List l = getMolecules(file);
				for (Object o : l) {
					Cluster clust = new Cluster();
					clust.addElement(o);
					cl.add(clust);
				}
				text.setText("right before display clusterlist");
				clustering = new ClusteringManager();
				ClusterList results = clustering.doClustering(cl, NUMCLUSTS);
				displayClusterList(results);
			} catch(BioclipseException e){text.setText(e.getMessage());}
		}
	}
	private void displayClusterList(ClusterList cl)
	{
		/*
		 * This just displays a ClusterList textually -- probably
		 * want to be able to offer different ways, maybe with passing
		 * in of display object.
		 * 
		 * But for now, we just list the elements in each cluster
		 */
		StringBuffer sb = new StringBuffer();
		for (Cluster c: cl.clusterList){
			sb.append("[");
			for (Object o : c.elementList ) {
				IMolecule m = (IMolecule) o;
				sb.append(m.toString()+" ");
			}
			sb.append("]");
		}
		text.setText(sb.toString());
	}
	private static List <IMolecule> getMolecules(org.eclipse.core.internal.resources.File file)
	throws BioclipseException
	{
		//Okay, we can actually open the selected file
		//with the below commented-out code but issue
		//of what file format (text is wrong anyway and
		//causes problems just by being selected) so
		//probably do CML file and use existing tools
		//but for now, stub with SMILEs code below
		/*try {
			InputStream i = file.getContents();
			int data;
			while((data = i.read()) !=-1) {
				System.out.println((char)data);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		CDKManager cdk = new CDKManager();
		List <IMolecule> mList = new LinkedList();
		mList.add(cdk.fromSMILES("CCC"));
		mList.add(cdk.fromSMILES("OCO"));
		mList.add(cdk.fromSMILES("CCC"));
		mList.add(cdk.fromSMILES("O"));
		mList.add(cdk.fromSMILES("CCC"));
		return mList;
	}
	private static org.eclipse.core.internal.resources.File
	getFileFromSelection(ISelection selection)
	{
		/*
		 * Idea currently is that various events will cause us to
		 * be unwantedly within the code that ideally only should
		 * respond to file selections.
		 * 
		 * For now, let us try determining if the selection is of
		 * a file and if not, return null.
		 */
		TreeSelection t = (TreeSelection) selection;
		TreePath[] paths = t.getPaths();
		if (paths.length > 0) {
		   Object otemp = paths[0].getLastSegment();
		   String tstring = otemp.getClass().getName();
		   if (tstring.equals("org.eclipse.core.internal.resources.File"))
		   {
			   return((org.eclipse.core.internal.resources.File)paths[0].getLastSegment());
		   }
		}
		return(null);
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		System.out.println("Hi from ViewClustering.setfocus");

	}

}
