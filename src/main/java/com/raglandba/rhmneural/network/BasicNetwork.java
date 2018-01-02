/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raglandba.rhmneural.network;

import com.raglandba.rhmneural.neuron.AbstractNeuron;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Brandon Alexander Ragland
 */
public class BasicNetwork extends AbstractNetwork{

	private Timer logTimer = null;
	private Workbook logBook = null;
	private Sheet logSheet = null;

	private Map<AbstractNeuron, Row> excitationLogRows = new HashMap<>();
	private Map<AbstractNeuron, Row> inhibitionLogRows = new HashMap<>();

	public BasicNetwork(int rows, int columns) throws RHMNeuralNetworkException{
		super(rows, columns);
	}

	@Override
	public void sendData(double[] values){
		for(int i = 0; i < values.length && i < dataInputNeurons.size(); i++){

			AbstractNeuron neuron = dataInputNeurons.get(i);

			if(neuron == null){
				continue;
			}

			neuron.synapseIn(values[i], 0.0);
		}
	}

	@Override
	public List<AbstractNeuron> getNeuronsToSynapseOn(AbstractNeuron firingNeuron){
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void printNetworkState(){
		System.out.println("==========Excitation States==========");
		for(List<AbstractNeuron> row : network){
			for(AbstractNeuron neuron : row){
				System.out.print(decimalFormat.format(neuron.getExcitationFactor()) + " ");
			}
			System.out.println();
		}

		System.out.println("==========Inhibition States==========");
		for(List<AbstractNeuron> row : network){
			for(AbstractNeuron neuron : row){
				System.out.print(decimalFormat.format(neuron.getInhibitionFactor()) + " ");
			}
			System.out.println();
		}
	}

	public void startLog(int freq){
		logTimer = new Timer();
		logBook = new XSSFWorkbook();
		logSheet = logBook.createSheet("Raw Data");

		for(List<AbstractNeuron> row : network){
			for(AbstractNeuron neuron : row){
				//grab the rows
				Row e = logSheet.createRow(logSheet.getLastRowNum() + 1);
				Row i = logSheet.createRow(logSheet.getLastRowNum() + 1);
				//add a space between neurons
				logSheet.createRow(logSheet.getLastRowNum() + 1);
				//add them to the maps
				excitationLogRows.put(neuron, e);
				inhibitionLogRows.put(neuron, i);
			}
		}

		logTimer.schedule(new LogTask(), 0, freq);
	}

	public void stopLog(OutputStream out) throws IOException{
		if(logTimer != null){
			logTimer.cancel();
			logTimer.purge();
			logTimer = null;
		}

		if(excitationLogRows != null){
			excitationLogRows.clear();
		}

		if(inhibitionLogRows != null){
			inhibitionLogRows.clear();
		}

		logBook.write(out);
		logBook.close();
		out.close();

		logBook = null;
		logSheet = null;
	}

	protected class LogTask extends TimerTask{

		@Override
		public void run(){
			for(List<AbstractNeuron> row : network){
				for(AbstractNeuron neuron : row){
					Row e = excitationLogRows.get(neuron);
					Row i = inhibitionLogRows.get(neuron);
					Cell eLog = e.createCell(e.getLastCellNum() == -1 ? 0 : e.getLastCellNum());
					Cell iLog = i.createCell(i.getLastCellNum() == -1 ? 0 : i.getLastCellNum());
					eLog.setCellValue(neuron.getExcitationFactor());
					iLog.setCellValue(neuron.getInhibitionFactor());
				}
			}
		}
	}
}
