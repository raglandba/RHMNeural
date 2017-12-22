/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.neuron;

import com.raglandba.rhmneural.network.AbstractNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Brandon Alexander Ragland
 */
public abstract class AbstractNeuron{

	//is this neuron active?
	protected boolean active = false;

	//reference to the location within the network this neuron is at
	protected int row;
	protected int column;

	//reference to the network this neuron belongs to
	protected AbstractNetwork network;
	
	//list of specific neurons this neuron should synapseOut to
	protected List<AbstractNeuron> neuronsToSynapseOn = new ArrayList<>();

	//resting potentials, what the neuron would be at when no inputs are being received
	protected double restingExcitationFactor = 0.0;
	protected double restingInhibitionFactor = 0.5;

	//the currect potentials or factors, used to calculate when this neuron should synapseOut
	protected double excitationFactor = 0.0;
	protected double inhibitionFactor = 0.5;

	//the potentials this neuron will synapseOut to other neurons
	protected double excitationOut = 0.0;
	protected double inhibitionOut = 0.0;

	//seekingFactor controls how many nearby non-target neurons we leak into, and the leakingFactor is how much of the full synapse we leak
	protected double seekingFactor = 0.25;
	protected double leakingFactor = 0.25;

	//how fast the neuron decays back to its resting exciting and inhibition factors
	protected int decayMillis = 10;

	//how fast this neuron responds to changes in its excitationFactor or inhibitionFactor. A clock-cycle basically. 
	protected int computeMillis = 2;

	//Timer for scheduling our DecayTask
	protected Timer decayTimer = null;
	
	//Timer for scheduling our ComputeTask
	protected Timer computeTimer = null;

	public void startNeuron(){
		active = true;

		decayTimer = new Timer();
		computeTimer = new Timer();
		
		decayTimer.schedule(new DecayTask(), 1, 1);
		computeTimer.schedule(new ComputeTask(), computeMillis, computeMillis);
	}

	public void stopNeuron(){
		active = false;

		//cancel, purge, and null the decayTimer if it isn't already null
		if(decayTimer != null){
			decayTimer.cancel();
			decayTimer.purge();
			decayTimer = null;
		}
		
		//cancel, purge, and null the computeTimer if it isn't already null
		if(computeTimer != null){
			computeTimer.cancel();
			computeTimer.purge();
			computeTimer = null;
		}
	}

	protected abstract void synapseOut();

	public abstract void synapseIn(double excitation, double inhibition);
	
	//++++++++++++++++++
	// Compute TimerTask
	//++++++++++++++++++
	protected class ComputeTask extends TimerTask{

		@Override
		public void run(){
			if(active == false){
				return;
			}
			
			if(excitationFactor > inhibitionFactor){
				synapseOut();
			}
		}
		
	}

	//++++++++++++++++++
	// Decay TimerTask
	//++++++++++++++++++
	protected class DecayTask extends TimerTask{

		//0 based where 0.0 = no decay.       v----> this is 1 because the decay decayTimer runs every 1 millisecond
		protected double decayAmountFactor = (1 / decayMillis);

		@Override
		public void run(){
			if(active == false){
				return;
			}
			
			//decay in different directions based on which polarization we are at
			if(excitationFactor > restingExcitationFactor){
				excitationFactor = excitationFactor * (1 - decayAmountFactor);
			}else if(excitationFactor < restingExcitationFactor){
				excitationFactor = excitationFactor * (1 + decayAmountFactor);
			}

			//decay in different directions based on which polarization we are at
			if(inhibitionFactor > restingInhibitionFactor){
				inhibitionFactor = inhibitionFactor * (1 - decayAmountFactor);
			}else if(inhibitionFactor < restingInhibitionFactor){
				inhibitionFactor = inhibitionFactor * (1 + decayAmountFactor);
			}
		}

	}

	//++++++++++++++++++
	// Getters & Setters
	//++++++++++++++++++
	public double getExcitationFactor(){
		return excitationFactor;
	}

	public void setExcitationFactor(double excitationFactor){
		this.excitationFactor = excitationFactor;
	}

	public double getInhibitionFactor(){
		return inhibitionFactor;
	}

	public void setInhibitionFactor(double inhibitionFactor){
		this.inhibitionFactor = inhibitionFactor;
	}

	public double getSeekingFactor(){
		return seekingFactor;
	}

	public void setSeekingFactor(double seekingFactor){
		this.seekingFactor = seekingFactor;
	}

	public double getLeakingFactor(){
		return leakingFactor;
	}

	public void setLeakingFactor(double leakingFactor){
		this.leakingFactor = leakingFactor;
	}

	public int getDecayMillis(){
		return decayMillis;
	}

	public void setDecayMillis(int decayMillis){
		this.decayMillis = decayMillis;
	}

	public AbstractNetwork getNetwork(){
		return network;
	}

	public void setNetwork(AbstractNetwork network){
		this.network = network;
	}

	public boolean isActive(){
		return active;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public int getRow(){
		return row;
	}

	public void setRow(int row){
		this.row = row;
	}

	public int getColumn(){
		return column;
	}

	public void setColumn(int column){
		this.column = column;
	}

	public int getComputeMillis(){
		return computeMillis;
	}

	public void setComputeMillis(int computeMillis){
		this.computeMillis = computeMillis;
	}

	public List<AbstractNeuron> getNeuronsToSynapseOn(){
		return neuronsToSynapseOn;
	}
}
