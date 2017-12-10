/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.network;

import com.raglandba.rhmneural.neuron.AbstractNeuron;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brandon Alexander Ragland
 */
public abstract class AbstractNetwork{
	
	private static final int MAX_ROWS = 10;
	private static final int MAX_COLUMNS = 10;
	
	protected int rows;
	protected int columns;
	
	protected List<List<AbstractNeuron>> network;
	
	protected List<AbstractNeuron> dataInputNeurons = new ArrayList<>();
	
	protected int decayMillis = 10;
	protected int neuronResponseMillis = 2;
	
	protected int dataInputMillis = 10;

	public AbstractNetwork(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		network = new ArrayList<>(rows);
		setupNetworkContainer();
	}
	
	public void startNetwork(){
		
	}
	
	public void stopNetwork(){
		
	}
	
	public void addNeuron(AbstractNeuron neuron, int row, boolean receivesDataInput) throws RHMNeuralNetworkException{
		if(row > MAX_ROWS){
			throw new RHMNeuralNetworkException("Row cannot be larger than " + MAX_ROWS);
		}
		
		if(row < 0){
			throw new RHMNeuralNetworkException("Row cannot be less than 0");
		}
		
		//make sure we have added enough rows to add to this row if it doesn't exist yet
		if(network.size() < row + 1){
			int rowsToAdd = (row + 1) - network.size();
			
			for(int i = 0; i < rowsToAdd; i++){
				network.add(new ArrayList<>());
			}
		}
		
		//set the basic network-wide stats for this neuron before adding to the network
		neuron.setNetwork(this);
		neuron.setDecayMillis(decayMillis);
		neuron.setNeuronResponseMillis(neuronResponseMillis);
		neuron.setRow(row);
		neuron.setColumn(network.get(row).size());
		
		//add to this row
		network.get(row).add(neuron);
		
		if(receivesDataInput){
			dataInputNeurons.add(neuron);
		}
	}
	
	private void setupNetworkContainer(){
		for(int i = 0; i < rows; i++){
			network.add(new ArrayList<>());
		}
	}

	//++++++++++++++++++
	// Getters & Setters
	//++++++++++++++++++
	public int getDecayMillis(){
		return decayMillis;
	}

	public void setDecayMillis(int decayMillis){
		this.decayMillis = decayMillis;
	}

	public int getDataInputMillis(){
		return dataInputMillis;
	}

	public void setDataInputMillis(int dataInputMillis){
		this.dataInputMillis = dataInputMillis;
	}

	public int getNeuronResponseMillis(){
		return neuronResponseMillis;
	}

	public void setNeuronResponseMillis(int neuronResponseMillis){
		this.neuronResponseMillis = neuronResponseMillis;
	}
}
