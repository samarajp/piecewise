package br.rio.puc.piecewisebus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {

	private Set<Vertice> vertices;
	private Set<Aresta> arestas;
    private final Map<Vertice, ArrayList<Aresta>> map;

	public Grafo() {
		vertices = new HashSet<Vertice>();
		arestas = new HashSet<Aresta>();
        this.map = new HashMap<Vertice, ArrayList<Aresta>>();
	}

	public Vertice addVertice(String nome) {

		Vertice vertice = new Vertice(nome);

		if(!vertices.contains(vertice)) {
			vertices.add(vertice);
		}
		
		return this.getVertice(nome);
	}
	
	public Vertice getVertice(String nome) {
		
		for (Vertice vertice : vertices) {
			if (vertice.getNome().equals(nome)) {
				return vertice;
			}
		}
		return null;
	}

	public Aresta addAresta(Vertice origem, Vertice destino, String edge,
			double distancia) {

		Aresta aresta = new Aresta(origem, destino, edge, distancia);
		origem.addAdj(aresta);
		arestas.add(aresta);
		
		addToMap(origem, aresta);
		
		return aresta;
	}
	
	private void addToMap (Vertice node, Aresta edge) {
        if (map.containsKey(node)) {
            List<Aresta> l = map.get(node);
            l.add(edge);
        } else  {
            List<Aresta> l = new ArrayList<Aresta>();
            l.add(edge);
            map.put(node, (ArrayList<Aresta>) l);
        }  
    }
	
	public List<Aresta> getAdj(Vertice node) {
        return map.get(node);
    }

    public Map<Vertice, ArrayList<Aresta>> getGraph() {
        return map;
    }
    
    public Set<Vertice> getVertexes() {
	    return vertices;
	  }

	  public Set<Aresta> getEdges() {
	    return arestas;
	  }
	
	public String toString() {

		String r = "";

		for (Vertice u : vertices) {
			r += u.getNome() + " -> ";
			for (Aresta e : u.getAdjacents()) {
				Vertice v = e.getToNode();
				r += v.getNome() + "(" + e.getCost() + ")" + ", ";
			}
			r += "\n";
		}
		return r;

	}

}