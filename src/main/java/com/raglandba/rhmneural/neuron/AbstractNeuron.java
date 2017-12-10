/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.neuron;

import com.raglandba.rhmneural.network.AbstractNetwork;

/**
 *
 * @author Brandon Alexander Ragland
 */
public abstract class AbstractNeuron{
	
	protected boolean active = false;
	
	protected int row;
	protected int column;
	
	protected AbstractNetwork network;

	protected double excitationFactor = 0.0;
	protected double inhibitionFactor = 0.5;
	
	protected double excitationOut = 0.0;
	protected double inhibitionOut = 0.0;

	protected double seekingFactor = 0.25;
	protected double leakingFactor = 0.25;
	
	protected int decayMillis = 10;
	protected int neuronResponseMillis = 2;
	
	
	public void startNeuron(){
		active = true;
	}
	
	public void stopNeuron(){
		active = false;
	}
	
	protected abstract void synapseOut();
	
	public abstract void synapseIn(double excitation, double inhibition);

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

	public int getNeuronResponseMillis(){
		return neuronResponseMillis;
	}

	public void setNeuronResponseMillis(int neuronResponseMillis){
		this.neuronResponseMillis = neuronResponseMillis;
	}
}
