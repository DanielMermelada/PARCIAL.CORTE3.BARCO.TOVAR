package sample.gui;


import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.logic.entities.Persona;


public class ReportScene extends BasicScene {

    //Propiedades Visuales de ReportScene - Son las propiedades de nuestra ventana extra
    private Stage stage;
    private Label label;
    private Label label2;
    private Label label3;
    private Scene scene;
    private int count;



    public ReportScene(){
        stage = new Stage();

        setUp();
        behavior(stage);

        stage.setTitle("¡Reporte!");
        stage.setScene(scene);
        stage.show();
    }


    //setUp de ReportScene - es una division que se encarga de settear (Hacer visual) las propiedades definidas
    private void setUp(){
        Counter();
        label = new Label("Disclamer: Estos datos son datos fijos hipotéticos no reales...");
        label.setStyle("-fx-background-color: LIGHTCORAL");
        label.setFont(Font.font("Agency FB", FontWeight.SEMI_BOLD, 15.55));
        label2 = new Label("Personas Conocidas: " + this.count);
        label2.setFont(Font.font("Agency FB", FontWeight.NORMAL, FontPosture.REGULAR, 28));
        label3 = new Label("Protestas realizadas: 21");
        label3.setFont(Font.font("Agency FB", FontWeight.NORMAL, FontPosture.REGULAR, 28));


        VBox layout = new VBox(30);
        layout.getChildren().add(label);
        layout.getChildren().add(label2);
        layout.getChildren().add(label3);

        layout.setStyle("-fx-background-color: LIGHTSLATEGRAY;");
        scene = new Scene(layout, 300, 400);
    }
    private void behavior(Stage stage) { }

    private int Counter(){

        for(Persona p: personaTableList)
        {
            String estado = p.getEstado();
            if(estado.equals("Conocido")){
                count++;
            }

        }
        return count;
    }


}
