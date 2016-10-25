package br.rio.puc.piecewisebus.variance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.function.PiecewiseException;
import br.rio.puc.piecewisebus.function.UpdateStreamFunction;
import br.rio.puc.piecewisebus.model.ArvoreBinaria;
import br.rio.puc.piecewisebus.model.Elements;
import br.rio.puc.piecewisebus.model.ElementsTimestamp;

public class Client {
	
	     public static void main(String[] args) throws NumberFormatException, PiecewiseException, ClassNotFoundException, SQLException, IOException {

	    	 DAO dao = new DAO();
	    	 Estatistica e = new Estatistica();
	    	 ArvoreBinaria arvore = new ArvoreBinaria();

	    	 //Parametro de entrada: Segmento de Rua (idEdge)
	    	 double array[] = dao.getTotalTravelTime(Integer.parseInt("1"));

             e.setArray(array);
          
	    	 //Parametro de entrada: Segmento de Rua (idEdge)
             ElementsTimestamp time = dao.getMaxMinTimeValue(Integer.parseInt("1"));
             
             if (arvore.getRaiz() == null){
	             //Construção do nó raiz
		    	 TreeVariance tree = new TreeVariance();
		    	 tree.setMiddleTime(time.getMiddleTime());
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariance(e.getVariancia());
		    	 tree.setStandardDeviation(e.getDesvioPadrao());
		    	 tree.setEndTime(time.getEndTime());
		    	 
		    	 arvore.insereBusData(tree);
	    	 
             }
	    	
	    	 /**
	    	  * Recebendo um novo DS, dada uma janela de tempo
	    	  * Expandindo a Árvore
	    	  */
	    	 double new_timewindow = time.getStartTime(); //Valor pode mudar
	    	 double antigavariancia = e.getVariancia();
	    	 double antigoDP = e.getDesvioPadrao();
	    	 double tolerance = (e.getVariancia() - antigavariancia) / antigoDP;

	    	 if (new_timewindow < time.getMiddleTime()){
	    		 arvore.insereBusData(updateEsq(time.getStartTime(), time.getMiddleTime(), tolerance, antigavariancia, antigoDP));
	    	 }
	    	 else{
	    		 arvore.insereBusData(updateDir(time.getMiddleTime(), time.getEndTime(), tolerance, antigavariancia, antigoDP));
	    	 }
	    	 
	    	 arvore.percorrerInOrder();
	    	

	     }
	     
	     public static TreeVariance updateEsq(double startTime, double middleTime, double tolerance,
	    		 double antigavariancia, double antigoDP) throws NumberFormatException, ClassNotFoundException, PiecewiseException, SQLException, IOException{
	    	 DAO dao = new DAO();
	    	 Estatistica e = new Estatistica();
	    	 TreeVariance tree = new TreeVariance();
	    	 
	    	 double arrayesq[] = dao.getTotalTravelTimeEsq(Integer.parseInt("1"), middleTime);
 
	    	 e.setArray(arrayesq);
	      
	         double middle_time = startTime + ((middleTime - startTime) /2);
	         
	         if (tolerance < e.getDesvioPadrao()){
 		    	 //Valor da Variância precisa ser atualizado para verificar se criação de novo nó é necessária.
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariance(e.getNewVariancia(antigavariancia, e.getVariancia()));
		    	 tree.setStandardDeviation(e.getNewDesvioPadrao(antigavariancia, e.getVariancia(), antigoDP));
		    	 tree.setEndTime(middleTime);
		     }
	         
	         return tree;
	     }

	     public static TreeVariance updateDir(double middleTime, double endTime, double tolerance,
	    		 double antigavariancia, double antigoDP) throws NumberFormatException, ClassNotFoundException, PiecewiseException, SQLException, IOException{
	    	 DAO dao = new DAO();
	    	 Estatistica e = new Estatistica();
	    	 TreeVariance tree = new TreeVariance();
	    	 
	    	 double arraydir[] = dao.getTotalTravelTimeDir(Integer.parseInt("1"), middleTime);
 
	    	 e.setArray(arraydir);
	      
	         double middle_time = middleTime + ((endTime - middleTime) /2);

	         if (tolerance > e.getDesvioPadrao()){
 		    	 //Valor da Variância precisa ser atualizado para verificar se criação de novo nó é necessária.
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariance(e.getNewVariancia(antigavariancia, e.getVariancia()));
		    	 tree.setStandardDeviation(e.getNewDesvioPadrao(antigavariancia, e.getVariancia(), antigoDP));
		    	 tree.setEndTime(endTime);
		     }
	         
	         return tree;
	        
	     }
    }