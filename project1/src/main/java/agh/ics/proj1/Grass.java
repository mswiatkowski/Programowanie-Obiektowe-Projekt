package agh.ics.proj1;


import agh.ics.proj1.gui.App;
import javafx.application.Application;

public class Grass implements Runnable {
    public Vector2d grassPosition;

    public Grass(Vector2d grassPosition){
        this.grassPosition = grassPosition;
    }

    public Vector2d getPosition() {
        return this.grassPosition;
    }

    @Override
    public String toString() {
        String grass = "C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\grass.png";
        return grass;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(1000);
            int max = App.height - 1;
            int min = 0;
            int randomx = (int) ((Math.random() * (max - min)) + min);
            int randomy = (int) ((Math.random() * (max - min)) + min);
            SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(randomx, randomy)), new Vector2d(randomx, randomy));

            // Poniżej tworzona jest trawa na obszarze zajmowanym przez dżunglę
            int jungleMax = App.height - App.junglesize;
            int jungleMin = App.junglesize;
            int jungleRandomx1 = (int) ((Math.random() * (jungleMax - jungleMin)) + jungleMin);
            int jungleRandomy1 = (int) ((Math.random() * (jungleMax - jungleMin)) + jungleMin);
            SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
            SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
            SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));
            SimEngine.addGrass(SimEngine.map, new Grass(new Vector2d(jungleRandomx1, jungleRandomy1)), new Vector2d(jungleRandomx1, jungleRandomy1));

            if (SimEngine.exit) {
                Thread.currentThread().interrupt();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
