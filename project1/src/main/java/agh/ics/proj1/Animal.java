package agh.ics.proj1;

import agh.ics.proj1.gui.App;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Animal implements Runnable {
    public final IMap map;
    public Vector2d initialPosition;
    public MapDir orientation;
    public List<Integer> genes;
    public int energy;
    public static GridPane grid1;
    public int id;

    public Animal(IMap map, Vector2d position) {
        this.id = id;
        this.orientation = MapDir.NE;
        this.map = map;
        this.initialPosition = position; // ustalenie początkowej pozycji zwierzaka
        this.genes = new ArrayList<>(); // utworzenie listy genów
        Genes animalGenes = new Genes(genes);
        animalGenes.geneGenerator(genes); // rozbudowanie listy genów o wygenerowaną przez generator genów
        Collections.sort(genes); // posortowanie genów dla lepszego wglądu
        this.energy = App.startenergy; // ustalenie energii początkowej (będzie dotyczyć tylko przodków)
    }

    public void starving(Animal animal, int cost) {
        animal.energy = animal.energy - cost;
        System.out.println("Energy DOWN! " + animal.energy);
        if (animal.energy == 0) {
            SimEngine.mapanimals.remove(animal.initialPosition);
            System.out.println("Starved!!!");
            System.out.println("Number of animals: " + SimEngine.mapanimals.size());
        }
    }

    public boolean checkPosition(Vector2d other) {
        return this.initialPosition.equals(other);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (orientation.equals(MapDir.N)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalN.png");
        }
        else if (orientation.equals(MapDir.NE)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalNE.png");
        }
        else if (orientation.equals(MapDir.E)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalE.png");
        }
        else if (orientation.equals(MapDir.SE)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalSE.png");
        }
        else if (orientation.equals(MapDir.S)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalS.png");
        }
        else if (orientation.equals(MapDir.SW)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalSW.png");
        }
        else if (orientation.equals(MapDir.W)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalW.png");
        }
        else if (orientation.equals(MapDir.NW)) {
            builder.append("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalNW.png");
        }
        return builder.toString();
    }

    public boolean isAt(Vector2d position1) {
        return initialPosition.toString().equals(position1.toString());
    }

    public void move(Integer gene) {
        switch (gene) {
            case 1 -> orientation = orientation.next();
            case 2 -> orientation = orientation.next().next();
            case 3 -> orientation = orientation.next().next().next();
            case 4 -> orientation = orientation.next().next().next().next();
            case 5 -> orientation = orientation.previous().previous().previous();
            case 6 -> orientation = orientation.previous().previous();
            case 7 -> orientation = orientation.previous();
            case 0 -> {
            } // Tu nic się nie zmienia
        }
        Vector2d newPosition = initialPosition.add(orientation.toUnitVector());
//        System.out.println(newPosition);
            // Aktualizujemy mapanimals, na podstawie którego tworzona jest mapa
            Animal currentAnimal = SimEngine.mapanimals.get(initialPosition);
            SimEngine.mapanimals.remove(initialPosition);
            SimEngine.mapanimals.put(newPosition, currentAnimal);
            initialPosition = newPosition;
//            System.out.println(SimEngine.mapanimals.keySet());
            starving(currentAnimal, App.moveenergy);
            eatGrass();
            breeding();
    }


    public void eatGrass() {
        for (Animal a: SimEngine.listanimals) {
            if (SimEngine.listgrasses.contains(a.initialPosition)) {
                a.energy = a.energy + App.plantenergy;
                SimEngine.listgrasses.remove(initialPosition);
                System.out.println("Energy UP! " + a.energy);
            }
        }
    }

    public void breeding() {
        List<Vector2d> toBreed = new ArrayList<>();
        List<Vector2d> unique = new ArrayList<>();
        for (Vector2d v: SimEngine.mapanimals.keySet()) {
            if (!(unique.contains(v))) {
                unique.add(v);
            }
            else {
                toBreed.add(v);
            }
        }
        for (Vector2d v : toBreed) {
            SimEngine.addAnimal(SimEngine.map, new Animal(SimEngine.map, v), v);
            Animal animalParent = SimEngine.mapanimals.get(v);
            int newEnergy = animalParent.energy - (animalParent.energy / 4);
            starving(animalParent, newEnergy);
            System.out.println("Breed!!!");
        }
    }

    @Override
    public void run() {
        try {
            while (!SimEngine.exit) {
//                for (int i=0; i < 100000; i++) {
                    Thread.sleep(App.time);
//                    System.out.println("Yawn...");
                    Random rand = new Random();
                    int randomElement = genes.get(rand.nextInt(genes.size()));
//                    System.out.println(randomElement);
//                    System.out.println(SimEngine.mapanimals.keySet());
                    this.move(randomElement);
                    if (SimEngine.exit) {
                        Thread.currentThread().interrupt();
                    }
//                }
            }
        } catch (Exception ignored){}
    }
}
