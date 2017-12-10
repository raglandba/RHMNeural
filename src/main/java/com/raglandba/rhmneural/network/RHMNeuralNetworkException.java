/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.network;

/**
 *
 * @author Brandon Alexander Ragland
 */
public class RHMNeuralNetworkException extends Exception{

	/**
	 * Creates a new instance of <code>RHMNeuralNetworkException</code> without detail message.
	 */
	public RHMNeuralNetworkException(){
	}

	/**
	 * Constructs an instance of <code>RHMNeuralNetworkException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public RHMNeuralNetworkException(String msg){
		super(msg);
	}
}
