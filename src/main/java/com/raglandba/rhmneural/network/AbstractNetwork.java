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
	
	protected int rows;
	protected int columns;
	
	protected List<List<AbstractNeuron>> network;

	public AbstractNetwork(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		network = new ArrayList<>(rows);
	}
	
	
}
