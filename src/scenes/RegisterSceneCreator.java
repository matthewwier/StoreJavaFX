package scenes;

import decorators.RegisterFormDecorator;
import factory.UserFormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class RegisterSceneCreator implements SceneFactory {

    @Override
    public Scene createScene() {
        GridPane registerPane = new UserFormFactory().createForm();
        RegisterFormDecorator registerFormDecorator = new RegisterFormDecorator();
        registerFormDecorator.addControls(registerPane);

        return new Scene(registerPane, 800, 400);
    }
}
