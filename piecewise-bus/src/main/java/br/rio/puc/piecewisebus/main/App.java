package br.rio.puc.piecewisebus.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.function.ManipulatorR;
import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.Grafo;
import br.rio.puc.piecewisebus.model.Vertice;

/**
 * Hello world!
 *
 */
public class App {
	
	private static ManipulatorR datafunction;

	public static void main(String[] args) throws ClassNotFoundException,
		SQLException, IOException, PiecewiseException {
		DAO dao = new DAO();
		
		List<Elements> elements = dao.getElements();
		double[][] elementsmatrix = dao.getData(1);
		
		Grafo grafo = new Grafo();

		for (Elements element : elements) {

			Vertice source = grafo.addVertice(element.getSource().trim());
			Vertice target = grafo.addVertice(element.getTarget().trim());
			
			datafunction = new ManipulatorR(elementsmatrix);
			
			datafunction.run(8);
			
			grafo.addAresta(source, target, element.getEdge().trim(), element.getDistance());
			
			System.out.println(grafo);
		}
	}
}
