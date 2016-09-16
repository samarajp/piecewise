package br.rio.puc.piecewisebus.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.function.ManipulatorR;
import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.function.UpdateStreamFunction;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.Grafo;
import br.rio.puc.piecewisebus.model.Vertice;
import br.rio.puc.piecewisebus.shortestpath.AStar;

/**
 * Hello world!
 *
 */
public class App {
	
	private static ManipulatorR datafunction;
	private static AStar astar;

	public static void main(String[] args) throws ClassNotFoundException,
		SQLException, IOException, PiecewiseException {

		long startTime = System.nanoTime();

		DAO dao = new DAO();
		List<Elements> elements = dao.getElements();
		
		double[][] elementsmatrix;
		double[][] updatefunctionelematrix;

		double cost;
		
		Grafo grafo = new Grafo();
		AStar astar = new AStar();

		
		for (Elements element : elements) {

			Vertice source = grafo.addVertice(element.getSource().trim());
			Vertice target = grafo.addVertice(element.getTarget().trim());
			
			elementsmatrix = dao.getData(Integer.parseInt(element.getEdge().trim()));
			
			datafunction = new ManipulatorR(elementsmatrix);
			//parametro passado: hora do dia
			datafunction.run(8);
			
			cost = datafunction.yFinal;
			
			grafo.addAresta(source, target, element.getEdge().trim(), cost);
			
			System.out.println(grafo);
			
			//astar.shortestpath(source, target);
			
		}
		
		long estimatedTime = System.nanoTime() - startTime;
		
		int road_id = 1;
		
		UpdateStreamFunction up = new UpdateStreamFunction();
		
		updatefunctionelematrix = up.coletaDados(estimatedTime, road_id);
		
		datafunction = new ManipulatorR(updatefunctionelematrix);
		
		datafunction.run(8);
		
		cost = datafunction.yFinal;
		
		System.out.println("Custo da edge que será atualizada: "+cost);
		
	}
}
