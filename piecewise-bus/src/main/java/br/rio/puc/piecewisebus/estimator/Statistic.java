package br.rio.puc.piecewisebus.estimator;

import java.util.Arrays;


public class Statistic {


      private double array[];
 
      public double getSomaDosElementos() {

            double total = 0;

            for (int counter = 0; counter < array.length; counter++)

                  total += array[counter];

            return total;

      }
 

      public double getSomaDosElementosAoQuadrado() {

            double total = 0;

            for (int counter = 0; counter < array.length; counter++)

                  total += Math.pow(array[counter], 2);

            return total;

      }

       public void ordenar() {

            Arrays.sort(array);

      }

 
      // Variância Amostral
      public double getVariancia() {

            double p1 = 1 / Double.valueOf(array.length - 1);

            double p2 = getSomaDosElementosAoQuadrado()

                        - (Math.pow(getSomaDosElementos(), 2) / Double

                                   .valueOf(array.length));

            return p1 * p2;

      }
      
    
      public double getMedia() {

    	  double media = 0;
    	  
    	  for (int i = 0; i < array.length; i++) {
			media += array[i];    
    	  }

          return (media/array.length);

      }
      
      // Desvio Padrão Amostral
      public double getDesvioPadrao() {

            return Math.sqrt(getVariancia());

      }
      
      // Nova Media
      public double getNewMedia(double old_media, double new_media) {

    	    return (old_media + ((new_media - old_media) / Double.valueOf(array.length + 1)));

      }

      // Novo Desvio Padrão Amostral
      public double getNewDesvioPadrao(double old_media, double new_media, double old_desviopadrao) {

            return Math.sqrt( (1 - (1 / Double.valueOf(array.length))) * Math.pow(old_desviopadrao, 2) + 
            		(Double.valueOf(array.length) + 1) * Math.pow((new_media - old_media), 2));


      }
      
      public double updateFunctionValue(double gama_value, double start_time, double end_time, double expected_time) {
 		
    	double distance_s = Math.sqrt(Math.pow((end_time - expected_time), 2));

 		if(distance_s <= (1/gama_value)){
 			return 0;
 		}
 		else if(distance_s > (1/gama_value)){
 			return 1;
 		}
 		else{
 			return ((1 / 2) + ((15 * gama_value) / 16) * distance_s - 
 					(( 5 * Math.pow(gama_value, 3) / 8 ) * Math.pow(distance_s, 3)) +
 					(( 3 * Math.pow(gama_value, 5) / 16 ) * Math.pow(distance_s, 5)));
 		}      	
      }

      public double[] getArray() {

            return array;

      }

 

      public void setArray(double[] array) {

            this.array = array;

      }

}