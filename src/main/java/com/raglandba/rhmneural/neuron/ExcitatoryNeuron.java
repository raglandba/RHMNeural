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
public class ExcitatoryNeuron extends AbstractNeuron{

	public ExcitatoryNeuron(){
		excitationOut = 0.6;
		inhibitionOut = 0.0;
	}

	public ExcitatoryNeuron(double strength) throws RHMNeuralNeuronException{
		inhibitionOut = 0.0;

		if(strength < 0.3){
			throw new RHMNeuralNeuronException("Strength cannot be less than 0.3 for Excitatory Neurons");
		}else if(strength > 2.0){
			throw new RHMNeuralNeuronException("Strength cannot be more than 2.0 for Excitatory Neurons");
		}else{
			excitationOut = strength;
		}
	}

	@Override
	public synchronized void synapseIn(Double excitation, Double inhibition){
		if(excitation != null){
			excitationFactor = (excitationFactor + excitation) / 2;
		}
		if(inhibition != null){
			inhibitionFactor = (inhibitionFactor + inhibition) / 2;
		}
	}

	@Override
	protected void synapseOut(){
		if(neuronsToSynapseOn != null && neuronsToSynapseOn.size() > 0){
			for(AbstractNeuron neuron : neuronsToSynapseOn){
				neuron.synapseIn(excitationFactor, null);
			}
		}else{
			for(AbstractNeuron neuron : network.getNeuronsToSynapseOn(this)){
				neuron.synapseIn(excitationFactor, null);
			}
		}
	}

}
