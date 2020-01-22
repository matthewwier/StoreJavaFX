package context;

import classes.Owner;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class ApplicationContext {
    private Stage actualStage;
    private Owner owner;
    private TextArea textArea;

    private static ApplicationContext applicationContext;

    private ApplicationContext() {
        textArea = new TextArea();
    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null)
            applicationContext = new ApplicationContext();

        return applicationContext;
    }

    public void setActualStage(Stage actualStage) {
        this.actualStage = actualStage;
    }

    public Stage getActualStage() {
        return actualStage;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public TextArea textArea() {
        return textArea;
    }
}
