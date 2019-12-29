package decorators;


import classes.User;
import data.Users;
import factory.AbstractFormFactory;
import factory.FormFactory;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static scenes.ScenesDetails.*;

public class LoginFormDecorator extends FormDecorator {


    public LoginFormDecorator(GridPane gridPane, Stage stage) {
        super(gridPane, stage);
    }

    @Override
    public void addControls() {
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

        AbstractFormFactory factory = new FormFactory();
        GridPane userPane = factory.createUserForm();

        UserFormDecorator userFormDecorator = new UserFormDecorator(userPane, stage);
        userFormDecorator.addControls();

        loginButton.setOnAction(e ->
        {
            String typedLogin = usernameField.getText();
            String typedPassword = passwordField.getText();
            for (User user : Users.userList) {
                if (user.getLogin().equals(typedLogin)) {
                    if (user.getPassword().equals(typedPassword)) {
                        stage.setScene(new Scene(userPane, 800, 400));
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


        GridPane registerPane = factory.createUserForm();
        RegisterFormDecorator registerFormDecorator = new RegisterFormDecorator(registerPane, stage);
        registerFormDecorator.addControls();

        registerScene = new Scene(registerPane, 800, 400);
        registerButton.setOnAction(e ->
        {
            stage.setScene(registerScene);
        });
    }


}
