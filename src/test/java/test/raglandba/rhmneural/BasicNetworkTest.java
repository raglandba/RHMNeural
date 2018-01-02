/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.raglandba.rhmneural;

import com.raglandba.rhmneural.network.BasicNetwork;
import com.raglandba.rhmneural.network.RHMNeuralNetworkException;
import com.raglandba.rhmneural.neuron.ExcitatoryNeuron;
import com.raglandba.rhmneural.neuron.InhibitoryNeuron;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Brandon Alexander Ragland
 */
public class BasicNetworkTest{

	private BasicNetwork network;

	public BasicNetworkTest(){
	}

	@BeforeClass
	public static void setUpClass(){
	}

	@AfterClass
	public static void tearDownClass(){
	}

	@Before
	public void setUp() throws RHMNeuralNetworkException{
		//create the network
		network = new BasicNetwork(4, 3);
		network.setComputeMillis(10);
		network.setDecayMillis(20);

		//create the neurons, named by location in the grid
		//create the data receiving neurons
		ExcitatoryNeuron d11 = new ExcitatoryNeuron();
		ExcitatoryNeuron d12 = new ExcitatoryNeuron();
		ExcitatoryNeuron d13 = new ExcitatoryNeuron();

		//create the regular neurons
		ExcitatoryNeuron e21 = new ExcitatoryNeuron();
		ExcitatoryNeuron e22 = new ExcitatoryNeuron();
		ExcitatoryNeuron e23 = new ExcitatoryNeuron();

		InhibitoryNeuron i31 = new InhibitoryNeuron();
		InhibitoryNeuron i32 = new InhibitoryNeuron();
		InhibitoryNeuron i33 = new InhibitoryNeuron();

		ExcitatoryNeuron e41 = new ExcitatoryNeuron();
		ExcitatoryNeuron e42 = new ExcitatoryNeuron();
		ExcitatoryNeuron e43 = new ExcitatoryNeuron();

		//create the first connection from the data receiving neurons to the processing ones
		d11.getNeuronsToSynapseOn().add(e21);
		d12.getNeuronsToSynapseOn().add(e22);
		d13.getNeuronsToSynapseOn().add(e23);

		//create the feedback loop connections
		//column 1
		e21.getNeuronsToSynapseOn().add(i31);
		e21.getNeuronsToSynapseOn().add(e41);
		i31.getNeuronsToSynapseOn().add(e41);
		e41.getNeuronsToSynapseOn().add(d11);
		//column 2
		e22.getNeuronsToSynapseOn().add(i32);
		e22.getNeuronsToSynapseOn().add(e42);
		i32.getNeuronsToSynapseOn().add(e42);
		e42.getNeuronsToSynapseOn().add(d12);
		//column 3
		e23.getNeuronsToSynapseOn().add(i33);
		e23.getNeuronsToSynapseOn().add(e43);
		i33.getNeuronsToSynapseOn().add(e43);
		e43.getNeuronsToSynapseOn().add(d13);

		//add the neurons to the network
		network.addNeuron(d11, 0, true);
		network.addNeuron(d12, 0, true);
		network.addNeuron(d13, 0, true);

		network.addNeuron(e21, 1, false);
		network.addNeuron(e22, 1, false);
		network.addNeuron(e23, 1, false);

		network.addNeuron(i31, 2, false);
		network.addNeuron(i32, 2, false);
		network.addNeuron(i33, 2, false);

		network.addNeuron(e41, 3, false);
		network.addNeuron(e42, 3, false);
		network.addNeuron(e43, 3, false);

		//start the network
		network.startNetwork();
		network.startLog(5);

		System.out.println("Setup of the BasicNetwork Test Complete");
	}

	@After
	public void tearDown() throws FileNotFoundException, IOException{
		if(network != null){
			network.stopNetwork();
			network.stopLog(new FileOutputStream("/home/bragland/Documents/rhm/log.xlsx"));
			network = null;
		}
		System.out.println("Teardown of the BasicNetwork Test Complete");
	}

	@Test
	public void test() throws InterruptedException{
		double[] values = {0.8, 1.0, 1.4};
		System.out.println("Starting Test...");

		network.printNetworkState();

		network.sendData(values);

		Thread.sleep(2000);
	}
}
