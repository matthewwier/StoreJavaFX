package data;

import classes.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class contains list of employees.
 *
 */
public class Employees {
    public static final ObservableList<Employee> employees =
            FXCollections.observableArrayList();
}
