package scenes;

import context.ApplicationContext;
import decorators.RegisterFormDecorator;
import factory.FormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class RegisterSceneFactory implements SceneFactory {

    @Override
    public Scene createScene() {
        GridPane registerPane = new FormFactory().createUserForm();
        RegisterFormDecorator registerFormDecorator = new RegisterFormDecorator();
        registerFormDecorator.addControls(registerPane, ApplicationContext.getInstance()
                .getActualStage());

        return new Scene(registerPane, 800, 400);
    }
}
