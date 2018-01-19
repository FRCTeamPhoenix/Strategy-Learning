package org.usfirst.frc.team2342.main;

import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;

public class Simulation {
	int state;
	
	public static int[][] genotypeToMatrix(Genotype<IntegerGene> gtf) {
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
	}
	
	public static int eval(Genotype<IntegerGene> gt) {
		int[][] matrix = genotypeToMatrix(gt);
		return 0;
	}
}
