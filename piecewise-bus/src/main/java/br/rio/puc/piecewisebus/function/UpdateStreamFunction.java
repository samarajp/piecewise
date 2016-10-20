package br.rio.puc.piecewisebus.function;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.rio.puc.piecewisebus.dao.ConnectionJDBC;

/**
 * Método que atualizará a função Piecewise dada uma TW
 * 
 * Passos para atualizar a Função:
 * 1. Verificar o intervalo de TS que contém o novo conjunto de Stream (SQL);
 * 2. Identificar nos dados históricos, quais objetos estão contidos nesse invervalo (SQL);
 * 3. Gerar matriz a partir dessa informação;
 * 4. Gerar uma função Piecewise a partir desse conjunto.
 * 5. A Piecewise corresponderá a apenas parte do conjunto de informações.
 * 
 * @author Samara
 *
 */

public class UpdateStreamFunction {

	//obter dados para gerar função dado o tempo de execução do algoritmo
	public double[][] coletaDados(long algorithmtime, int edge_id) throws PiecewiseException {
		
		Connection connectionJDBC = null;
		try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		double[][] matrixResult;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select distinct gf.timeday, gf.totaltime from generatorfunction gf, grafoteste gt "
					+ "where timeday between (select min(gf.timeday) - "+ algorithmtime + " as mintime from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +") and (select max(gf.timeday) +"+ algorithmtime + " as maxtime "
					+ "from generatorfunction gf, grafoteste gt where gf.idedge = gt.road_id and gf.idedge = " + edge_id +");");
			
			resultSet.last();
			int size = resultSet.getRow();
			resultSet.beforeFirst();
			
			matrixResult = new double[size][2];
			
			int i = 0;
			while (resultSet.next()) {
			
				Double dateTime = resultSet.getDouble("timeday");
				Double duracao = resultSet.getDouble("totaltime");
				double[] result = new double[2];
				result[0] = duracao;
				result[1] = dateTime;
				
				matrixResult[i] = result;
				i++;
			}
					
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
		return matrixResult;
	}
}
