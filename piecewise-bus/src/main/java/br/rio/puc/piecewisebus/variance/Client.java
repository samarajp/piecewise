package br.rio.puc.piecewisebus.variance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.model.ArvoreBinaria;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.ElementsTimestamp;

public class Client {
	
	     public static void main(String[] args) throws NumberFormatException, PiecewiseException, ClassNotFoundException, SQLException, IOException {

	    	 DAO dao = new DAO();
	 	 
	    	 double array[] = dao.getTotalTravelTime(Integer.parseInt("1"));

	    	 Estatistica e = new Estatistica();

             e.setArray(array);
          
             ElementsTimestamp time = dao.getMaxMinTimeValue(Integer.parseInt("1"));
             
	    	 TreeVariance tree = new TreeVariance();
	    	 tree.setMiddleTime(time.getMiddleTime());
	    	 tree.setFunction(e.getVariancia());
	    	 tree.setVariance(e.getVariancia());
	    	 tree.setStandardDeviation(e.getDesvioPadrao());
	    	 tree.setEndTime(time.getEndTime());
	    	 
	    	 
	    	 ArvoreBinaria arvore = new ArvoreBinaria();
	    	 arvore.insereBusData(tree);
	    	 
	    	 arvore.percorrerInOrder();
	    	 arvore.percorrerPostOrder();
	    	 arvore.percorrerPreOrder();


	    	 /**
	    	  * Recebendo um novo DS, dada uma janela de tempo
	    	  * Expandindo a Árvore
	    	  */
	    	 
	    	 double new_timewindow = time.getStartTime(); //Valor pode mudar
	    	 
	    	 if (new_timewindow < time.getMiddleTime()){
	    		 
	    		 double arrayesq[] = dao.getTotalTravelTimeEsq(Integer.parseInt("1"), time.getMiddleTime());

		    	 e.setArray(arrayesq);
	          
	             double middle_time = time.getMiddleTime() - time.getStartTime();
	             
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariance(e.getVariancia());
		    	 tree.setStandardDeviation(e.getDesvioPadrao());
		    	 tree.setEndTime(time.getMiddleTime());
		    	 
		    	 arvore.insereBusData(tree);

	    	 }
	    	 else{
	    		 
	    		 double arrayesq[] = dao.getTotalTravelTimeDir(Integer.parseInt("1"), time.getMiddleTime());

		    	 e.setArray(arrayesq);
	          
	             double middle_time = time.getMiddleTime() - time.getStartTime();
	             
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariance(e.getVariancia());
		    	 tree.setStandardDeviation(e.getDesvioPadrao());
		    	 tree.setEndTime(time.getMiddleTime());
		    	 
		    	 arvore.insereBusData(tree);
	    	 }
	     }

    }