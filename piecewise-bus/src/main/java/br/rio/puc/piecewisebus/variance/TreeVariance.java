package br.rio.puc.piecewisebus.variance;

public class TreeVariance {
	private double middle_time;
	private double function;
	private double variance;
	private double standard_deviation;
	private double end_time;

	
	public Double getMiddleTime() {
	    return this.middle_time;
	  }
	public void setMiddleTime(double middle_time) {
	    this.middle_time = middle_time;
	  }
	
	public Double getFunction() {
	    return this.function;
	  }
	public void setFunction(double function) {
	    this.function = function;
	  }
	
	public Double getVariance() {
	    return this.variance;
	  }
	public void setVariance(double variance) {
	    this.variance = variance;
	  }
	
	public Double getStandardDeviation() {
	    return this.standard_deviation;
	  }
	public void setStandardDeviation(double standard_deviation) {
	    this.standard_deviation = standard_deviation;
	  }
	
	public Double getEndTime() {
	    return this.end_time;
	  }
	public void setEndTime(double end_time) {
	    this.end_time = end_time;
	  }
}
