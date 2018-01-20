package org.usfirst.frc.team2342.main;

import io.jenetics.DoubleGene;
import io.jenetics.Genotype;
import io.jenetics.NumericGene;

import jeigen.DenseMatrix;
import static jeigen.Shortcuts.*;

public class Simulation {
	
	// genotypeToMatrix
	// Turns a numeric genotype (Genotype<NumericGene>) into a DenseMatrix
	
	public static <N extends Number & Comparable<? super N>,G extends NumericGene<N,G>> DenseMatrix genotypeToMatrix(Genotype<G> gt) {
		int rows = gt.length();
		int cols = gt.getChromosome().length();
		
		DenseMatrix mat = zeros(rows, cols);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				mat.set(i, j, (double) gt.get(i, j).getAllele());
			}
		}
		
		return mat;
	}
	
	public static double eval(Genotype<DoubleGene> gt) {
		DenseMatrix matrix = genotypeToMatrix(gt);
		
		DenseMatrix state = zeros(matrix.cols, 1);
		state.set(0, 0, 1);
		
		// Starts in State A:
		
		// [ A ]    [ 1 ]
		// [ B ] -> [ 0 ]
		// [ C ]    [ 0 ]
		
		for (int i = 0; i < 2; i++) {
			state = matrix.mmul(state);
		}
		
		
		// Try to maximize on State C
		return state.get(2, 0);
	}
}
