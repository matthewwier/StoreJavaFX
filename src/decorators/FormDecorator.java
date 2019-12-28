package decorators;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class FormDecorator {

    protected GridPane gridPane;
    protected Stage stage;

    public FormDecorator(GridPane gridPane, Stage stage) {
        this.gridPane = gridPane;
        this.stage = stage;
    }

    public abstract void addControls();
}
