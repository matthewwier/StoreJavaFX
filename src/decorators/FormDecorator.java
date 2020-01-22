package decorators;


import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public interface FormDecorator {
    public void addControls(GridPane gridPane, Stage stage);
}
