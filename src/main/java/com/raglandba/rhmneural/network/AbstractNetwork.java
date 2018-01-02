/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.network;

import com.raglandba.rhmneural.neuron.AbstractNeuron;
import java.text.DecimalFormat;
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
	protected int computeMillis = 2;

	protected int dataInputMillis = 10;

	protected static DecimalFormat decimalFormat = new DecimalFormat("0.0");

	public AbstractNetwork(int rows, int columns) throws RHMNeuralNetworkException{
		if(rows > MAX_ROWS){
			throw new RHMNeuralNetworkException("Row cannot be larger than " + MAX_ROWS);
		}

		if(columns >= MAX_COLUMNS){
			throw new RHMNeuralNetworkException("Column cannot be larger than " + MAX_COLUMNS);
		}

		this.rows = rows;
		this.columns = columns;
		network = new ArrayList<>(rows);

		setupNetworkContainer();
	}

	public abstract void sendData(double[] values);

	public abstract List<AbstractNeuron> getNeuronsToSynapseOn(AbstractNeuron firingNeuron);

	public void startNetwork(){
		for(List<AbstractNeuron> row : network){
			for(AbstractNeuron neuron : row){
				neuron.startNeuron();
			}
		}
	}

	public void stopNetwork(){
		for(List<AbstractNeuron> row : network){
			for(AbstractNeuron neuron : row){
				neuron.stopNeuron();
			}
		}
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

		//make sure we aren't larger than the max columns as well
		if(network.get(row).size() >= MAX_COLUMNS){
			throw new RHMNeuralNetworkException("Column cannot be larger than " + MAX_COLUMNS);
		}

		//set the basic network-wide stats for this neuron before adding to the network
		neuron.setNetwork(this);
		neuron.setDecayMillis(decayMillis);
		neuron.setComputeMillis(computeMillis);
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

	public int getComputeMillis(){
		return computeMillis;
	}

	public void setComputeMillis(int computeMillis){
		this.computeMillis = computeMillis;
	}
}
