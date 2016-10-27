package br.rio.puc.piecewisebus.model;

import br.rio.puc.piecewisebus.estimator.TreeEstimator;
import br.rio.puc.piecewisebus.variance.TreeVariance;

public class No {
	TreeEstimator busdata;

    public No(TreeEstimator busdata) {
        this.busdata = busdata;
    }

    public TreeEstimator getElements() {
        return busdata;
    }

    public void setElements(TreeEstimator busdata) {
        this.busdata = busdata;
    }
}