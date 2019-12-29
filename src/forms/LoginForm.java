package forms;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

public class LoginForm extends Form{

    public LoginForm(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(40, 40, 40, 40));
        setHgap(10);
        setVgap(10);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
    }

}
