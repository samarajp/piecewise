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
		    	 tree.setMedia(e.getMedia());
		    	 tree.setVariancia(e.getVariancia());
		    	 tree.setStandardDeviation(e.getDesvioPadrao());
		    	 tree.setEndTime(time.getEndTime());
		    	 
		  //  	 arvore.insereBusData(tree);

             }
             
    		 System.out.println("criou raiz");

	    	 arvore.percorrerInOrder();

    		 System.out.println("fim criacao da raiz");

	    	
	    	 /**
	    	  * Recebendo um novo DS, dada uma janela de tempo
	    	  * Expandindo a Árvore
	    	  */
	    	 double new_timewindow = time.getStartTime(); //Valor pode mudar
	    	 double antigamedia = e.getMedia();
	    	 double antigoDP = e.getDesvioPadrao();
	    	 double tolerance = (e.getVariancia() - antigamedia) / antigoDP;
	    	 
    		 System.out.println("new_timewindow: "+ new_timewindow);
    		 System.out.println("antigamedia: "+ antigamedia);
    		 System.out.println("antigoDP: "+ antigoDP);
    		 System.out.println("tolerance: "+ tolerance);


	    	 if (new_timewindow < time.getMiddleTime() ){
	    		 System.out.println("criando no a esquerda");
	   // 		 arvore.insereBusData(updateEsq(time.getStartTime(), time.getMiddleTime(), tolerance, antigamedia, antigoDP));
	    	 }
	    	 else{
	    		 System.out.println("criando no a direita");
	    //		 arvore.insereBusData(updateDir(time.getMiddleTime(), time.getEndTime(), tolerance, antigamedia, antigoDP));
	    	 }
	    	 
	    	 arvore.percorrerInOrder();
	    	

	     }
	     
	     public static TreeVariance updateEsq(double startTime, double middleTime, double tolerance,
	    		 double antigamedia, double antigoDP) throws NumberFormatException, ClassNotFoundException, PiecewiseException, SQLException, IOException{
	    	 DAO dao = new DAO();
	    	 Estatistica e = new Estatistica();
	    	 TreeVariance tree = new TreeVariance();
	    	 
	    	 double arrayesq[] = dao.getTotalTravelTimeEsq(Integer.parseInt("1"), middleTime);
 
	    	 e.setArray(arrayesq);
	      
	         double middle_time = startTime + ((middleTime - startTime) /2);
	         
    		 System.out.println("Entrou aqui 1");

	         if (tolerance < e.getDesvioPadrao()){
	    		 System.out.println("Entrou aqui 2");

 		    	 //Valor da Variância precisa ser atualizado para verificar se criação de novo nó é necessária.
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariancia(e.getVariancia());
		    	 tree.setMedia(e.getNewMedia(antigamedia, e.getMedia()));
		    	 tree.setStandardDeviation(e.getNewDesvioPadrao(antigamedia, e.getMedia(), antigoDP));
		    	 tree.setEndTime(middleTime);
		     }
	         
	         return tree;
	     }

	     public static TreeVariance updateDir(double middleTime, double endTime, double tolerance,
	    		 double antigamedia, double antigoDP) throws NumberFormatException, ClassNotFoundException, PiecewiseException, SQLException, IOException{
	    	 DAO dao = new DAO();
	    	 Estatistica e = new Estatistica();
	    	 TreeVariance tree = new TreeVariance();
	    	 
	    	 double arraydir[] = dao.getTotalTravelTimeDir(Integer.parseInt("1"), middleTime);
 
	    	 e.setArray(arraydir);
	      
	         double middle_time = middleTime + ((endTime - middleTime) /2);

	         if (tolerance > e.getDesvioPadrao()){
	        	 
	    		 System.out.println("Entrou aqui 3");

 		    	 //Valor da Variância precisa ser atualizado para verificar se criação de novo nó é necessária.
		    	 tree.setMiddleTime(middle_time);
		    	 tree.setFunction(e.getVariancia());
		    	 tree.setVariancia(e.getVariancia());
		    	 tree.setMedia(e.getNewMedia(antigamedia, e.getMedia()));
		    	 tree.setStandardDeviation(e.getNewDesvioPadrao(antigamedia, e.getMedia(), antigoDP));
		    	 tree.setEndTime(endTime);
		     }
	         
	         return tree;
	        
	     }
    }