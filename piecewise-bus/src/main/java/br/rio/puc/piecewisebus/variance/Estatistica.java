package br.rio.puc.piecewisebus.variance;

import java.util.Arrays;


public class Estatistica {


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

      // Desvio Padrão Amostral
      public double getDesvioPadrao() {

            return Math.sqrt(getVariancia());

      }
      
      // Nova Variância Amostral
      public double getNewVariancia(double old_variace, double new_variance) {

    	    return (old_variace + ((new_variance - old_variace) / Double.valueOf(array.length)));

      }

      // Novo Desvio Padrão Amostral
      public double getNewDesvioPadrao(double old_variace, double new_variance, double old_desviopadrao) {

            return Math.sqrt( (1 - (1 / Double.valueOf(array.length))) * Math.pow(old_desviopadrao, 2) + 
            		(Double.valueOf(array.length) + 1) * Math.pow((new_variance - old_variace), 2));


      }

      public double[] getArray() {

            return array;

      }

 

      public void setArray(double[] array) {

            this.array = array;

      }

}