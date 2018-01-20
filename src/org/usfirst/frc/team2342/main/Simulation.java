package org.usfirst.frc.team2342.main;

import io.jenetics.Chromosome;
import io.jenetics.DoubleGene;
import io.jenetics.Gene;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.NumericGene;
import jeigen.DenseMatrix;

import static jeigen.Shortcuts.*;

public class Simulation {
	
	/*public static int[][] genotypeToMatrix(Genotype<IntegerGene> gtf) {
		int[][] matrix = new int[gtf.length()][gtf.getChromosome(0).length()];
		int x = 0;
		int y = 0;
		for (Chromosome<IntegerGene> c : gtf) {
			x = 0;
			for (IntegerGene g : c) {
				matrix[y][x] = g.intValue();
				x++;
			}
			y++;
		}
		return matrix;
	}*/
	
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
	
	public static int eval(Genotype<DoubleGene> gt) {
		DenseMatrix matrix = genotypeToMatrix(gt);
		System.out.println(matrix);
		
		DenseMatrix state = zeros(matrix.cols, 1);
		state.set(0, 0, 1);
		System.out.println(state);
		
		// Starts in State A:
		
		// [ A ]    [ 1 ]
		// [ B ] -> [ 0 ]
		// [ C ]    [ 0 ]
		
		for (int i = 0; i < 2; i++) {
			state = matrix.mmul(state);
		}
		
		
		// Try to end on State C
		return (int) state.get(2, 0) * 1000;
	}
}
