/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.network;

import com.raglandba.rhmneural.neuron.AbstractNeuron;
import java.util.List;

/**
 *
 * @author Brandon Alexander Ragland
 */
public class BasicNetwork extends AbstractNetwork{
	
	public BasicNetwork(int rows, int columns) throws RHMNeuralNetworkException{
		super(rows, columns);
	}

	@Override
	public List<AbstractNeuron> getNeuronsToSynapseOn(AbstractNeuron firingNeuron){
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
