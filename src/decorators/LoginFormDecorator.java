package decorators;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginFormDecorator implements FormDecorator {

    private GridPane gridPane;
    private Stage stage;

    LoginFormDecorator(GridPane gridPane, Stage stage){
        this.gridPane = gridPane;
        this.stage = stage;
    }

    @Override
    public void addControls() {

    }
}
