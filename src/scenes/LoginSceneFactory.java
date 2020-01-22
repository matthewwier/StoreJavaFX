package scenes;

import context.ApplicationContext;
import decorators.LoginFormDecorator;
import factory.FormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class LoginSceneFactory implements SceneFactory {

    public Scene createScene() {
        GridPane gridPane = new FormFactory().createLoginForm();
        LoginFormDecorator loginFormDecorator = new LoginFormDecorator();
        loginFormDecorator.addControls(gridPane,
                ApplicationContext.getInstance().getActualStage());

        return new Scene(gridPane, 800, 400);
    }
}
