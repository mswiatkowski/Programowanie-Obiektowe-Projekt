package agh.ics.proj1.gui;

import agh.ics.proj1.Animal;
import agh.ics.proj1.Grass;
import agh.ics.proj1.SimEngine;
import agh.ics.proj1.Vector2d;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GridCreator {

    public GridPane createGrid(int lowerYBorder, int upperYBorder,
                       int leftXBorder, int rightXBorder) {



        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30,30,30,30));
//        grid.setGridLinesVisible( true );

        List<Label> xAxisLabel = new ArrayList<>();
        List<Label> yAxisLabel = new ArrayList<>();
        for (int i = lowerYBorder; i < upperYBorder; i++) {
            yAxisLabel.add(new Label(String.format("%d", i)));
        }
        for (int i = 0; i < yAxisLabel.size(); i++) {
            grid.add(yAxisLabel.get(i), 0, upperYBorder-i+1+lowerYBorder);
            GridPane.setHalignment(yAxisLabel.get(i), HPos.CENTER);
        }


        for (int i = leftXBorder; i < rightXBorder; i++) {
            xAxisLabel.add(new Label(String.format("%d", i)));
        }
        for (int i = 1; i <= xAxisLabel.size(); i++) {
            grid.add(xAxisLabel.get(i-1), i, 0);
            GridPane.setHalignment(xAxisLabel.get(i-1), HPos.CENTER);
        }
        grid.add(new Label("y/x"), 0,0);

        // Dodawanie rysunkowych zwierząt
        for (Map.Entry<Vector2d, Animal> entry : SimEngine.mapanimals.entrySet()) {
            Label animlabl = new Label();
            Image animimg = new Image(entry.getValue().toString());
            ImageView imgViewAnimal = new ImageView(animimg);
            imgViewAnimal.setFitHeight(15);
            imgViewAnimal.setFitWidth(15);
            animlabl.setGraphic(imgViewAnimal);
            grid.add(animlabl,
                    entry.getKey().x+1,
                    upperYBorder - entry.getKey().y+lowerYBorder+1);
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
//                grid.getChildren().remove(animlabl);
//                grid.add(animlabl,
//                        entry.getKey().x+1,
//                        upperYBorder - entry.getKey().y+lowerYBorder+1);
//            }));
//            timeline.play();
        }

        // Dodawanie rysunkowej trawy
        for (Map.Entry<Vector2d, Grass> entry : SimEngine.mapgrasses.entrySet()) {
            Label grsslabl = new Label();
            Image grssimg = new Image(entry.getValue().toString());
            ImageView imgViewGrass = new ImageView(grssimg);
            imgViewGrass.setFitHeight(10);
            imgViewGrass.setFitWidth(10);
            grsslabl.setGraphic(imgViewGrass);
            grid.add(grsslabl,
                    entry.getKey().x+1,
                    upperYBorder - entry.getKey().y+lowerYBorder+1);
        }

        return grid;
    }
}
