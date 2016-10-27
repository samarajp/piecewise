package br.rio.puc.piecewisebus.test_estimator;

import java.io.IOException;
import java.sql.SQLException;

import br.rio.puc.piecewisebus.estimator.ManipulatorFunction;
import br.rio.puc.piecewisebus.function.PiecewiseException;

public class Test_Estimator {

	public static void main(String[] args) 
			throws NumberFormatException, ClassNotFoundException, PiecewiseException, SQLException, IOException {
		// TODO Auto-generated method stub

		//Tolerancia em Milisegundos
		ManipulatorFunction test = new ManipulatorFunction();
		test.build_treeEstimator(8, 1, 900000, 0.15);
		
	}

}
