package br.rio.puc.piecewisebus.model;

import java.util.ArrayList;
import java.util.List;

public class PiecewiseElements {

	List<Double> timeday = new ArrayList<Double>();
	List<Double> totalTime = new ArrayList<Double>();
	double[][] coletas; 

	public List<Double> getTimeday() {
		return timeday;
	}

	public void setTimeday(double timeday) {
		this.timeday.add(timeday);
	}
	
	public List<Double> getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime.add(totalTime);
	}
	
	public void addMatrix(double timeday, double totalTime, int index) {
		System.out.println("Valor timeday: "+ timeday);
		System.out.println("Valor totaltime: "+ totalTime);
		System.out.println("Valor index: "+ index);
		
		coletas[index][1] = timeday;
		coletas[index][2] = totalTime;
	}
}
