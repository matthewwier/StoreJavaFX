package decorators;

import data.Users;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import scenes.LoginSceneFactory;


public class RegisterFormDecorator implements FormDecorator {


    @Override
    public void addControls(GridPane gridPane, Stage stage) {
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

        registerButton.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure to register as " + usernameField.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                Users.addUser(usernameField.getText(), passwordField.getText(), emailField.getText());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("Sign in using your email and password");
                alert2.setTitle("Registration");
                alert2.setHeaderText("User registered successfully!");
                alert2.show();
                stage.setScene(new LoginSceneFactory().createScene());
            }

        });
        gridPane.add(registerButton, 0, 4, 2, 1);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0, 20, 0));
    }


}
