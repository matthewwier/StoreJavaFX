package creators;

import classes.Item;
import classes.Owner;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xmloperations.XMLOperations;
import xmlworker.XMLWorker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class StoreSceneCreator implements AbstractSceneCreator {

    @Override
    public Scene create(Stage actualStage, Scene userScene, ObservableList data, Owner owner, File xmlFile, TextArea textArea) {
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

        XMLWorker worker = XMLWorker.getInstance();
        worker.setXmlFile(xmlFile);
        worker.setDocumentBuilder();

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
                    worker.addItemToXMLFile(item);
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

                String nameToDelete = deleteName.getText();
                Iterator<Item> it = data.iterator();
                while (it.hasNext()) {
                    Item next = it.next();
                    if (next.getName().equals(nameToDelete)) {
                        System.out.println("remove" + next.getName());
                        it.remove();

                        try {
                            worker.removeItemFromXMLFile(next);
                        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (item != null) {
                        if (next.getName().equals(item.getName())
                                && next.getDescription().equals(item.getDescription()) && next.getAmount().equals(item.getAmount())) {
                            System.out.println("remove" + next.getName());
                            try {
                                worker.removeItemFromXMLFile(next);
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
                    try {
                        textArea.setText(worker.readXMLFile());
                    } catch (IOException | SAXException | ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
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
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        ((Group) storeScene.getRoot()).getChildren().addAll(vbox);

        return storeScene;

    }
}
