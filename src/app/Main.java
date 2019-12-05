package app;

import classes.Employee;
import classes.Item;
import classes.Owner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import users.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static creators.SceneCreator.*;
import static loginForm.LoginForm.LoginForm;
import static userForm.UserForm.UserForm;
import static xmloperations.XMLOperations.*;

public class Main extends Application {

    private Stage actualStage;
    private Scene loginScene;
    private Scene userScene;

    private List<User> userList;
    private final ObservableList<Item> data =
            FXCollections.observableArrayList();
    private final ObservableList<Employee> employees =
            FXCollections.observableArrayList();
    private Owner owner = null;

    private HBox hb;
    private TableView<Item> table;
    private File xmlFile;


    public void fillArrayList() {
        data.addAll(new Item("Stół", "12", "Brązowy"),
                new Item("Sofa", "15", "Żółta"),
                new Item("Okno", "20", "Duże"),
                new Item("Parapet", "11", "Kolorowy"),
                new Item("Krzesło", "4", "Czarne"));
    }


    public void addUsers() {
        userList = new ArrayList<>();
        userList.add(new User("danny", "danny", "danny@gmail.com"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        addUsers();
        fillArrayList();
        actualStage = primaryStage;
        actualStage.setTitle("Aplikacja do zarządzania magazynem");

        GridPane gridPane = LoginForm();
        addLoginFormControls(gridPane, actualStage);
        loginScene = new Scene(gridPane, 800, 400);
        actualStage.setScene(loginScene);

        actualStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addLoginFormControls(GridPane gridPane, Stage actualStage) throws IOException, SAXException, ParserConfigurationException {

        Label headerLabel = new Label("ZALOGUJ SIĘ");
        headerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        Label nameLabel = new Label("Username:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(nameLabel, 0, 1);


        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1, 1);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);

        Button submitButton = new Button("Log in");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);

        // LOGIN - przejście do UserForm as scene
        GridPane userPane = UserForm();
        addUserFormControls(userPane, actualStage);

        userScene = new Scene(userPane, 800, 400);
        submitButton.setOnAction(e ->
        {
            String typedLogin = nameField.getText();
            String typedPassword = passwordField.getText();
            for (User user : userList) {
                if (user.getLogin().equals(typedLogin)) {
                    if (user.getPassword().equals(typedPassword)) {
                        actualStage.setScene(userScene);
                    }
                }
            }
        });
        gridPane.add(submitButton, 0, 3, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));
    }

    private void addUserFormControls(GridPane userPane, Stage actualStage) {
        Label infoLabel = new Label("Content of your XML file:");
        infoLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        userPane.add(infoLabel, 1, 0, 2, 1);
        GridPane.setHalignment(infoLabel, HPos.CENTER);
        GridPane.setMargin(infoLabel, new Insets(20, 0, 20, 0));

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Select XML file...");
        TextArea textAreaXML = new TextArea();
        textAreaXML.setWrapText(true);
        openButton.setOnAction(
                e -> {
                    xmlFile = fileChooser.showOpenDialog(actualStage);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(xmlFile));
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        //String filteredContent = content.toString().replaceAll(" ", "");
                        System.out.println(content.toString());
                        textAreaXML.setText(content.toString());
                        // parse XML

                        addItemsDataFromXMLFile(xmlFile, data);
                        addEmployeesDataFromXMLFile(xmlFile, employees);
                        owner = addOwnerFromXMLFile(xmlFile);

                    } catch (IOException | ParserConfigurationException | SAXException ex) {
                        ex.printStackTrace();
                    }


                });
        openButton.setMinWidth(150);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Button closeAppButton = new Button("Close app");
        closeAppButton.setMinWidth(150);
        closeAppButton.setOnAction(e -> {
                    Stage stage = (Stage) closeAppButton.getScene().getWindow();
                    stage.close();
                }
        );

        Button detailsButton = new Button("See store details");
        detailsButton.setMinWidth(150);
        detailsButton.setOnAction(e -> {
                    actualStage.setScene(createDetailsScene(actualStage, userScene, employees, owner));
                }
        );

        vbox.getChildren().addAll(openButton, detailsButton, closeAppButton);

        userPane.add(vbox, 0, 2);
        userPane.add(textAreaXML, 1, 2);
        Button back = new Button();
        back.setText("To Store");
        back.setOnAction(e -> {
            actualStage.setScene(createStoreScene(actualStage, userScene, data));
        });


        userPane.add(back, 3, 2);

    }


}

