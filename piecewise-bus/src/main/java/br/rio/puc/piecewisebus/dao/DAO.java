package br.rio.puc.piecewisebus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.ElementsTimestamp;
import br.rio.puc.piecewisebus.model.ElementsTotalTravelTime;

public class DAO {
	
	private int FIELD_DATE_TIME = 1;
	private int FIELD_DURACAO   = 2;

	public List<Elements> getElements() throws ClassNotFoundException,
			SQLException, IOException {

		List<Elements> elements = new ArrayList<Elements>();
		Connection connetion = ConnectionJDBC.getConnection();
		ResultSet rs = null;

		try {

			PreparedStatement sql = connetion
					.prepareStatement("SELECT source, target, road_id FROM grafoteste");
			rs = sql.executeQuery();
			
			while (rs.next()) {
				Elements element = new Elements();

				element.setSource(rs.getString("source"));
				element.setTarget(rs.getString("target"));
				element.setEdge(rs.getString("road_id"));
				
				elements.add(element);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print(e.getMessage());
			System.err.print("[ERRO] UM ERRO OCORREU QUANDO OS ELEMENTOS ESTAVAM SENDO CAPTURADOS");
			System.err.print(e.getMessage());
		}

		connetion.close();
		return elements;
	}
	
   public double[] getTotalTravelTime(int edge_id) throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
	    
	   Connection connectionJDBC = null;
		try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		double[] arrayResult;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select gf.totaltime from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +";");
						
			resultSet.last();
			int size = resultSet.getRow();
			resultSet.beforeFirst();
			
			arrayResult = new double[size];
			
			int i = 0;
			while (resultSet.next()) {
				arrayResult[i] = resultSet.getDouble("totaltime");
				i++;
			}
				
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
		
		return arrayResult;
	}
   
   public double[] getTotalTravelTimeEsq(int edge_id, double middle_time) throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
	    
	   Connection connectionJDBC = null;
		try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		double[] arrayResult;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select gf.totaltime from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +" "
							+ "and gf.timeday <= " + middle_time +";");
						
			resultSet.last();
			int size = resultSet.getRow();
			resultSet.beforeFirst();
			
			arrayResult = new double[size];
			
			int i = 0;
			while (resultSet.next()) {
				arrayResult[i] = resultSet.getDouble("totaltime");
				i++;
			}
				
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
		
		return arrayResult;
	}
   
   public double[] getTotalTravelTimeDir(int edge_id, double middle_time) throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
	    
	   Connection connectionJDBC = null;
		try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		double[] arrayResult;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select gf.totaltime from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +" "
							+ "and gf.timeday > " + middle_time +";");
						
			resultSet.last();
			int size = resultSet.getRow();
			resultSet.beforeFirst();
			
			arrayResult = new double[size];
			
			int i = 0;
			while (resultSet.next()) {
				arrayResult[i] = resultSet.getDouble("totaltime");
				i++;
			}
				
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
		
		return arrayResult;
	}
   
   public ElementsTimestamp getMaxMinTimeValue(int edge_id) throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
	    
  	   ElementsTimestamp elementTS = new ElementsTimestamp();
	   Connection connectionJDBC = null;
		
	   try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select min(gf.timeday) as min_time, max(gf.timeday) as max_time "
					+ "from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +";");
						
			while (resultSet.next()) {
				elementTS.setStartTime(resultSet.getDouble("min_time"));
				elementTS.setEndTime(resultSet.getDouble("max_time"));
				elementTS.setMiddleTime(resultSet.getDouble("min_time") + ((resultSet.getDouble("max_time") - resultSet.getDouble("min_time")) / 2));
			}
				
	
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
	return elementTS;
	}
   
   public ElementsTimestamp getMaxMinTimeValue(int edge_id, double middle_time) throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
	    
  	   ElementsTimestamp elementTS = new ElementsTimestamp();
	   Connection connectionJDBC = null;
		
	   try {
			connectionJDBC = ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu quando estava sendo aberta a conexão com DB.");
		}
		
		ResultSet resultSet = null;
		try {
			Statement statement = connectionJDBC.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = statement.executeQuery("select min(gf.timeday) as min_time, max(gf.timeday) as max_time "
					+ "from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +" and gf.timeday ;");
						
			while (resultSet.next()) {
				elementTS.setStartTime(resultSet.getDouble("min_time"));
				elementTS.setEndTime(resultSet.getDouble("max_time"));
				elementTS.setMiddleTime(resultSet.getDouble("max_time") - resultSet.getDouble("min_time"));
			}
				
	
		} catch (SQLException e) {
			throw new PiecewiseException("[ERRO] Um erro ocorreu ao pegar as informações no DB.");
		}
		
	return elementTS;
	}
   
   public double[][] getData(int edge_id) throws PiecewiseException {
		
		List<Elements> elements = new ArrayList<Elements>();

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
			
			resultSet = statement.executeQuery("select gf.timeday, gf.totaltime from generatorfunction gf, grafoteste gt "
					+ "where gf.idedge = gt.road_id and gf.idedge = " + edge_id +";");
						
			resultSet.last();
			int size = resultSet.getRow();
			resultSet.beforeFirst();
			
			matrixResult = new double[size][2];
			
			int i = 0;
			while (resultSet.next()) {
			
				Double dateTime = resultSet.getDouble(FIELD_DATE_TIME);
				Double duracao = resultSet.getDouble(FIELD_DURACAO);
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
   
   public List<Elements> getDataTeste() throws PiecewiseException, ClassNotFoundException, SQLException, IOException {
		
	   List<Elements> elements = new ArrayList<Elements>();
		Connection connetion = ConnectionJDBC.getConnection();
		ResultSet rs = null;

		try {

			PreparedStatement sql = connetion
					.prepareStatement("SELECT source, target, cost FROM testdij");
			rs = sql.executeQuery();
			
			while (rs.next()) {
				Elements element = new Elements();

				element.setSource(rs.getString("source"));
				element.setTarget(rs.getString("target"));
				element.setDistance(rs.getDouble("cost"));
				
				elements.add(element);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print(e.getMessage());
			System.err.print("[ERRO] UM ERRO OCORREU QUANDO OS ELEMENTOS ESTAVAM SENDO CAPTURADOS");
			System.err.print(e.getMessage());
		}

		connetion.close();
		return elements;
	}
	
}
