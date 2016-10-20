package br.rio.puc.piecewisebus.model;

public class ElementsTotalTravelTime {

	private double duration;
	private double timestamp;
	
	public ElementsTotalTravelTime(double duration, double timestamp) {
        this.duration = duration;
        this.timestamp = timestamp;
    }
	
	public Double getDuration() {
	    return this.duration;
	  }
	public void setDuration(double duration) {
	    this.duration = duration;
	  }
	
	public Double getTimestamp() {
	    return this.timestamp;
	  }
	public void setTimestamp(double timestamp) {
	    this.timestamp = timestamp;
	  }
}
