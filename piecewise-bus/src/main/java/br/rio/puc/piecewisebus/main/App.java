package br.rio.puc.piecewisebus.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.function.ManipulatorR;
import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.function.UpdateStreamFunction;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.Grafo;
import br.rio.puc.piecewisebus.model.Vertice;
import br.rio.puc.piecewisebus.shortestpath.*;

/**
 * Hello world!
 *
 */
public class App {
	
	private static ManipulatorR datafunction;

	public static void main(String[] args) throws ClassNotFoundException,
		SQLException, IOException, PiecewiseException {

		long startTime = System.nanoTime();

		DAO dao = new DAO();
		List<Elements> elements = dao.getElements();
//		List<Elements> elements = dao.getDataTeste();

		
		double[][] elementsmatrix;
		double[][] updatefunctionelematrix;
		double cost;
		
		Grafo grafo = new Grafo();
		
		for (Elements element : elements) {

			Vertice source = grafo.addVertice(element.getSource().trim());
			Vertice target = grafo.addVertice(element.getTarget().trim());
			
			elementsmatrix = dao.getData(Integer.parseInt(element.getEdge().trim()));
			
			datafunction = new ManipulatorR(elementsmatrix);
			//parametro passado: hora do dia
			datafunction.run(8);
			
			cost = datafunction.yFinal;

			grafo.addAresta(source, target, element.getEdge().trim(), cost);
//			grafo.addAresta(source, target, "1", element.getDistance());

			System.out.println(grafo);
		}
		
		/*
		 * Comentada essa parte do código porque corresponde a atualização da função via Stream
		 */
//		long estimatedTime = System.nanoTime() - startTime;
//		
//		int road_id = 1;
//		
//		UpdateStreamFunction up = new UpdateStreamFunction();
//		
//		updatefunctionelematrix = up.coletaDados(estimatedTime, road_id);
//		
//		datafunction = new ManipulatorR(updatefunctionelematrix);
//		
//		datafunction.run(8);
//		
//		cost = datafunction.yFinal;
//		
//		System.out.println("Custo da edge que será atualizada: "+cost);

		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(grafo);
	    dijkstra.execute(grafo.getVertice("s"));
	    LinkedList<Vertice> path = dijkstra.getPath(grafo.getVertice("w"));
	    
	    System.out.println("Menor caminho de S a W:");
	    
	    for (Vertice vertex : path) {
	      System.out.println(vertex);
	    }
		
	}
}
