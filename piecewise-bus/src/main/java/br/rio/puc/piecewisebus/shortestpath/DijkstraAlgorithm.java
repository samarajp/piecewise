package br.rio.puc.piecewisebus.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import br.rio.puc.piecewisebus.model.Aresta;
import br.rio.puc.piecewisebus.model.Grafo;
import br.rio.puc.piecewisebus.model.Vertice;

public class DijkstraAlgorithm {

	  private final ArrayList<Vertice> nodes;
	  private final ArrayList<Aresta> edges;
	  private Set<Vertice> settledNodes;
	  private Set<Vertice> unSettledNodes;
	  private Map<Vertice, Vertice> predecessors;
	  private Map<Vertice, Double> distance;
	  
	  public DijkstraAlgorithm(Grafo graph) {
	    // create a copy of the array so that we can operate on this array
	    this.nodes = new ArrayList<Vertice>(graph.getVertexes());
	    this.edges = new ArrayList<Aresta>(graph.getEdges());
	  }

	  public void execute(Vertice source) {
		  
		source.setDistance(0);
  
	    settledNodes = new HashSet<Vertice>();
	    unSettledNodes = new HashSet<Vertice>();
	    distance = new HashMap<Vertice, Double>();
	    predecessors = new HashMap<Vertice, Vertice>();
	    
	    distance.put(source, source.getDistance());
	    unSettledNodes.add(source);
	    
		while (unSettledNodes.size() > 0) {
	      Vertice node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      findMinimalDistances(node);
	    }
	  }
	  
	  private void findMinimalDistances(Vertice node) {
		  
		for (Aresta target : node.getAdjacents()) {	
			if (node.getCostToNode(target.getToNode().getNome()) <= (node.getDistance() + node.getCostToNode(target.getToNode().getNome()))) {
					target.getToNode().setDistance(node.getDistance() + node.getCostToNode(target.getToNode().getNome()));
					distance.put(target.getToNode(), (node.getDistance() + node.getCostToNode(target.getToNode().getNome())));
			        predecessors.put(target.getToNode(), node);
			        unSettledNodes.add(target.getToNode());
			      }
		}
	  }

	  private Vertice getMinimum(Set<Vertice> vertexes) {
		Vertice minimum = null;
		for (Vertice vertex : vertexes) {
	      if (minimum == null) {
	        minimum = vertex;
	      } else {
	        if (vertex.getDistance() <= minimum.getDistance()) {
	          minimum = vertex;
	        }
	      }
	    }
	    return minimum;
	  }

	  /*
	   * This method returns the path from the source to the selected target and
	   * NULL if no path exists
	   */
	  public LinkedList<Vertice> getPath(Vertice target) {
	    LinkedList<Vertice> path = new LinkedList<Vertice>();
	    Vertice step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    
	    path.add(step);
	    
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    
	    // Put it into the correct order
	    Collections.reverse(path);
	    
	    return path;
	  }

	}  