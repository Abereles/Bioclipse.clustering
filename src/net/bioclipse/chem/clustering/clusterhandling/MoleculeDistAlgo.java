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
/*
 * This implementation uses fingerprint and Tanimoto, maybe better name
 * can be used.
 */
import java.util.BitSet;

import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule.Property;
import net.bioclipse.cdk.business.CDKManager;
import net.bioclipse.cdk.domain.CDKMolecule;

public class MoleculeDistAlgo implements IDistanceAlgorithm <CDKMolecule>{

	@Override
	public double calculateElementDistance(CDKMolecule cdkm1, CDKMolecule cdkm2) throws BioclipseException {
		Property urgency = Property.USE_CACHED;
		
		CDKManager cdk = new CDKManager();
		BitSet bs1 = cdkm1.getFingerprint(urgency);
		BitSet bs2 = cdkm2.getFingerprint(urgency);
		return cdk.calculateTanimoto(bs1, bs2);
	}

}
