package br.rio.puc.piecewisebus.model;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private String nome;
	private int no;

	private List<Aresta> adj;
    private double distance = Double.MAX_VALUE;

	public Vertice(String nome) {
		this.nome = nome;
		this.adj = new ArrayList<Aresta>();
	}
	
	public Vertice(int no) {
		this.no = no;
		this.adj = new ArrayList<Aresta>();
	}

	void addAdj(Aresta e) {
		adj.add(e);
	}

	public String getNome() {
		return nome;
	}

	public List<Aresta> getAdjacents() {
		return adj;
	}

	public Double getCostToNode(String nome) {
		for (Aresta e : adj) {
			if (nome.equals(e.getToNode().getNome()))
				return e.getCost();
		}
		return null;
	}
	
	 @Override
	 public boolean  equals (Object object) {
	     boolean result = false;
	     if (object == null || object.getClass() != getClass()) {
	         result = false;
	     } else {
	    	 Vertice vertice = (Vertice) object;
	         if (this.nome.equals(vertice.getNome())) {
	             result = true;
	         }
	     }
	     return result;
	 }


	@Override
	public int hashCode() {
		return getNome().length() * 8;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
	public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
