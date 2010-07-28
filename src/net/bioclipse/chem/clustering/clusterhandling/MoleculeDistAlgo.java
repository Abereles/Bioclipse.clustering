package net.bioclipse.chem.clustering.clusterhandling;
/*
 * This implementation uses fingerprint and Tanimoto, maybe better name
 * can be used.
 */
import java.util.BitSet;

import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule.Property;
import net.bioclipse.cdk.business.CDKManager;
import net.bioclipse.cdk.domain.CDKMolecule;

public class MoleculeDistAlgo implements IDistanceAlgorithm {

	@Override
	public double calculateElementDistance(Object obj1, Object obj2) throws BioclipseException {
		Property urgency = Property.USE_CACHED;
		if ((obj1.getClass() != CDKMolecule.class)&& (obj2.getClass() != CDKMolecule.class))
		{
			BioclipseException e = new BioclipseException("Requires CDKMolecules as arguments");
			throw e;
		}
		CDKManager cdk = new CDKManager();
		CDKMolecule cdkm1 = (CDKMolecule) obj1;
		CDKMolecule cdkm2 = (CDKMolecule) obj2;
		
		BitSet bs1 = cdkm1.getFingerprint(urgency);
		BitSet bs2 = cdkm2.getFingerprint(urgency);
		return cdk.calculateTanimoto(bs1, bs2);
	}

}
