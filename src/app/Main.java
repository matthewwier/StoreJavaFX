package app;

import data.Users;
import javafx.application.Application;
import javafx.stage.Stage;
import context.ApplicationContext;
import scenes.LoginSceneCreator;

/**
 * Main class of application.
 *
 * @author Maciej Wiercioch
 */
public class Main extends Application {

    /** Keep application context */
    private ApplicationContext context;

    /**
     * Initialize login scene.
     */
    @Override
    public void start(Stage primaryStage) {
        context = ApplicationContext.getInstance();
        context.setActualStage(primaryStage);
        initLoginScene();
        context.getActualStage().show();
    }

    /**
     * Initialize login scene.
     */
    private void initLoginScene() {
        fillWithDefaultData();
        context.getActualStage().setTitle("Store Management");
        context.getActualStage().setScene(new LoginSceneCreator().createScene());
    }

    /**
     * Fills application with default users.
     */
    private void fillWithDefaultData() {
        Users.addUsers();
        Users.fillArrayList();
    }

    /**
     * Function launches application.
     * @param args arguments for launch function
     */
    public static void main(String[] args) {
        launch(args);
    }


}

