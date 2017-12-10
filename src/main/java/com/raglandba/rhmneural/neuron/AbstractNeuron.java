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
public abstract class AbstractNeuron{

	protected double excitationFactor = 0.5;
	protected double inhibitionFactor = 0.5;

	protected double seekingFactor = 0.25;
	protected double leakingFactor = 0.25;
	
	

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
}
