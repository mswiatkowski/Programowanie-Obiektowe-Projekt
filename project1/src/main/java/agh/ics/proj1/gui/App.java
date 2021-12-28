package agh.ics.proj1.gui;

import agh.ics.proj1.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class App extends Application {

    public static Stage window;
    public static Integer height;
    public static Integer width;
    public static Integer grassamount;
    public static Integer animalamount;
    public static Integer startenergy;
    public static Integer moveenergy;
    public static Integer plantenergy;
    public static Integer junglesize;
    public static GridPane grid1;
    public static GridPane grid2;
    public static int time = 1000; // czas w milisekundach

    public static void main(String[] args) {
        launch(args);
    }

    // Funkcja tworząca pola tekstowe
    public TextField textBox(String s, String i) {
        TextField field = new TextField(i);
        field.setPromptText(s);
        field.setPrefWidth(100);
        field.setMaxWidth(100);
        return field;
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Animal Survival");

        // Menu główne
        // Etykietki:
        Label mapHeight = new Label("Map Size:"); // Size, ponieważ width i height są takie same
        TextField textHeight = textBox("20", "20");
        Label mapGrass = new Label("Grass amount:");
        TextField textGrass = textBox("30", "30");
        Label mapAnim = new Label("Animal amount:");
        TextField textAnim = textBox("10", "10");
        Label startEnergy = new Label("Start energy:");
        TextField textEnergy = textBox("100", "100");
        Label moveEnergy = new Label("Move energy:");
        TextField textMove = textBox("10", "10");
        Label plantEnergy = new Label("Plant energy:");
        TextField textPlant = textBox("5", "10");
        Label jungleSize = new Label("Jungle size (bigger number, smaller jungle):");
        TextField textJungle = textBox("3", "3");
        Button startButton = new Button("Start!");
        // Umiejscowienie etykiet:
        VBox menuLayout = new VBox(10);
        menuLayout.getChildren().addAll(mapHeight, textHeight, mapGrass,
                textGrass, mapAnim, textAnim, startEnergy, textEnergy, moveEnergy, textMove,
                plantEnergy, textPlant, jungleSize, textJungle,
                startButton);
        menuLayout.setAlignment(Pos.CENTER);
        // Menu główne:
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setCenter(menuLayout);
        Scene menu = new Scene(borderPane2, 300, 520);



        // Symulacja
        HBox topMenu = new HBox();
        Button pause = new Button("Pause");
        Button menuReturn = new Button("Return");
        Button exit = new Button("Exit");
        topMenu.setPadding(new Insets(15,12,15,12));
        topMenu.setSpacing(10);
        topMenu.setStyle("-fx-background-color: #369933;");
        pause.setPrefSize(100,20);
        menuReturn.setPrefSize(100,20);
        exit.setPrefSize(100,20);
        topMenu.getChildren().addAll(pause, menuReturn, exit);

        Image animN = new Image("C:\\Users\\marti\\IdeaProjects\\project1\\src\\main\\resources\\animalN.png");
        ImageView viewN = new ImageView(animN);


        // Poniżej po wciśnięciu start uruchamiany jest silnik symulacji
        // oraz tworzone są mapy i wypełniane listy zwierząt i krzaków
        startButton.setOnAction(event -> {
            height = Integer.parseInt(textHeight.getText().trim());
            width = height;
            grassamount = Integer.parseInt(textGrass.getText().trim());
            animalamount = Integer.parseInt(textAnim.getText().trim());
            startenergy = Integer.parseInt(textEnergy.getText().trim());
            moveenergy = Integer.parseInt(textMove.getText().trim());
            plantenergy = Integer.parseInt(textPlant.getText().trim());
            junglesize = Integer.parseInt(textJungle.getText().trim());

            // Uruchamiany jest silnik, listy są wypełniane
            SimEngine engine = new SimEngine(width, height);
            engine.run();
            // Pierwsza mapa (nie zawijana)
            GridCreator tempGrid1 = new GridCreator();
            grid1 = tempGrid1.createGrid(0,height,0,width);

//            grid1.getChildren().clear();


            // Druga mapa
            GridCreator tempGrid2 = new GridCreator();
            grid2 = tempGrid2.createGrid(0, height, 0, width);

            // Scena symulacji
            BorderPane borderPane1 = new BorderPane();
            borderPane1.setLeft(grid1);
            borderPane1.setTop(topMenu);
            borderPane1.setRight(grid2);
//            borderPane1.setCenter(viewN);
            Scene simulation = new Scene(borderPane1, 90 * width, 50 * height);
            menuReturn.setOnAction(event1 -> {
                engine.stop();
                SimEngine.mapanimals.clear(); // czyści elementy na mapach po powrocie do menu
                SimEngine.listanimals.clear(); // tu też
                SimEngine.mapgrasses.clear(); // i tu
                SimEngine.listgrasses.clear(); // i tu także
                grid1.getChildren().clear();
                grid2.getChildren().clear();
                window.setScene(menu);
//                textHeight.clear();
//                textGrass.clear();
//                textEnergy.clear();
//                textMove.clear();
//                textPlant.clear();
//                textJungle.clear();
            });
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), eventCh -> {
                window.setScene(simulation);
//                window.show();
//            }));
//            timeline.play();
//            window.setScene(simulation);
            window.setFullScreen(true);

        });

        exit.setOnAction(event -> System.exit(0));


        window.setScene(menu);
        window.show();
    }
}
