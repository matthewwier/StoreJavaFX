package app;

import data.Users;
import decorators.LoginFormDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static forms.LoginForm.LoginForm;
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
        GridPane gridPane = LoginForm();
        LoginFormDecorator loginFormDecorator = new LoginFormDecorator(gridPane, actualStage);
        loginFormDecorator.addControls();
        loginScene = new Scene(LoginForm(), 800, 400);
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

