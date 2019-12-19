package creators;

import classes.Employee;
import classes.Item;
import classes.Owner;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xmloperations.XMLOperations;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class DetailsSceneCreator implements AbstractSceneCreator {
    @Override
    public Scene create(Stage actualStage, Scene userScene, ObservableList data, Owner owner, File xmlFile, TextArea textArea) {
        Scene storeScene = new Scene(new Group(), 800, 400);
        TableView<Employee> table = new TableView<Employee>();
        table.setMaxHeight(240);
        HBox hb = new HBox();
        HBox del = new HBox();

        Label ownerLabel = new Label("Owner:" + owner.getFirstname() + " " + owner.getLastname());
        ownerLabel.setFont(new Font("Arial", 20));
        Label label = new Label("Store employees:");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Firstname");
        firstNameCol.setMinWidth(260);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("firstname"));

        TableColumn lastnameCol = new TableColumn("Lastname");
        lastnameCol.setMinWidth(260);
        lastnameCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("lastname"));

        TableColumn ageCol = new TableColumn("Age");
        ageCol.setMinWidth(260);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("age"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastnameCol, ageCol);

        final TextField firstnameField = new TextField();
        firstnameField.setPromptText("Firstname");
        firstnameField.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField lastnameField = new TextField();
        lastnameField.setMaxWidth(lastnameCol.getPrefWidth());
        lastnameField.setPromptText("Lastname");
        final TextField ageField = new TextField();
        ageField.setMaxWidth(ageCol.getPrefWidth());
        ageField.setPromptText("Age");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Employee employee = new Employee(
                        firstnameField.getText(),
                        lastnameField.getText(),
                        ageField.getText());
                data.add(employee);

                try {
                    XMLOperations.addEmployeeToXMLFile(employee, xmlFile);
                } catch (ParserConfigurationException | IOException | SAXException | TransformerException ex) {
                    ex.printStackTrace();
                }

                firstnameField.clear();
                lastnameField.clear();
                ageField.clear();
            }
        });

        final TextField deleteName = new TextField();
        deleteName.setPromptText("Name to delete or select row");
        deleteName.setMinWidth(175);
        deleteName.setMaxWidth(firstNameCol.getPrefWidth());

        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Employee employee = table.getSelectionModel().getSelectedItem();

                String nameToDelete = deleteName.getText();
                Iterator<Employee> it = data.iterator();
                while (it.hasNext()) {
                    Employee next = it.next();
                    if (next.getFirstname().equals(nameToDelete)) {
                        System.out.println("remove" + next.getFirstname());
                        try {
                            XMLOperations.removeEmployeeFromXMLFile(next, xmlFile);
                        } catch (ParserConfigurationException | IOException | TransformerException | SAXException ex) {
                            ex.printStackTrace();
                        }
                        it.remove();
                    }
                    if (employee != null) {
                        if (next.getFirstname().equals(employee.getFirstname())
                                && next.getLastname().equals(employee.getLastname()) && next.getAge().equals(employee.getAge())) {
                            System.out.println("remove" + next.getFirstname());
                            try {
                                XMLOperations.removeEmployeeFromXMLFile(next, xmlFile);
                            } catch (ParserConfigurationException | IOException | TransformerException | SAXException ex) {
                                ex.printStackTrace();
                            }
                            it.remove();
                        }
                    }
                }
                deleteName.clear();
            }
        });

        final Button backToUserButton = new Button("Back to XML file");
        backToUserButton.setOnAction(e -> {
                    // aktualizacja pliku
                    try {
                        textArea.setText(XMLOperations.readXMLFile(xmlFile));
                    } catch (IOException | SAXException | ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
                    actualStage.setScene(userScene);
                }
        );

        hb.getChildren().addAll(firstnameField, lastnameField, ageField, addButton);
        hb.setSpacing(3);
        del.getChildren().addAll(deleteName, deleteButton);
        del.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(backToUserButton);

        HBox ownerHBox = new HBox();
        ownerHBox.setAlignment(Pos.TOP_CENTER);
        ownerHBox.getChildren().add(ownerLabel);
        vbox.getChildren().addAll(ownerHBox, label, table, hb, del);

        ((Group) storeScene.getRoot()).getChildren().addAll(vbox);

        return storeScene;
    }
}
