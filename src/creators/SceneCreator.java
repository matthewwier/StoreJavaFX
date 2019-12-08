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

public class SceneCreator {


    public static Scene createStoreScene(Stage actualStage, Scene userScene, ObservableList<Item> data, File xmlFile) {
        Scene storeScene = new Scene(new Group(), 800, 400);
        TableView<Item> table = new TableView<Item>();
        table.setMaxHeight(250);
        HBox hb = new HBox();
        HBox del = new HBox();


        Label label = new Label("Store items:");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(260);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("name"));

        TableColumn amountCol = new TableColumn("Amount");
        amountCol.setMinWidth(260);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("amount"));

        TableColumn descCol = new TableColumn("Description");
        descCol.setMinWidth(260);
        descCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("description"));

        table.setItems(data);
        table.getColumns().addAll(nameCol, amountCol, descCol);

        final TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(nameCol.getPrefWidth());
        final TextField amountField = new TextField();
        amountField.setMaxWidth(amountCol.getPrefWidth());
        amountField.setPromptText("Amount");
        final TextField descField = new TextField();
        descField.setMaxWidth(descCol.getPrefWidth());
        descField.setPromptText("Description");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Item item = new Item(
                        nameField.getText(),
                        amountField.getText(),
                        descField.getText());
                data.add(item);

                try {
                    XMLOperations.addItemToXMLFile(item, xmlFile);
                } catch (ParserConfigurationException | IOException | SAXException | TransformerException ex) {
                    ex.printStackTrace();
                }

                nameField.clear();
                amountField.clear();
                descField.clear();
            }
        });

        final TextField deleteName = new TextField();
        deleteName.setPromptText("Name to delete or select row");
        deleteName.setMinWidth(175);
        deleteName.setMaxWidth(nameCol.getPrefWidth());

        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Item item = table.getSelectionModel().getSelectedItem();
//                System.out.println(item.getName());
                String nameToDelete = deleteName.getText();
                Iterator<Item> it = data.iterator();
                while (it.hasNext()) {
                    Item next = it.next();
                    if (next.getName().equals(nameToDelete)) {
                        System.out.println("remove" + next.getName());
                        it.remove();

                        try {
                            XMLOperations.removeItemFromXMLFile(next, xmlFile);
                        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(item!=null){
                        if(next.getName().equals(item.getName())
                                && next.getDescription().equals(item.getDescription()) && next.getAmount().equals(item.getAmount())){
                            System.out.println("remove" + next.getName());
                            try {
                                XMLOperations.removeItemFromXMLFile(next, xmlFile);
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
                    actualStage.setScene(userScene);
                }
        );

        hb.getChildren().addAll(nameField, amountField, descField, addButton);
        hb.setSpacing(3);
        del.getChildren().addAll(deleteName, deleteButton);
        del.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(backToUserButton);
        vbox.getChildren().addAll(label, table, hb, del);

        ((Group) storeScene.getRoot()).getChildren().addAll(vbox);

        return storeScene;

    }

    public static Scene createDetailsScene(Stage actualStage, Scene userScene, ObservableList<Employee> data, Owner owner, File xmlFile) {
        Scene storeScene = new Scene(new Group(), 800, 400);
        TableView<Employee> table = new TableView<Employee>();
        table.setMaxHeight(240);
        HBox hb = new HBox();
        HBox del = new HBox();


        Label ownerLabel = new Label("Owner:" + owner.getFirstname() + "" + owner.getLastname());
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
                data.add(new Employee(
                        firstnameField.getText(),
                        lastnameField.getText(),
                        ageField.getText()));
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
                    if(employee!=null){
                        if(next.getFirstname().equals(employee.getFirstname())
                                && next.getLastname().equals(employee.getLastname()) && next.getAge().equals(employee.getAge())){
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
