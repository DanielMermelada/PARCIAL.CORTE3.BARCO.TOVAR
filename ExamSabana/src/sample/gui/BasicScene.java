package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.impl.PersonaService;

public class BasicScene extends Application {

    //Propiedades Visuales
    private Scene scene;
    private TableView<Persona> personasTable;
    private TextField nameInput;
    private TextField lastnameInput;
    private TextField ageInput;
    private TextField estadoInput;
    private TextField trabajoInput;
    private TextField cedInput;
    private Button addPersona;
    private Button deletePersona;

    //Propiedades Lógicas
    private IPersonaServices personaServices;

    //CRUD - Se crea un CRUD para la interfaz gráfica
    @Override
    public void start(Stage primaryStage) throws Exception{
        setUp();
        behavior();

        //Stage - Se crea la ventana de stage
        primaryStage.setTitle("Situación en Colombia");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void behavior(){
        this.personaServices = new PersonaService();
        try {
            this.personaServices.insert(new Persona("44", "Ivan", "Duque", "Conocido", "Presidente", "85890669"));
            this.personaServices.insert(new Persona("51", "Claudia", "Lopez", "Conocido", "Alcaldesa", "85890670"));
            this.personaServices.insert(new Persona("61", "Gustavo", "Petro", "Conocido", "Senador", "85890671"));
            this.personaServices.insert(new Persona("68", "Alvaro", "Uribe", "Conocido", "Ex-Presidente", "85890672"));
        }catch (PersonaException e) {
            e.printStackTrace();
        }

        personasTable.setItems(this.personaServices.getAll());
        addPersona.setOnAction(e-> {
            try {
                Persona p = new Persona(ageInput.getText(), nameInput.getText(), lastnameInput.getText(), estadoInput.getText(), trabajoInput.getText(), cedInput.getText());
                this.personaServices.insert(p);
                ageInput.clear();
                nameInput.clear();
                lastnameInput.clear();
                estadoInput.clear();
                trabajoInput.clear();
                cedInput.clear();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

        deletePersona.setOnAction(e ->{
            this.personaServices.delete(personasTable.getSelectionModel().getSelectedItems());
        });
    }

    private void setUp(){

        //Columna de edad
        TableColumn<Persona, Integer> ageColumn = new TableColumn<>("Edad");
        ageColumn.setMaxWidth(200);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        //Columna de nombre
        TableColumn<Persona, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Columna de apellido
        TableColumn<Persona, String> lastnameColumn = new TableColumn<>("Apellido");
        lastnameColumn.setMaxWidth(200);
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        //Columna de estado
        TableColumn<Persona, String> estadoColumn = new TableColumn<>("Estado");
        estadoColumn.setMaxWidth(200);
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        //Columna de trabajo
        TableColumn<Persona, String> trabajoColumn = new TableColumn<>("Trabajo");
        trabajoColumn.setMaxWidth(200);
        trabajoColumn.setCellValueFactory(new PropertyValueFactory<>("trabajo"));

        //Columna de cédula
        TableColumn<Persona, String> cedColumn = new TableColumn<>("Cédula");
        cedColumn.setMaxWidth(200);
        cedColumn.setCellValueFactory(new PropertyValueFactory<>("ced"));

        //Se settea la tabla
        personasTable= new TableView<>();
        personasTable.getColumns().add(ageColumn);
        personasTable.getColumns().add(nameColumn);
        personasTable.getColumns().add(lastnameColumn);
        personasTable.getColumns().add(estadoColumn);
        personasTable.getColumns().add(trabajoColumn);
        personasTable.getColumns().add(cedColumn);

        setupInputs();

        addPersona = new Button();
        addPersona.setText("¡Adiciona otra persona!");
        addPersona.setMinWidth(200);

        deletePersona = new Button();
        deletePersona.setText("¡Elimina a una persona!");
        deletePersona.setMinWidth(200);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(ageInput,nameInput,lastnameInput,estadoInput,trabajoInput,cedInput,addPersona,deletePersona);

        //Layout - Se crea para la escena
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(personasTable, hBox);

        //Scene - Se crea como punto de partida para la vista de resumen
        scene = new Scene(layout, 900,600);

    }

    private void setupInputs(){

        ageInput = new TextField();
        ageInput.setPromptText("edad");
        ageInput.setMinWidth(30);

        nameInput = new TextField();
        nameInput.setPromptText("nombre");
        nameInput.setMinWidth(30);

        lastnameInput = new TextField();
        lastnameInput.setPromptText("apellido");
        lastnameInput.setMinWidth(30);


        estadoInput = new TextField();
        estadoInput.setPromptText("estado");
        estadoInput.setMinWidth(30);

        trabajoInput = new TextField();
        trabajoInput.setPromptText("trabajo");
        trabajoInput.setMinWidth(30);

        cedInput = new TextField();
        cedInput.setPromptText("cédula");
        cedInput.setMinWidth(30);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
