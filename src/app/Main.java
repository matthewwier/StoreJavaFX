package app;

import data.Users;
import decorators.LoginFormDecorator;
import factory.AbstractFormFactory;
import factory.FormFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static scenes.ScenesDetails.*;


public class Main extends Application {

    private Stage actualStage;

    @Override
    public void start(Stage primaryStage) {

        actualStage = primaryStage;
        initLoginScene();
        actualStage.show();

    }


    private void initLoginScene() {
        fillWithDefaultData();

        actualStage.setTitle("Store Management");

        AbstractFormFactory factory = new FormFactory();
        GridPane gridPane = factory.createLoginForm();

        LoginFormDecorator loginFormDecorator = new LoginFormDecorator(gridPane, actualStage);
        loginFormDecorator.addControls();

        loginScene = new Scene(gridPane, 800, 400);
        actualStage.setScene(loginScene);
    }

    private void fillWithDefaultData() {
        Users.addUsers();
        Users.fillArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

