package context;

import classes.Owner;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Singleton class that keeps application context.
 *
 * @author Maciej Wiercioch
 */
public class ApplicationContext {
    /** Actual stage of application */
    private Stage actualStage;
    /** Owner of XML document */
    private Owner owner;
    /** Text area for XML documents */
    private TextArea textArea;
    /** Instance of XML worker */
    private static ApplicationContext applicationContext;
    /**
     * Constructor for application context.
     *
     */
    private ApplicationContext() {
        textArea = new TextArea();
    }

    /**
     * Return instance of XML worker.
     * @return xml worker
     */
    public static ApplicationContext getInstance() {
        if (applicationContext == null)
            applicationContext = new ApplicationContext();

        return applicationContext;
    }
    /**
     * Set actualStage of application.
     * @param actualStage actualStage
     */
    public void setActualStage(Stage actualStage) {
        this.actualStage = actualStage;
    }
    /**
     * Return actual stage of application.
     * @return actual stage of application
     */
    public Stage getActualStage() {
        return actualStage;
    }
    /**
     * Return xml file owner.
     * @return xml file owner
     */
    public Owner getOwner() {
        return owner;
    }
    /**
     * Set owner of XML file.
     * @param owner owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    /**
     * Return text area.
     * @return text area
     */
    public TextArea textArea() {
        return textArea;
    }
}
