package br.rio.puc.piecewisebus.estimator;

public class TreeEstimator {
	private double middle_time;
	private double function;
	private double media;
	private double variancia;
	private double standard_deviation;
	private double end_time;
	private double start_time;

	
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
	
	public Double getMedia() {
	    return this.media;
	  }
	public void setMedia(double media) {
	    this.media = media;
	  }
	
	public Double getVariancia() {
	    return this.variancia;
	  }
	public void setVariancia(double variancia) {
	    this.variancia = variancia;
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
	
	public Double getStartTime() {
	    return this.start_time;
	  }
	public void setStartTime(double start_time) {
	    this.start_time = start_time;
	  }
}
