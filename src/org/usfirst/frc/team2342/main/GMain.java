package org.usfirst.frc.team2342.main;

import io.jenetics.DoubleChromosome;
import io.jenetics.DoubleGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.DoubleRange;
import io.jenetics.util.Factory;
 
public class GMain {
	
    public static void main(String[] args) {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<DoubleGene>> gtf =
            Genotype.of(
            		DoubleChromosome.of(
            				DoubleRange.of(0, 1),
            				3
            		),
            		3
            );
        System.out.println("Initial:\n" + gtf);
 
        // 3.) Create the execution environment.
        Engine<DoubleGene, Double> engine = Engine
            .builder(Simulation::eval, gtf)
            .build();
 
        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<DoubleGene> result = engine.stream()
            .limit(100)
            .collect(EvolutionResult.toBestGenotype());
 
        System.out.println("Final:\n" + result);
    }
}