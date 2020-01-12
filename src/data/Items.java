package data;

import classes.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *  Class provides list of items in store.
 *
 */
public class Items {
    public static final ObservableList<Item> data =
            FXCollections.observableArrayList();
}
