package decorators;


import javafx.scene.layout.GridPane;

/**
 * Decorator interface for grid panes.
 *
 */
public interface FormDecorator {
    /**
     * Add controls to given grid pane and stage.
     * @param gridPane Grid pane to decorate
     */
    public void addControls(GridPane gridPane);
}
