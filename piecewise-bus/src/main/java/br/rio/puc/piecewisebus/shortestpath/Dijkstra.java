package br.rio.puc.piecewisebus.shortestpath;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import br.rio.puc.piecewisebus.model.Aresta;
import br.rio.puc.piecewisebus.model.Grafo;
import br.rio.puc.piecewisebus.model.Vertice;

public class Dijkstra {

    private final Grafo graph;

    public Dijkstra(Grafo graph) {
        if (graph == null) {
            throw new NullPointerException("The input graph cannot be null.");
        }
        this.graph = graph;
    }
    
    public class NodeCompator implements Comparator<Vertice>  {
        @Override
        public int compare(Vertice n1, Vertice n2) {
            if (n1.getDistance() > n2.getDistance()) {
                return 1;
            } else {
                return -1;
            }
        }
    };
   
    
    public Set<Vertice> findShortest(String source) {

        
        final Queue<Vertice> queue = new PriorityQueue<Vertice>(10, new NodeCompator());

        System.out.println("entou no dijkstra: "+source);

        Vertice start = new Vertice(source);
        if (!(graph.getVertice(start.getNome()).equals(null))) {
            System.out.println("incremento de lista: "+start);
            start.setDistance(0);
            queue.offer(start);
        }
        
       final Set<Vertice> doneSet = new HashSet<Vertice>();

        while (!queue.isEmpty()) {
           System.out.println("lista não é vazia");

           Vertice src = queue.poll();
           
           System.out.println("vertice recebido da lista: "+src);

           if (!doneSet.contains(src)){
        	   doneSet.add(src);
               System.out.println("Adicionou: "+src);

               for (Aresta edge : graph.getAdj(src)) {
            	
            	   System.out.println("pega vertices adjacentes ao: "+src);
            	   System.out.println("pega vertices adjacentes ao: "+edge.getToNode());

            	   Vertice currentNode = edge.getToNode();
            	   System.out.println("current node: "+currentNode);

            	   if (!doneSet.contains(currentNode)) {
            		   System.out.println("doneSet não tem: "+currentNode);
            		   System.out.println("CUSTO: "+edge.getCost());

            		   double newDistance = (double) src.getDistance() + edge.getCost();
                  
            		   System.out.println("CUSTO newDistance: "+newDistance);

            		   if (newDistance < currentNode.getDistance()) {
            			   currentNode.setDistance(newDistance);
            			   queue.add(currentNode);
            		   } 
            	   }
               }
           }
        }
      
        return doneSet;
    }

}