package br.rio.puc.piecewisebus.model;

public class ElementsTimestamp {

	private double start_time;
	private double middle_time;
	private double end_time;
	
	public Double getStartTime() {
	    return this.start_time;
	  }
	public void setStartTime(double start_time) {
	    this.start_time = start_time;
	  }
	
	public Double getMiddleTime() {
	    return this.middle_time;
	  }
	
	public void setMiddleTime(double middle_time) {
	    this.middle_time = middle_time;
	  }
	
	public Double getEndTime() {
	    return this.end_time;
	  }
	public void setEndTime(double end_time) {
	    this.end_time = end_time;
	  }
}
