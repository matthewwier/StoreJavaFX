package scenes;

import decorators.LoginFormDecorator;
import factory.LoginFormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * Factory for login scene.
 *
 */
public class LoginSceneCreator implements SceneFactory {

    public Scene createScene() {
        GridPane gridPane = new LoginFormFactory().createForm();
        LoginFormDecorator loginFormDecorator = new LoginFormDecorator();
        loginFormDecorator.addControls(gridPane);

        return new Scene(gridPane, 800, 400);
    }
}
