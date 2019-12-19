package app;

import classes.Employee;
import classes.Item;
import classes.Owner;
import creators.AbstractSceneCreator;
import creators.DetailsSceneCreator;
import creators.StoreSceneCreator;
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
import classes.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static forms.LoginForm.LoginForm;
import static forms.UserForm.UserForm;
import static xmloperations.XMLOperations.*;

public class Main extends Application {
    private AbstractSceneCreator sceneCreator;

    private Stage actualStage;
    private Scene loginScene;
    private Scene registerScene;
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
//        data.addAll(new Item("Stół", "12", "Brązowy"),
//                new Item("Sofa", "15", "Żółta"),
//                new Item("Okno", "20", "Duże"),
//                new Item("Parapet", "11", "Kolorowy"),
//                new Item("Krzesło", "4", "Czarne"));
    }


    public void addUsers() {
        userList = new ArrayList<>();
        userList.add(new User("danny", "danny", "danny@gmail.com"));
    }

    public void addUser(String username, String password, String email) {
        userList.add(new User(username, password, email));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        addUsers();
        fillArrayList();
        actualStage = primaryStage;
        actualStage.setTitle("Store Management");

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

        Label headerLabel = new Label("SIGN IN");
        headerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(usernameLabel, 0, 1);


        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 1);

//        Label emailLabel = new Label("Email:");
//        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//        gridPane.add(emailLabel, 0, 2);
//
//        TextField emailField = new TextField();
//        emailField.setPrefHeight(40);
//        gridPane.add(emailField, 1, 2);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);

        Button loginButton = new Button("Log in");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);

        Button registerButton = new Button("Register");
        registerButton.setPrefHeight(40);
        registerButton.setDefaultButton(true);
        registerButton.setPrefWidth(100);


        // LOGIN - przejście do UserForm as scene
        GridPane userPane = UserForm();
        addUserFormControls(userPane, actualStage);

        userScene = new Scene(userPane, 800, 400);
        loginButton.setOnAction(e ->
        {
            String typedLogin = usernameField.getText();
            String typedPassword = passwordField.getText();
            for (User user : userList) {
                if (user.getLogin().equals(typedLogin)) {
                    if (user.getPassword().equals(typedPassword)) {
                        actualStage.setScene(userScene);
                    }
                }
            }
        });
        gridPane.add(loginButton, 0, 4, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20, 100, 20, 0));

        gridPane.add(registerButton, 0, 4, 2, 1);
        GridPane.setHalignment(registerButton, HPos.RIGHT);
        GridPane.setMargin(registerButton, new Insets(20, 250, 20, 0));

        // przejsce do rejestracji
        GridPane registerPane = UserForm();
        addRegisterFormControls(registerPane, actualStage);

        registerScene = new Scene(registerPane, 800, 400);
        addRegisterFormControls(registerPane, actualStage);
        registerButton.setOnAction(e ->
        {
            actualStage.setScene(registerScene);
        });
    }


    private void addRegisterFormControls(GridPane gridPane, Stage actualStage) {
        Label headerLabel = new Label("SIGN UP");
        headerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(usernameLabel, 0, 1);


        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 1);

        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(emailLabel, 0, 2);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);


        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        Button registerButton = new Button("Register");
        registerButton.setPrefHeight(40);
        registerButton.setDefaultButton(true);
        registerButton.setPrefWidth(100);


        // LOGIN - przejście do UserForm as scene
        GridPane userPane = UserForm();
        addUserFormControls(userPane, actualStage);

        userScene = new Scene(userPane, 800, 400);
        registerButton.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure to register as " + usernameField.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                addUser(usernameField.getText(), passwordField.getText(), emailField.getText());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("Sign in using your email and password");
                alert2.setTitle("Registration");
                alert2.setHeaderText("User registered successfully!");
                alert2.show();
                actualStage.setScene(loginScene);
            }

        });
        gridPane.add(registerButton, 0, 4, 2, 1);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0, 20, 0));

        // przejsce do rejestracji
    }

    private void addUserFormControls(GridPane userPane, Stage actualStage) {
        Label infoLabel = new Label("List of elements of your XML file:");
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
                        textAreaXML.setText(readXMLFile(xmlFile));
                        if (employees.size() == 0) {
                            addItemsDataFromXMLFile(xmlFile, data);
                            addEmployeesDataFromXMLFile(xmlFile, employees);
                            owner = addOwnerFromXMLFile(xmlFile);
                        }

                    } catch (IOException | ParserConfigurationException | SAXException | TransformerException ex) {
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
                    sceneCreator = new DetailsSceneCreator();
                    actualStage.setScene(sceneCreator.create(actualStage, userScene, employees, owner, xmlFile, textAreaXML));
                }
        );

        vbox.getChildren().addAll(openButton, detailsButton, closeAppButton);

        userPane.add(vbox, 0, 2);
        userPane.add(textAreaXML, 1, 2);
        Button toStore = new Button();
        toStore.setText("To Store");
        toStore.setOnAction(e -> {
            sceneCreator = new StoreSceneCreator();
            actualStage.setScene(sceneCreator.create(actualStage, userScene, data, owner, xmlFile, textAreaXML));
        });


        userPane.add(toStore, 3, 2);

    }


}

