package javaFX.main;

import datos.SingletonDatos;
import entity.Plantilla;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    Stage window;
    Scene scene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        window.setTitle("Generador de C\u00f3digo");

        ListView<Plantilla> listPlantillas = new ListView<>();
        listPlantillas.getItems().addAll(SingletonDatos.getInstance().listaPlantillas);
        listPlantillas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Buttons
        Button btnVerPlantilla = new Button("Ver Plantilla");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listPlantillas, btnVerPlantilla);

        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }
}
