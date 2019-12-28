package decorators;

import creators.AbstractSceneCreator;
import creators.DetailsSceneCreator;
import creators.StoreSceneCreator;
import data.Employees;
import data.Items;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xmlworker.XMLWorker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static scenes.ScenesDetails.*;

public class UserFormDecorator extends FormDecorator {


    UserFormDecorator(GridPane gridPane, Stage stage) {
        super(gridPane, stage);
    }

    @Override
    public void addControls() {
        Label infoLabel = new Label("List of elements of your XML file:");
        infoLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        gridPane.add(infoLabel, 1, 0, 2, 1);
        GridPane.setHalignment(infoLabel, HPos.CENTER);
        GridPane.setMargin(infoLabel, new Insets(20, 0, 20, 0));

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Select XML file...");
        TextArea textAreaXML = new TextArea();
        textAreaXML.setWrapText(true);
        openButton.setOnAction(
                e -> {
                    xmlFile = fileChooser.showOpenDialog(stage);
                    try {
                        XMLWorker worker = XMLWorker.getInstance();
                        worker.setDocumentBuilder();
                        worker.setXmlFile(xmlFile);
                        textAreaXML.setText(worker.readXMLFile());
                        if (Employees.employees.size() == 0) {

                            worker.addItemsDataFromXMLFile(Items.data);
                            worker.addEmployeesDataFromXMLFile(Employees.employees);
                            owner = worker.addOwnerFromXMLFile();
                        }

                    } catch (IOException | ParserConfigurationException | SAXException | TransformerException ex) {
                        ex.printStackTrace();
                    }


                });
        openButton.setMinWidth(150);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Button closeAppButton = new Button("Close app");
        closeAppButton.setMinWidth(150);
        closeAppButton.setOnAction(e -> {
                    Stage stage = (Stage) closeAppButton.getScene().getWindow();
                    stage.close();
                }
        );

        Button detailsButton = new Button("See store details");
        detailsButton.setMinWidth(150);
        detailsButton.setOnAction(e -> {
                    AbstractSceneCreator sceneCreator = new DetailsSceneCreator();
                    stage.setScene(sceneCreator.create(stage, userScene, Employees.employees, owner, xmlFile, textAreaXML));
                }
        );

        vbox.getChildren().addAll(openButton, detailsButton, closeAppButton);

        gridPane.add(vbox, 0, 2);
        gridPane.add(textAreaXML, 1, 2);
        Button toStore = new Button();
        toStore.setText("To Store");
        toStore.setOnAction(e -> {
            AbstractSceneCreator sceneCreator = new StoreSceneCreator();
            stage.setScene(sceneCreator.create(stage, userScene, Items.data, owner, xmlFile, textAreaXML));
        });


        gridPane.add(toStore, 3, 2);
    }


}
