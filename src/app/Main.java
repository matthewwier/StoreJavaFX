package app;

import data.Users;
import javafx.application.Application;
import javafx.stage.Stage;
import context.ApplicationContext;
import scenes.LoginSceneFactory;


public class Main extends Application {

    private ApplicationContext context;

    @Override
    public void start(Stage primaryStage) {

        context = ApplicationContext.getInstance();
        //actualStage = primaryStage;
        context.setActualStage(primaryStage);
        initLoginScene();
        context.getActualStage().show();
        //actualStage.show();

    }


    private void initLoginScene() {
        fillWithDefaultData();
        context.getActualStage().setTitle("Store Management");
        context.getActualStage().setScene(new LoginSceneFactory().createScene());
    }

    private void fillWithDefaultData() {
        Users.addUsers();
        Users.fillArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

