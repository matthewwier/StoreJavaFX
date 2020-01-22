package scenes;
import decorators.UserFormDecorator;
import factory.UserFormFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


/**
 * Factory for user scene.
 *
 */
public class UserSceneCreator implements SceneFactory{
    @Override
    public Scene createScene() {
        GridPane userPane = new UserFormFactory().createForm();
        UserFormDecorator userFormDecorator = new UserFormDecorator();
        userFormDecorator.addControls(userPane);

        return new Scene(userPane, 800, 400);
    }
}
