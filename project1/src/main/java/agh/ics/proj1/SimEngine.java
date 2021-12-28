package agh.ics.proj1;

import agh.ics.proj1.gui.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimEngine {
    public static Map<Vector2d, Animal> mapanimals = new HashMap<Vector2d, Animal>();
    public static ArrayList<Animal> listanimals = new ArrayList<>();
    public static Map<Vector2d, Grass> mapgrasses = new HashMap<Vector2d, Grass>();
    public static ArrayList<Vector2d> listgrasses = new ArrayList<>();
    public static IMap map;
    private Vector2d[] positions;
    int width;
    int height;
    public static boolean exit = false;

    public SimEngine(Integer width, Integer height) {
        map = new RectMap(App.width,App.height);
        this.positions = new Vector2d[]{new Vector2d(2, 3),
                new Vector2d(3, 4),
                new Vector2d(3, 4)
        };
        this.width = width;
        this.height = height;
    }

    public static void addAnimal(IMap map, Animal animal, Vector2d initialPosition) {

            listanimals.add(animal);
            mapanimals.put(initialPosition, animal);
            System.out.println("Number of animals: " + mapanimals.keySet().size());
            System.out.println(SimEngine.mapanimals.keySet());

        Thread thr = new Thread(animal);
            thr.start();

    }

    public static void addGrass(IMap map, Grass grass, Vector2d position) {
        for (Animal anim : mapanimals.values()) {
            if ((anim.initialPosition.x != grass.getPosition().x)
                && (anim.initialPosition.y != grass.getPosition().y)
                && (!(mapanimals.containsKey(grass.getPosition())))) {
                listgrasses.add(grass.getPosition());
                mapgrasses.put(grass.getPosition(), grass);
//                System.out.println(mapgrasses.keySet());
                Thread thr = new Thread(grass);
//                thr.start();
            }
        }
    }

    public void run() {
        try {
//            while (!exit) {
                System.out.println("RUN!!!");

                for (int i = 0; i < App.animalamount; i++) {
                    int max = App.height;
                    if (App.height < App.width) {
                        max = App.width;
                    }
                    int min = 0;
                    int randomx = (int) ((Math.random() * (max - min)) + min);
                    int randomy = (int) ((Math.random() * (max - min)) + min);
                    Animal animal = new Animal(map, new Vector2d(randomx, randomy));
                    addAnimal(map, animal, animal.initialPosition);
                }

                for (int i = 0; i < 30; i++) {
                    int max = App.height;
                    if (App.height < App.width) {
                        max = App.width;
                    }
                    int min = 0;
                    int randomx = (int) ((Math.random() * (max - min)) + min);
                    int randomy = (int) ((Math.random() * (max - min)) + min);
                    addGrass(map, new Grass(new Vector2d(randomx, randomy)), new Vector2d(randomx, randomy));
                    int jungleMax = App.height - App.junglesize;
                    int jungleMin = App.junglesize;
                    int jungleRandomx1 = (int) ((Math.random() * (jungleMax - jungleMin)) + jungleMin);
                    int jungleRandomy1 = (int) ((Math.random() * (jungleMax - jungleMin)) + jungleMin);
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
                    SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));

                }
//            }

        }catch (Exception ignored) {}
    }
    public void stop() {
        exit = true;
    }
}
