package scenes;

import context.ApplicationContext;
import decorators.UserFormDecorator;
import factory.AbstractFormFactory;
import factory.FormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class UserSceneFactory implements SceneFactory{
    @Override
    public Scene createScene() {
        AbstractFormFactory factory = new FormFactory();
        GridPane userPane = factory.createUserForm();
        UserFormDecorator userFormDecorator = new UserFormDecorator();
        userFormDecorator.addControls(userPane, ApplicationContext.getInstance()
                .getActualStage());

        return new Scene(userPane, 800, 400);
    }
}
