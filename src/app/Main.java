package app;


import creators.AbstractSceneCreator;
import data.Users;
import decorators.LoginFormDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static forms.LoginForm.LoginForm;
import static scenes.ScenesDetails.*;


public class Main extends Application {

    private Stage actualStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        actualStage = primaryStage;
        initLoginScene();
        actualStage.show();

    }


    public void initLoginScene() throws ParserConfigurationException, SAXException, IOException {
        Users.addUsers();
        Users.fillArrayList();

        actualStage.setTitle("Store Management");
        GridPane gridPane = LoginForm();

        LoginFormDecorator loginFormDecorator = new LoginFormDecorator(gridPane, actualStage);
        loginFormDecorator.addControls();

        loginScene = new Scene(gridPane, 800, 400);
        actualStage.setScene(loginScene);
    }

    public void fillWithDefaultData() {
        Users.addUsers();
        Users.fillArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

