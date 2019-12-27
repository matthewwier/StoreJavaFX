package creators;

import classes.Owner;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;

public interface AbstractSceneCreator {
    public Scene create(Stage actualStage, Scene userScene, ObservableList data, Owner owner, File xmlFile, TextArea textArea);
}
