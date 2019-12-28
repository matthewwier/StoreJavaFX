package decorators;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class FormDecorator {

    GridPane gridPane;
    Stage stage;

    FormDecorator(GridPane gridPane, Stage stage) {
        this.gridPane = gridPane;
        this.stage = stage;
    }

    public abstract void addControls();
}
