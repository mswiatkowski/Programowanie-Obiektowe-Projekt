package agh.ics.proj1;

import java.util.List;

public class Genes {
    public List<Integer> genes;

    public Genes(List<Integer> genes) {
        this.genes = genes;
    }

    public void geneGenerator(List<Integer> gens) {
        for(int i = 0; i < 32; i++) {
            int max = 8;
            int min = 0;
            Integer newGene = (int) ((Math.random() * (max - min)) + min);
            gens.add(newGene);
        }
    }
}
