package br.rio.puc.piecewisebus.model;

import br.rio.puc.piecewisebus.variance.TreeVariance;

public class No {
    TreeVariance busdata;

    public No(TreeVariance busdata) {
        this.busdata = busdata;
    }

    public TreeVariance getElements() {
        return busdata;
    }

    public void setElements(TreeVariance busdata) {
        this.busdata = busdata;
    }
}