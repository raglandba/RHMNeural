/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.neuron;

/**
 *
 * @author Brandon Alexander Ragland
 */
public class InhibitoryNeuron extends AbstractNeuron{

	public InhibitoryNeuron(){
		excitationOut = 0.0;
		inhibitionOut = 0.3;
	}
	
	public InhibitoryNeuron(double strength) throws RHMNeuralNeuronException{
		excitationOut = 0.0;
		
		if(strength < 0.3){
			throw new RHMNeuralNeuronException("Strength cannot be less than 0.3 for Inhibitory Neurons");
		}else if(strength > 2.0){
			throw new RHMNeuralNeuronException("Strength cannot be more than 2.0 for Inhibitory Neurons");
		}else{
			inhibitionOut = strength;
		}
	}

	@Override
	public synchronized void synapseIn(double excitation, double inhibition){
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	protected void synapseOut(){
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
